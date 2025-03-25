package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.UserShoppingOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户购物订单Mapper接口
 * 
 * @author ruoyi
 * @date 2024-12-24
 */
public interface UserShoppingOrderMapper 
{
    /**
     * 查询用户购物订单
     * 
     * @param id 用户购物订单主键
     * @return 用户购物订单
     */
    public UserShoppingOrder selectUserShoppingOrderById(Long id);

    /**
     * 查询用户购物订单列表
     * 
     * @param userShoppingOrder 用户购物订单
     * @return 用户购物订单集合
     */
    public List<UserShoppingOrder> selectUserShoppingOrderList(UserShoppingOrder userShoppingOrder);

    /**
     * 新增用户购物订单
     * 
     * @param userShoppingOrder 用户购物订单
     * @return 结果
     */
    public int insertUserShoppingOrder(UserShoppingOrder userShoppingOrder);

    /**
     * 批量新增用户购物订单
     *
     * @param userShoppingOrders 用户购物订单
     * @return 结果
     */
    public int insertUserShoppingOrders(@Param("list") List<UserShoppingOrder> userShoppingOrders);

    /**
     * 修改用户购物订单
     * 
     * @param userShoppingOrder 用户购物订单
     * @return 结果
     */
    public int updateUserShoppingOrder(UserShoppingOrder userShoppingOrder);

    /**
     * 删除用户购物订单
     * 
     * @param id 用户购物订单主键
     * @return 结果
     */
    public int deleteUserShoppingOrderById(Long id);

    /**
     * 批量删除用户购物订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserShoppingOrderByIds(Long[] ids);

}
