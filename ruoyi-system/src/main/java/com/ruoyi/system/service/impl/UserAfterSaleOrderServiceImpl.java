package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.constant.HintConstants;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.entity.UserAmount;
import com.ruoyi.common.exception.LangException;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.CodeUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.UserBillDetail;
import com.ruoyi.system.domain.UserShoppingOrder;
import com.ruoyi.system.domain.UserShoppingOrderDetail;
import com.ruoyi.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.UserAfterSaleOrderMapper;
import com.ruoyi.system.domain.UserAfterSaleOrder;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 商城售后订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
@Service
public class UserAfterSaleOrderServiceImpl implements IUserAfterSaleOrderService 
{
    @Resource
    private UserAfterSaleOrderMapper userAfterSaleOrderMapper;

    @Autowired
    private IUserShoppingOrderService userShoppingOrderService;

    @Autowired
    private IUserAmountService userAmountService;

    @Autowired
    private IUserBillDetailService userBillDetailService;

    @Autowired
    private IUserShoppingOrderDetailService userShoppingOrderDetailService;

    /**
     * 查询商城售后订单
     * 
     * @param userAfterSaleOrderId 商城售后订单主键
     * @return 商城售后订单
     */
    @Override
    public UserAfterSaleOrder selectUserAfterSaleOrderByUserAfterSaleOrderId(Long userAfterSaleOrderId)
    {
        return userAfterSaleOrderMapper.selectUserAfterSaleOrderByUserAfterSaleOrderId(userAfterSaleOrderId);
    }

    /**
     * 查询商城售后订单列表
     * 
     * @param userAfterSaleOrder 商城售后订单
     * @return 商城售后订单
     */
    @Override
    public List<UserAfterSaleOrder> selectUserAfterSaleOrderList(UserAfterSaleOrder userAfterSaleOrder)
    {
        return userAfterSaleOrderMapper.selectUserAfterSaleOrderList(userAfterSaleOrder);
    }

    /**
     * 新增商城售后订单
     * 
     * @param userAfterSaleOrder 商城售后订单
     * @return 结果
     */
    @Override
    public int insertUserAfterSaleOrder(UserAfterSaleOrder userAfterSaleOrder)
    {
        userAfterSaleOrder.setCreateTime(DateUtils.getNowDate());
        return userAfterSaleOrderMapper.insertUserAfterSaleOrder(userAfterSaleOrder);
    }

    /**
     * 修改商城售后订单
     * 
     * @param userAfterSaleOrder 商城售后订单
     * @return 结果
     */
    @Override
    public int updateUserAfterSaleOrder(UserAfterSaleOrder userAfterSaleOrder)
    {
        userAfterSaleOrder.setUpdateTime(DateUtils.getNowDate());
        return userAfterSaleOrderMapper.updateUserAfterSaleOrder(userAfterSaleOrder);
    }

    /**
     * 批量删除商城售后订单
     * 
     * @param userAfterSaleOrderIds 需要删除的商城售后订单主键
     * @return 结果
     */
    @Override
    public int deleteUserAfterSaleOrderByUserAfterSaleOrderIds(Long[] userAfterSaleOrderIds)
    {
        return userAfterSaleOrderMapper.deleteUserAfterSaleOrderByUserAfterSaleOrderIds(userAfterSaleOrderIds);
    }

    /**
     * 删除商城售后订单信息
     * 
     * @param userAfterSaleOrderId 商城售后订单主键
     * @return 结果
     */
    @Override
    public int deleteUserAfterSaleOrderByUserAfterSaleOrderId(Long userAfterSaleOrderId)
    {
        return userAfterSaleOrderMapper.deleteUserAfterSaleOrderByUserAfterSaleOrderId(userAfterSaleOrderId);
    }

    /**
     * 用户申请售后
     * @param userAfterSaleOrder
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int userApplyAfterSale(UserAfterSaleOrder userAfterSaleOrder){
        //用户ID
        Long userId = SecurityUtils.getUserId();
        //用户购物订单明细ID
        Long userShoppingOrderDetailId = userAfterSaleOrder.getUserShoppingOrderDetailId();
        //用户购物订单明细信息
        UserShoppingOrderDetail userShoppingOrderDetail = userShoppingOrderDetailService.selectUserShoppingOrderDetailById(userShoppingOrderDetailId);
        if (userShoppingOrderDetail == null){
            throw new LangException(HintConstants.SYSTEM_BUSY,"获取用户购物订单明细信息异常");
        }
        //校验用户信息
        if (!userId.equals(userShoppingOrderDetail.getUserId())){
            throw new ServiceException("校验用户信息异常", HttpStatus.UNAUTHORIZED);
        }
        //订单状态 0：待付款 1：待发货 2：待收货 3：已取消 4：退款/售后 6：交易成功 7：交易关闭
        Integer detailOrderStatus = userShoppingOrderDetail.getOrderStatus();
        //售后状态 0：未售后 1：待审核 2：售后中 3：已拒绝 4：已退款 5：用户撤销
        Integer afterSaleStatus = userShoppingOrderDetail.getAfterSaleStatus();
        //验证订单状态
        if (detailOrderStatus.equals(0)){
            throw new LangException(HintConstants.SYSTEM_BUSY,"订单还未付款");
        }else if (detailOrderStatus.equals(3)){
            throw new LangException(HintConstants.SYSTEM_BUSY,"订单已取消");
        }else if (detailOrderStatus.equals(4)){
            if (afterSaleStatus.equals(1)){
                throw new LangException(HintConstants.SYSTEM_BUSY,"订单售后中，无需重复申请售后");
            }else if (afterSaleStatus.equals(2)){
                throw new LangException(HintConstants.SYSTEM_BUSY,"订单售后中，无需重复申请售后");
            }else if (afterSaleStatus.equals(3)){
                throw new LangException(HintConstants.SYSTEM_BUSY,"订单已退款成功，无法申请售后");
            }
        }else if (detailOrderStatus.equals(7)){
            throw new LangException(HintConstants.SYSTEM_BUSY,"订单交易已关闭");
        }
        //用户购物订单信息
        UserShoppingOrder userShoppingOrder = userShoppingOrderService.selectUserShoppingOrderById(userShoppingOrderDetail.getUserShoppingOrderId());
        if (userShoppingOrder == null){
            throw new LangException(HintConstants.SYSTEM_BUSY,"获取用户购物订单信息异常");
        }

        //购物金额
        BigDecimal orderPrice = userShoppingOrderDetail.getOrderPrice();
        //如果还未发货，直接退款成功
        if (detailOrderStatus.equals(1)){
            //设置明细订单状态为已关闭
            userShoppingOrderDetail.setOrderStatus(7);
            //设置明细售后状态为退款成功
            userShoppingOrderDetail.setAfterSaleStatus(4);
            //检验是否还有其他明细订单
            UserShoppingOrderDetail userShoppingOrderDetailSearch = new UserShoppingOrderDetail();
            userShoppingOrderDetailSearch.setUserShoppingOrderId(userShoppingOrderDetail.getUserShoppingOrderId());
            List<UserShoppingOrderDetail> details = userShoppingOrderDetailService.selectUserShoppingOrderDetailList(userShoppingOrderDetailSearch);
            //当前还是待发货的订单明细
            details = details.stream().filter(a -> a.getOrderStatus().equals(1)).collect(Collectors.toList());
            //如果只剩当前的明细订单状态是已付款，设置主订单状态为已关闭
            if (details.size() == 1){
                if (!details.get(0).getId().equals(userShoppingOrderDetail.getId())){
                    throw new LangException(HintConstants.SYSTEM_BUSY,"校验订单状态异常");
                }
                userShoppingOrder.setOrderStatus(7);
            }else {
                //如果还有其他待发货的订单明细
                //重新统计订单金额
                userShoppingOrder.setOrderPrice(userShoppingOrder.getOrderPrice().subtract(orderPrice));
                userShoppingOrder.setOrderPriceBeforeDiscount(userShoppingOrder.getOrderPriceBeforeDiscount().subtract(orderPrice));
                userShoppingOrder.setOrderSystemPrice(userShoppingOrder.getOrderSystemPrice().subtract(userShoppingOrderDetail.getOrderSystemPrice()));
            }
            //用户钱包信息
            UserAmount userAmount = userAmountService.getUserAmount(userId, 3L);
            //用户余额变更前
            BigDecimal userAmountBefore = userAmount.getAmount();
            //用户余额变更前
            BigDecimal userAmountAfter = userAmountBefore.add(orderPrice);
            //更新用户余额
            userAmount.setAmount(userAmountAfter);
            int updateUserAmount = userAmountService.updateUserAmount(userAmount);
            if (updateUserAmount == 0){
                throw new LangException(HintConstants.SYSTEM_BUSY,"更新用户余额异常");
            }
            //插入流水明细记录
            UserBillDetail userBillDetail = new UserBillDetail();
            userBillDetail.setUserId(userId);
            userBillDetail.setDeType("用户购物订单退款");
            userBillDetail.setDeSummary("用户购物订单退款");
            userBillDetail.setOrderAmount(orderPrice);
            userBillDetail.setOrderTime(new Date());
            userBillDetail.setAmountBefore(userAmountBefore);
            userBillDetail.setAmountAfter(userAmountAfter);
            userBillDetail.setRelateOrderId(userShoppingOrderDetail.getId());
            userBillDetail.setOrderClass(78);
            userBillDetail.setCurrencyId(userAmount.getCurrencyId());
            int insertUserBillDetail = userBillDetailService.insertUserBillDetail(userBillDetail);
            if (insertUserBillDetail <= 0) {
                throw new LangException(HintConstants.SYSTEM_BUSY,"插入流水明细异常");
            }
        }else {
            //设置明细订单状态为退款/售后
            userShoppingOrderDetail.setOrderStatus(4);
            //设置明细售后状态为待审核
            userShoppingOrderDetail.setAfterSaleStatus(1);
            //设置主订单状态为退款/售后
            userShoppingOrder.setOrderStatus(4);
            //插入售后订单
            userAfterSaleOrder.setUserShoppingOrderId(userShoppingOrderDetail.getUserShoppingOrderId());
            userAfterSaleOrder.setUserAfterSaleOrderCode(CodeUtils.generateOrderCode(""));
            userAfterSaleOrder.setAfterSaleAmount(orderPrice);
            userAfterSaleOrder.setAfterSaleStatus(0);
            userAfterSaleOrder.setCreateTime(new Date());
            int insertUserAfterSaleOrder = userAfterSaleOrderMapper.insertUserAfterSaleOrder(userAfterSaleOrder);
            if (insertUserAfterSaleOrder == 0){
                throw new LangException(HintConstants.SYSTEM_BUSY,"更新用户购物订单信息异常");
            }
        }

        //更新用户购物订单信息
        int updateUserShoppingOrder = userShoppingOrderService.updateUserShoppingOrder(userShoppingOrder);
        if (updateUserShoppingOrder == 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"更新用户购物订单信息异常");
        }

        //更新用户购物订单明细信息
        int updateUserShoppingOrderDetail = userShoppingOrderDetailService.updateUserShoppingOrderDetail(userShoppingOrderDetail);
        if (updateUserShoppingOrderDetail == 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"更新用户购物订单明细信息异常");
        }
        return 1;
    }

    /**
     * 售后订单审核
     * @param userAfterSaleOrder
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int userAfterSaleOrderReview(UserAfterSaleOrder userAfterSaleOrder){
        //用户售后订单信息ID
        Long afterSaleOrderId = userAfterSaleOrder.getUserAfterSaleOrderId();
        if (afterSaleOrderId == null){
            throw new ServiceException("请选择需要审核的售后订单");
        }
        //售后状态 0：待审核 1：售后中 2：已拒绝 3：已退款 4：用户撤销
        Integer afterSaleStatus = userAfterSaleOrder.getAfterSaleStatus();
        //拒绝原因
        String rejectRefundReason = userAfterSaleOrder.getRejectRefundReason();
        //用户售后订单信息
        userAfterSaleOrder = userAfterSaleOrderMapper.selectUserAfterSaleOrderByUserAfterSaleOrderId(afterSaleOrderId);
        if (userAfterSaleOrder == null){
            throw new ServiceException("获取用户售后订单信息异常");
        }
//        //售后种类 0：退款退货 1：仅退款 2：换货
//        Integer afterSaleType = userAfterSaleOrder.getAfterSaleType();
//        //如果退货退款
//        if (afterSaleType.equals(1)){
//            if (!afterSaleStatus.equals(2) || !afterSaleStatus.equals(3)){
//                throw new ServiceException("请选择是否同意退款");
//            }
//        }
        //用户购物订单信息
        UserShoppingOrder userShoppingOrder = userShoppingOrderService.selectUserShoppingOrderById(userAfterSaleOrder.getUserShoppingOrderId());
        //售后订单原售后状态
        Integer afterSaleStatusBefore = userAfterSaleOrder.getAfterSaleStatus();
        if (afterSaleStatusBefore.equals(2) || afterSaleStatusBefore.equals(3) || afterSaleStatusBefore.equals(4)){
            throw new ServiceException("该售后订单无需审核");
        }
        //如果同意退款
        if (afterSaleStatus.equals(3)){
            //退钱
            //用户ID
            Long userId = userShoppingOrder.getUserId();
            //返还购物金额
            BigDecimal orderPrice = userShoppingOrder.getOrderPrice();
            //用户钱包信息
            UserAmount userAmount = userAmountService.getUserAmount(userId, 3L);
            //用户余额变更前
            BigDecimal userAmountBefore = userAmount.getAmount();
            //用户余额变更前
            BigDecimal userAmountAfter = userAmountBefore.add(orderPrice);
            //更新用户余额
            userAmount.setAmount(userAmountAfter);
            int updateUserAmount = userAmountService.updateUserAmount(userAmount);
            if (updateUserAmount == 0){
                throw new LangException(HintConstants.SYSTEM_BUSY,"更新用户余额异常");
            }
            //插入流水明细记录
            UserBillDetail userBillDetail = new UserBillDetail();
            userBillDetail.setUserId(userId);
            userBillDetail.setDeType("用户购物订单退款");
            userBillDetail.setDeSummary("用户购物订单退款");
            userBillDetail.setOrderAmount(orderPrice);
            userBillDetail.setOrderTime(new Date());
            userBillDetail.setAmountBefore(userAmountBefore);
            userBillDetail.setAmountAfter(userAmountAfter);
            userBillDetail.setRelateOrderId(userShoppingOrder.getId());
            userBillDetail.setOrderClass(78);
            userBillDetail.setCurrencyId(userAmount.getCurrencyId());
            int insertUserBillDetail = userBillDetailService.insertUserBillDetail(userBillDetail);
            if (insertUserBillDetail <= 0) {
                throw new ServiceException("插入流水明细异常");
            }
            userShoppingOrder.setRefundStatus(2);
        }else if (afterSaleStatus.equals(2)){
            //拒绝退款
            //填写拒绝退款原因
            userAfterSaleOrder.setRejectRefundReason(rejectRefundReason);
            userShoppingOrder.setRefundStatus(3);
        }
        //更新售后订单信息
        userAfterSaleOrder.setAfterSaleStatus(afterSaleStatus);
        int updateUserAfterSaleOrder = userAfterSaleOrderMapper.updateUserAfterSaleOrder(userAfterSaleOrder);
        if (updateUserAfterSaleOrder <= 0) {
            throw new ServiceException("更新用户售后订单信息异常");
        }
        //更新用户购物订单信息
        int updateUserShoppingOrder = userShoppingOrderService.updateUserShoppingOrder(userShoppingOrder);
        if (updateUserShoppingOrder <= 0) {
            throw new ServiceException("更新购物订单信息异常");
        }
        return 1;
    }
}
