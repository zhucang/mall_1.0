package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.UserAfterSaleOrder;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 商城售后订单Service接口
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public interface IUserAfterSaleOrderService 
{
    /**
     * 查询商城售后订单
     * 
     * @param userAfterSaleOrderId 商城售后订单主键
     * @return 商城售后订单
     */
    public UserAfterSaleOrder selectUserAfterSaleOrderByUserAfterSaleOrderId(Long userAfterSaleOrderId);

    /**
     * 查询商城售后订单列表
     * 
     * @param userAfterSaleOrder 商城售后订单
     * @return 商城售后订单集合
     */
    public List<UserAfterSaleOrder> selectUserAfterSaleOrderList(UserAfterSaleOrder userAfterSaleOrder);

    /**
     * 新增商城售后订单
     * 
     * @param userAfterSaleOrder 商城售后订单
     * @return 结果
     */
    public int insertUserAfterSaleOrder(UserAfterSaleOrder userAfterSaleOrder);

    /**
     * 修改商城售后订单
     * 
     * @param userAfterSaleOrder 商城售后订单
     * @return 结果
     */
    public int updateUserAfterSaleOrder(UserAfterSaleOrder userAfterSaleOrder);

    /**
     * 批量删除商城售后订单
     * 
     * @param userAfterSaleOrderIds 需要删除的商城售后订单主键集合
     * @return 结果
     */
    public int deleteUserAfterSaleOrderByUserAfterSaleOrderIds(Long[] userAfterSaleOrderIds);

    /**
     * 删除商城售后订单信息
     * 
     * @param userAfterSaleOrderId 商城售后订单主键
     * @return 结果
     */
    public int deleteUserAfterSaleOrderByUserAfterSaleOrderId(Long userAfterSaleOrderId);

    /**
     * 用户申请售后
     * @param userAfterSaleOrder
     * @return
     */
    public int userApplyAfterSale(UserAfterSaleOrder userAfterSaleOrder);

    /**
     * 售后订单审核
     * @param userAfterSaleOrder
     * @return
     */
    public int userAfterSaleOrderReview(UserAfterSaleOrder userAfterSaleOrder);
}
