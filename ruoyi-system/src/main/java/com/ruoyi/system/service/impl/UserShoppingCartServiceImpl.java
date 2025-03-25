package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.HintConstants;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.exception.LangException;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.UserShoppingCart;
import com.ruoyi.system.mapper.UserShoppingCartMapper;
import com.ruoyi.system.service.IUserShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户购物车Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-12-26
 */
@Service
public class UserShoppingCartServiceImpl implements IUserShoppingCartService 
{
    @Resource
    private UserShoppingCartMapper userShoppingCartMapper;

    /**
     * 查询用户购物车
     * 
     * @param id 用户购物车主键
     * @return 用户购物车
     */
    @Override
    public UserShoppingCart selectUserShoppingCartById(Long id)
    {
        return userShoppingCartMapper.selectUserShoppingCartById(id);
    }

    /**
     * 查询用户购物车列表
     * 
     * @param userShoppingCart 用户购物车
     * @return 用户购物车
     */
    @Override
    public List<UserShoppingCart> selectUserShoppingCartList(UserShoppingCart userShoppingCart)
    {
        return userShoppingCartMapper.selectUserShoppingCartList(userShoppingCart);
    }

    /**
     * 新增用户购物车
     * 
     * @param userShoppingCart 用户购物车
     * @return 结果
     */
    @Override
    public int insertUserShoppingCart(UserShoppingCart userShoppingCart)
    {
        //当前时间
        Date nowDateTime = DateUtils.getNowDate();
        UserShoppingCart userShoppingCartSearch = new UserShoppingCart();
        userShoppingCartSearch.setUserId(userShoppingCart.getUserId());
        userShoppingCartSearch.setSellingGoodsInfoId(userShoppingCart.getSellingGoodsInfoId());
        userShoppingCartSearch.setSkuId(userShoppingCart.getSkuId());
        List<UserShoppingCart> userShoppingCarts = userShoppingCartMapper.selectUserShoppingCartList(userShoppingCartSearch);
        if (userShoppingCarts.size() > 0){
            //如果该sku的商品已在购物车
            UserShoppingCart vo = userShoppingCarts.get(0);
            //更新数量
            vo.setQuantity(userShoppingCart.getQuantity() + vo.getQuantity());
            vo.setUpdateTime(nowDateTime);
            return userShoppingCartMapper.updateUserShoppingCart(vo);
        }
        userShoppingCart.setCreateTime(nowDateTime);
        userShoppingCart.setUpdateTime(nowDateTime);
        return userShoppingCartMapper.insertUserShoppingCart(userShoppingCart);
    }

    /**
     * 修改用户购物车
     * 
     * @param userShoppingCart 用户购物车
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUserShoppingCart(UserShoppingCart userShoppingCart)
    {
        //购物车ID
        Long id = userShoppingCart.getId();
        //购物车信息
        UserShoppingCart vo = userShoppingCartMapper.selectUserShoppingCartById(id);
        //校验用户信息
        if (!SecurityUtils.getUserId().equals(vo.getUserId())){
            throw new ServiceException("校验用户信息异常", HttpStatus.UNAUTHORIZED);
        }
        //在售商品信息ID
        Long sellingGoodsInfoId = userShoppingCart.getSellingGoodsInfoId();
        if (sellingGoodsInfoId != null && !sellingGoodsInfoId.equals(vo.getSellingGoodsInfoId())){
            throw new LangException(HintConstants.SYSTEM_BUSY,"校验购物车信息异常");
        }
        UserShoppingCart userShoppingCartSearch = new UserShoppingCart();
        userShoppingCartSearch.setUserId(userShoppingCart.getUserId());
        userShoppingCartSearch.setSellingGoodsInfoId(userShoppingCart.getSellingGoodsInfoId());
        userShoppingCartSearch.setSkuId(userShoppingCart.getSkuId());
        List<UserShoppingCart> userShoppingCarts = userShoppingCartMapper.selectUserShoppingCartList(userShoppingCartSearch);
        if (userShoppingCarts.size() > 0){
            //如果该sku的商品已在购物车
            UserShoppingCart vo2 = userShoppingCarts.get(0);
            //更新数量
            userShoppingCart.setQuantity(userShoppingCart.getQuantity() + vo2.getQuantity());
            //删除另一条购物车信息
            int deleteUserShoppingCartById = userShoppingCartMapper.deleteUserShoppingCartById(vo2.getId());
            if (deleteUserShoppingCartById == 0){
                throw new LangException(HintConstants.SYSTEM_BUSY,"更新购物车信息异常");
            }
        }
        userShoppingCart.setUpdateTime(DateUtils.getNowDate());
        return userShoppingCartMapper.updateUserShoppingCart(userShoppingCart);
    }

    /**
     * 批量删除用户购物车
     * 
     * @param ids 需要删除的用户购物车主键
     * @return 结果
     */
    @Override
    public int deleteUserShoppingCartByIds(Long[] ids)
    {
        return userShoppingCartMapper.deleteUserShoppingCartByIds(ids);
    }

    /**
     * 删除用户购物车信息
     * 
     * @param id 用户购物车主键
     * @return 结果
     */
    @Override
    public int deleteUserShoppingCartById(Long id)
    {
        return userShoppingCartMapper.deleteUserShoppingCartById(id);
    }
}
