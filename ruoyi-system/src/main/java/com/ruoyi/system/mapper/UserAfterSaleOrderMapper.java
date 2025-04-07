package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.UserAfterSaleOrder;

/**
 * 商城售后订单Mapper接口
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public interface UserAfterSaleOrderMapper 
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
     * 删除商城售后订单
     * 
     * @param userAfterSaleOrderId 商城售后订单主键
     * @return 结果
     */
    public int deleteUserAfterSaleOrderByUserAfterSaleOrderId(Long userAfterSaleOrderId);

    /**
     * 批量删除商城售后订单
     * 
     * @param userAfterSaleOrderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserAfterSaleOrderByUserAfterSaleOrderIds(Long[] userAfterSaleOrderIds);
}
