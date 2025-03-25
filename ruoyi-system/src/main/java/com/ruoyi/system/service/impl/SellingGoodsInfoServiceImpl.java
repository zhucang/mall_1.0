package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.HintConstants;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.entity.UserInfo;
import com.ruoyi.common.exception.LangException;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.SellingGoodsInfoMapper;
import com.ruoyi.system.mapper.UserInfoMapper;
import com.ruoyi.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 在售商品信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-12-20
 */
@Service
public class SellingGoodsInfoServiceImpl implements ISellingGoodsInfoService 
{
    @Resource
    private SellingGoodsInfoMapper sellingGoodsInfoMapper;

    @Autowired
    private IAttrTypeService attrTypeService;

    @Autowired
    private IUserFavoriteGoodsService userFavoriteGoodsService;

    @Autowired
    private ISkuService skuService;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Autowired
    private IShopInfoService shopInfoService;

    @Autowired
    private ISystemGoodsInfoService systemGoodsInfoService;

    /**
     * 查询在售商品信息
     * 
     * @param id 在售商品信息主键
     * @return 在售商品信息
     */
    @Override
    public SellingGoodsInfo selectSellingGoodsInfoById(Long id)
    {
        return sellingGoodsInfoMapper.selectSellingGoodsInfoById(id);
    }

    /**
     * 填充其他信息
     * @param sellingGoodsInfo
     * @return
     */
    @Override
    public void fillOtherInfo(SellingGoodsInfo sellingGoodsInfo){
        //填充属性类型
        AttrType attrType = new AttrType();
        attrType.setSystemGoodsInfoId(sellingGoodsInfo.getSystemGoodsInfoId());
        attrType.setStatus(1);
        List<AttrType> attrTypes = attrTypeService.selectAttrTypeList(attrType);
        sellingGoodsInfo.getParams().put("attrTypes",attrTypes);

        //填充sku
        Sku sku = new Sku();
        sku.setSystemGoodsInfoId(sellingGoodsInfo.getSystemGoodsInfoId());
        List<Sku> skus = skuService.selectSkuList(sku);
        sellingGoodsInfo.getParams().put("skus",skus);

        //填充用户商品收藏状态
        int userFavoriteStatus = 0;
        try{
            //如果是登录状态
            Long userId = SecurityUtils.getUserId();
            userFavoriteStatus = userFavoriteGoodsService.getUserFavoriteStatus(userId, sellingGoodsInfo.getId());
        }catch (Exception e){

        }
        sellingGoodsInfo.getParams().put("userFavoriteStatus",userFavoriteStatus);
    }

    /**
     * 查询在售商品信息列表
     * 
     * @param sellingGoodsInfo 在售商品信息
     * @return 在售商品信息
     */
    @Override
    public List<SellingGoodsInfo> selectSellingGoodsInfoList(SellingGoodsInfo sellingGoodsInfo)
    {
        return sellingGoodsInfoMapper.selectSellingGoodsInfoList(sellingGoodsInfo);
    }

    /**
     * 新增在售商品信息
     * 
     * @param sellingGoodsInfo 在售商品信息
     * @return 结果
     */
    @Override
    public int insertSellingGoodsInfo(SellingGoodsInfo sellingGoodsInfo)
    {
        sellingGoodsInfo.setCreateTime(DateUtils.getNowDate());
        return sellingGoodsInfoMapper.insertSellingGoodsInfo(sellingGoodsInfo);
    }

    /**
     * 修改在售商品信息
     * 
     * @param sellingGoodsInfo 在售商品信息
     * @return 结果
     */
    @Override
    public int updateSellingGoodsInfo(SellingGoodsInfo sellingGoodsInfo)
    {
        sellingGoodsInfo.setUpdateTime(DateUtils.getNowDate());
        return sellingGoodsInfoMapper.updateSellingGoodsInfo(sellingGoodsInfo);
    }

    /**
     * 批量删除在售商品信息
     * 
     * @param ids 需要删除的在售商品信息主键
     * @return 结果
     */
    @Override
    public int deleteSellingGoodsInfoByIds(Long[] ids)
    {
        return sellingGoodsInfoMapper.deleteSellingGoodsInfoByIds(ids);
    }

    /**
     * 删除在售商品信息信息
     * 
     * @param id 在售商品信息主键
     * @return 结果
     */
    @Override
    public int deleteSellingGoodsInfoById(Long id)
    {
        return sellingGoodsInfoMapper.deleteSellingGoodsInfoById(id);
    }

    /**
     * 商户添加商品到店铺
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int sellerAddGoodsToShop(List<Long> systemGoodsIds){
        //商户ID
        Long userId = SecurityUtils.getUserId();
        //商户信息
        UserInfo userInfo = userInfoMapper.selectUserInfoById(userId);
        if (userInfo == null || !userInfo.getIsDel().equals(0)){
            throw new ServiceException("获取用户信息异常",HttpStatus.UNAUTHORIZED);
        }
        //检验是否商户
        if (!userInfo.getSellerFlag().equals(1)){
            throw new ServiceException("校验商户身份异常",HttpStatus.UNAUTHORIZED);
        }
        //获取店铺信息
        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setSellerId(userId);
        List<ShopInfo> shopInfos = shopInfoService.selectShopInfoList(shopInfo);
        //店铺信息
        shopInfo = shopInfos.get(0);
        //系统商品信息
        SystemGoodsInfo systemGoodsInfoSearch = new SystemGoodsInfo();
        systemGoodsInfoSearch.getParams().put("ids",systemGoodsIds);
        systemGoodsInfoSearch.getParams().put("systemGoodsInfoDelFlag",0);
        systemGoodsInfoSearch.getParams().put("categoryDelFlag",0);
        List<SystemGoodsInfo> systemGoodsInfos = systemGoodsInfoService.selectSystemGoodsInfoList(systemGoodsInfoSearch);
        if (systemGoodsInfos.size() == 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"获取商品信息异常");
        }
        //即将插入的在售商品数据
        List<SellingGoodsInfo> sellingGoodsInfos = new ArrayList<>();
        //遍历
        for (int i = 0; i < systemGoodsInfos.size(); i++) {
            //系统商品信息
            SystemGoodsInfo systemGoodsInfo = systemGoodsInfos.get(i);
            //系统价格
            BigDecimal systemPrice = systemGoodsInfo.getSystemPrice();
            //出售价格(暂时取1.2倍)
            BigDecimal sellingPrice = systemPrice.multiply(new BigDecimal(1.2)).setScale(Constants.BIGDECIMAL_SCALE,Constants.BIGDECIMAL_ROUNDINGMODE);
            SellingGoodsInfo sellingGoodsInfo = new SellingGoodsInfo();
            sellingGoodsInfo.setSystemGoodsInfoId(systemGoodsInfo.getId());
            sellingGoodsInfo.setSellingPrice(sellingPrice);
            sellingGoodsInfo.setRecommendedFlag(0);
            sellingGoodsInfo.setSoldNum(0);
            sellingGoodsInfo.setShopInfoId(shopInfo.getId());
            sellingGoodsInfo.setSellerId(userId);
            sellingGoodsInfo.setCreateTime(new Date());
            sellingGoodsInfo.setDelFlag(0);
            sellingGoodsInfos.add(sellingGoodsInfo);
        }
        int insertSellingGoodsInfo = sellingGoodsInfoMapper.insertSellingGoodsInfos(sellingGoodsInfos);
        if (insertSellingGoodsInfo != sellingGoodsInfos.size()){
            throw new LangException(HintConstants.SYSTEM_BUSY,"添加店铺商品异常");
        }
        //更新店铺商品数量
        shopInfo.setGoodsNum(shopInfo.getGoodsNum()+insertSellingGoodsInfo);
        int updateShopInfo = shopInfoService.updateShopInfo(shopInfo);
        if (updateShopInfo == 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"更新店铺信息异常");
        }
        return 1;
    }

    /**
     * 商户移除店铺商品
     */
    @Override
    public int sellerRemoveGoodsFromShop(List<Long> ids){
        //商户ID
        Long userId = SecurityUtils.getUserId();
        //商户信息
        UserInfo userInfo = userInfoMapper.selectUserInfoById(userId);
        if (userInfo == null || !userInfo.getIsDel().equals(0)){
            throw new ServiceException("获取用户信息异常",HttpStatus.UNAUTHORIZED);
        }
        //检验是否商户
        if (!userInfo.getSellerFlag().equals(1)){
            throw new ServiceException("校验商户身份异常",HttpStatus.UNAUTHORIZED);
        }
        //获取店铺商品信息
        SellingGoodsInfo sellingGoodsInfoSearch = new SellingGoodsInfo();
        sellingGoodsInfoSearch.getParams().put("ids",ids);
        List<SellingGoodsInfo> sellingGoodsInfos = sellingGoodsInfoMapper.selectSellingGoodsInfoList(sellingGoodsInfoSearch);
        if (sellingGoodsInfos.size() != ids.size()){
            throw new LangException(HintConstants.SYSTEM_BUSY,"获取店铺信息异常");
        }
        if (sellingGoodsInfos.stream().filter(a->!a.getSellerId().equals(userId)).count() > 0){
            throw new ServiceException("校验身份异常",HttpStatus.UNAUTHORIZED);
        }
        //删除
        int deleteSellingGoodsInfoByIds = sellingGoodsInfoMapper.deleteSellingGoodsInfoByIds(ids.toArray(new Long[ids.size()]));
        if (deleteSellingGoodsInfoByIds != ids.size()){
            throw new LangException(HintConstants.SYSTEM_BUSY,"移除店铺商品异常");
        }
        //获取店铺信息
        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setSellerId(userId);
        List<ShopInfo> shopInfos = shopInfoService.selectShopInfoList(shopInfo);
        //店铺信息
        shopInfo = shopInfos.get(0);
        //更新店铺商品数量
        shopInfo.setGoodsNum(shopInfo.getGoodsNum()-deleteSellingGoodsInfoByIds);
        int updateShopInfo = shopInfoService.updateShopInfo(shopInfo);
        if (updateShopInfo == 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"更新店铺信息异常");
        }
        return 1;
    }
}
