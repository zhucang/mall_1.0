package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.UserShoppingCart;

/**
 * 用户购物车Mapper接口
 * 
 * @author ruoyi
 * @date 2024-12-26
 */
public interface UserShoppingCartMapper 
{
    /**
     * 查询用户购物车
     * 
     * @param id 用户购物车主键
     * @return 用户购物车
     */
    public UserShoppingCart selectUserShoppingCartById(Long id);

    /**
     * 查询用户购物车列表
     * 
     * @param userShoppingCart 用户购物车
     * @return 用户购物车集合
     */
    public List<UserShoppingCart> selectUserShoppingCartList(UserShoppingCart userShoppingCart);

    /**
     * 新增用户购物车
     * 
     * @param userShoppingCart 用户购物车
     * @return 结果
     */
    public int insertUserShoppingCart(UserShoppingCart userShoppingCart);

    /**
     * 修改用户购物车
     * 
     * @param userShoppingCart 用户购物车
     * @return 结果
     */
    public int updateUserShoppingCart(UserShoppingCart userShoppingCart);

    /**
     * 删除用户购物车
     * 
     * @param id 用户购物车主键
     * @return 结果
     */
    public int deleteUserShoppingCartById(Long id);

    /**
     * 批量删除用户购物车
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserShoppingCartByIds(Long[] ids);
}
