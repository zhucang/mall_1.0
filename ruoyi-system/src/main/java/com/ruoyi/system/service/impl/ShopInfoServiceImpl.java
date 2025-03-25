package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.ShopInfo;
import com.ruoyi.system.mapper.ShopInfoMapper;
import com.ruoyi.system.service.IShopInfoService;
import com.ruoyi.system.service.IUserFavoriteShopService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 店铺信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-12-21
 */
@Service
public class ShopInfoServiceImpl implements IShopInfoService 
{
    @Resource
    private ShopInfoMapper shopInfoMapper;

    @Resource
    private IUserFavoriteShopService userFavoriteShopService;

    /**
     * 查询店铺信息
     * 
     * @param id 店铺信息主键
     * @return 店铺信息
     */
    @Override
    public ShopInfo selectShopInfoById(Long id)
    {
        return shopInfoMapper.selectShopInfoById(id);
    }

    /**
     * 填充其他信息
     * @param shopInfo
     * @return
     */
    @Override
    public void fillOtherInfo(ShopInfo shopInfo){
        int userFavoriteStatus = 0;
        try{
            //如果是登录状态
            Long userId = SecurityUtils.getUserId();
            userFavoriteStatus = userFavoriteShopService.getUserFavoriteStatus(userId, shopInfo.getId());
        }catch (Exception e){

        }
        //填充用户店铺收藏状态
        shopInfo.getParams().put("userFavoriteStatus",userFavoriteStatus);
    }

    /**
     * 查询店铺信息列表
     * 
     * @param shopInfo 店铺信息
     * @return 店铺信息
     */
    @Override
    public List<ShopInfo> selectShopInfoList(ShopInfo shopInfo)
    {
        return shopInfoMapper.selectShopInfoList(shopInfo);
    }

    /**
     * 新增店铺信息
     * 
     * @param shopInfo 店铺信息
     * @return 结果
     */
    @Override
    public int insertShopInfo(ShopInfo shopInfo)
    {
        shopInfo.setCreateTime(DateUtils.getNowDate());
        return shopInfoMapper.insertShopInfo(shopInfo);
    }

    /**
     * 修改店铺信息
     * 
     * @param shopInfo 店铺信息
     * @return 结果
     */
    @Override
    public int updateShopInfo(ShopInfo shopInfo)
    {
        shopInfo.setUpdateTime(DateUtils.getNowDate());
        return shopInfoMapper.updateShopInfo(shopInfo);
    }

    /**
     * 批量删除店铺信息
     * 
     * @param ids 需要删除的店铺信息主键
     * @return 结果
     */
    @Override
    public int deleteShopInfoByIds(Long[] ids)
    {
        return shopInfoMapper.deleteShopInfoByIds(ids);
    }

    /**
     * 删除店铺信息信息
     * 
     * @param id 店铺信息主键
     * @return 结果
     */
    @Override
    public int deleteShopInfoById(Long id)
    {
        return shopInfoMapper.deleteShopInfoById(id);
    }

    /**
     * 变更店铺粉丝数量
     */
    @Override
    public int updateShopFansNum(Long shopInfoId,Integer changeNum){
        return shopInfoMapper.updateShopFansNum(shopInfoId,changeNum);
    }
}
