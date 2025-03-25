package com.ruoyi.system.service;

import com.ruoyi.system.domain.UserShoppingOrder;

import java.util.List;

/**
 * 用户购物订单Service接口
 * 
 * @author ruoyi
 * @date 2024-12-24
 */
public interface IUserShoppingOrderService 
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
     * 填充其他信息
     * @param userShoppingOrders
     */
    public void fillOtherInfo(List<UserShoppingOrder> userShoppingOrders);

    /**
     * 新增用户购物订单
     * 
     * @param userShoppingOrder 用户购物订单
     * @return 结果
     */
    public int insertUserShoppingOrder(UserShoppingOrder userShoppingOrder);

    /**
     * 修改用户购物订单
     * 
     * @param userShoppingOrder 用户购物订单
     * @return 结果
     */
    public int updateUserShoppingOrder(UserShoppingOrder userShoppingOrder);

    /**
     * 批量删除用户购物订单
     * 
     * @param ids 需要删除的用户购物订单主键集合
     * @return 结果
     */
    public int deleteUserShoppingOrderByIds(Long[] ids);

    /**
     * 删除用户购物订单信息
     * 
     * @param id 用户购物订单主键
     * @return 结果
     */
    public int deleteUserShoppingOrderById(Long id);

    /**
     * 购物订单平台发货
     * @param userShoppingOrder
     * @return
     */
    public int shipUserShoppingOrder(UserShoppingOrder userShoppingOrder);


    /**
     * 用户提交商城订单
     *
     * @param userShoppingOrders 用户购物订单
     * @return 结果
     */
    public int userAddShoppingOrders(List<UserShoppingOrder> userShoppingOrders);

    /**
     * 用户支付商城订单
     * @param userShoppingOrders
     * @return
     */
    public int userPayShoppingOrders(List<UserShoppingOrder> userShoppingOrders);

    /**
     * 用户取消商订单
     * @param userShoppingOrder
     * @return
     */
    public int userCancelShoppingOrders(UserShoppingOrder userShoppingOrder);

    /**
     * 用户确认收货
     * @param userShoppingOrder
     * @return
     */
    public int userConfirmReceipt(UserShoppingOrder userShoppingOrder);

    /**
     * 商户提交订单到后台
     * @param userShoppingOrders
     * @return
     */
    public int sellerSubmitOrderToPlatform(List<UserShoppingOrder> userShoppingOrders);

    /**
     * 用户申请退款
     * @param userShoppingOrder
     * @return
     */
    public int userApplyRefund(UserShoppingOrder userShoppingOrder);

    /**
     * 后台购物订单退款审核
     * @param userShoppingOrder
     * @return
     */
    public int refundReview(UserShoppingOrder userShoppingOrder);
}
