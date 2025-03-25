package com.ruoyi.system.service;

import com.ruoyi.system.domain.UserShoppingOrderDetail;

import java.util.List;

/**
 * 用户购物订单明细Service接口
 * 
 * @author ruoyi
 * @date 2024-12-24
 */
public interface IUserShoppingOrderDetailService 
{
    /**
     * 查询用户购物订单明细
     * 
     * @param id 用户购物订单明细主键
     * @return 用户购物订单明细
     */
    public UserShoppingOrderDetail selectUserShoppingOrderDetailById(Long id);

    /**
     * 查询用户购物订单明细列表
     * 
     * @param userShoppingOrderDetail 用户购物订单明细
     * @return 用户购物订单明细集合
     */
    public List<UserShoppingOrderDetail> selectUserShoppingOrderDetailList(UserShoppingOrderDetail userShoppingOrderDetail);

    /**
     * 新增用户购物订单明细
     * 
     * @param userShoppingOrderDetail 用户购物订单明细
     * @return 结果
     */
    public int insertUserShoppingOrderDetail(UserShoppingOrderDetail userShoppingOrderDetail);

    /**
     * 批量新增用户购物订单明细
     * @param userShoppingOrderDetails
     * @return
     */
    public int insertUserShoppingOrderDetails(List<UserShoppingOrderDetail> userShoppingOrderDetails);

    /**
     * 修改用户购物订单明细
     * 
     * @param userShoppingOrderDetail 用户购物订单明细
     * @return 结果
     */
    public int updateUserShoppingOrderDetail(UserShoppingOrderDetail userShoppingOrderDetail);

    /**
     * 批量删除用户购物订单明细
     * 
     * @param ids 需要删除的用户购物订单明细主键集合
     * @return 结果
     */
    public int deleteUserShoppingOrderDetailByIds(Long[] ids);

    /**
     * 删除用户购物订单明细信息
     * 
     * @param id 用户购物订单明细主键
     * @return 结果
     */
    public int deleteUserShoppingOrderDetailById(Long id);
}
