package com.ruoyi.web.controller.task;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.CodeUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.UserReceiveAddress;
import com.ruoyi.system.domain.UserShoppingOrder;
import com.ruoyi.system.domain.UserShoppingOrderDetail;
import com.ruoyi.system.mapper.UserShoppingOrderDetailMapper;
import com.ruoyi.system.service.IUserShoppingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 下单机器人定时任务
 *
 * @author ruoyi
 * @date 2022-11-14
 */
@Component
public class OrderRobotTask {

    @Resource
    private UserShoppingOrderDetailMapper userShoppingOrderDetailMapper;

    @Autowired
    private IUserShoppingOrderService userShoppingOrderService;

    /**
     * 执行机器人下单任务
     */
    @Scheduled(cron = "0 0/3 0-20 * * ?")
    void task(){
        //获取随机时间
        long time = RandomUtil.randomInt(60*60*4);
        //执行器
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(()->{
            try {
                doTask();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        },time, TimeUnit.SECONDS);
        executorService.shutdown();
    }

    @Transactional(rollbackFor = Exception.class)
    public void doTask(){
        //随机获取合适的机器人和商品
        UserShoppingOrderDetail userShoppingOrderDetail;
        while (true){
            userShoppingOrderDetail = userShoppingOrderDetailMapper.generateRobotOrder();
            if (userShoppingOrderDetail == null){
                return;
            }else {
                //检验订单是否合理
                //如果不合理，重新抽取订单
                break;
            }
        }
        //购买数量
        Integer buyNum = userShoppingOrderDetail.getBuyNum();
        userShoppingOrderDetail.setOrderPrice(new BigDecimal(buyNum).multiply(userShoppingOrderDetail.getGoodsSinglePrice()).setScale(Constants.BIGDECIMAL_SCALE,Constants.BIGDECIMAL_ROUNDINGMODE));
        userShoppingOrderDetail.setOrderSystemPrice(new BigDecimal(buyNum).multiply(userShoppingOrderDetail.getGoodsSystemSinglePrice()).setScale(Constants.BIGDECIMAL_SCALE,Constants.BIGDECIMAL_ROUNDINGMODE));
        userShoppingOrderDetail.setCreateTime(new Date());
        //购物订单信息
        UserShoppingOrder userShoppingOrder = new UserShoppingOrder();
        //订单号
        String orderCode = CodeUtils.generateOrderCode("");
        userShoppingOrder.setOrderCode(orderCode);
        userShoppingOrder.setUserId(userShoppingOrderDetail.getUserId());
        userShoppingOrder.setOrderPrice(userShoppingOrderDetail.getOrderPrice().add(userShoppingOrderDetail.getFreightAmount()));
        userShoppingOrder.setOrderSystemPrice(userShoppingOrderDetail.getOrderSystemPrice());
        userShoppingOrder.setFreightAmount(userShoppingOrderDetail.getFreightAmount());
        UserReceiveAddress userReceiveAddress = new UserReceiveAddress();
        userReceiveAddress.setCountryName("American");
        userReceiveAddress.setReceiverProvince("Pennsylvania");
        userReceiveAddress.setReceiverCity("Philadelphia");
        userReceiveAddress.setReceiverName("Lawrence C Gresham");
        userReceiveAddress.setReceiverPhone("215-984-0517");
        userReceiveAddress.setReceiverAddress("Pennsylvania Philadelphia 476 Hiddenview Drive");
        userShoppingOrder.setShippingAddress(JSONObject.toJSONString(userReceiveAddress));
        userShoppingOrder.setShopInfoId(userShoppingOrderDetail.getShopInfoId());
        userShoppingOrder.setSellerId(userShoppingOrderDetail.getSellerId());
        userShoppingOrder.setOrderStatus(1);
        userShoppingOrder.setPayTime(new Date());
        userShoppingOrder.setShippingTime(null);
        userShoppingOrder.setFinishTime(null);
        userShoppingOrder.setCreateTime(new Date());
        int insertUserShoppingOrder = userShoppingOrderService.insertUserShoppingOrder(userShoppingOrder);
        if (insertUserShoppingOrder <= 0){
            throw new ServiceException();
        }
        userShoppingOrderDetail.setUserShoppingOrderId(userShoppingOrder.getId());
        List<UserShoppingOrderDetail> userShoppingOrderDetails = new ArrayList<>();
        userShoppingOrderDetails.add(userShoppingOrderDetail);
        int insertUserShoppingOrderDetail = userShoppingOrderDetailMapper.insertUserShoppingOrderDetails(userShoppingOrderDetails);
        if (userShoppingOrderDetails.size() != insertUserShoppingOrderDetail){
            throw new ServiceException();
        }
    }


    /**
     * 机器人订单自动确认收货
     */
    @Scheduled(cron = "0 0 * * * ?")
    void task2(){
        //获取发货时间超过24小时的机器人订单
        UserShoppingOrder userShoppingOrder = new UserShoppingOrder();
        userShoppingOrder.setShippingTime(DateUtils.getDateBeforeOrAfterDate(new Date(), Calendar.DAY_OF_YEAR,-1));
        userShoppingOrder.setAccountType(2);
        userShoppingOrder.setOrderStatus(2);
        List<UserShoppingOrder> userShoppingOrders = userShoppingOrderService.selectUserShoppingOrderList(userShoppingOrder);
        if (userShoppingOrders.size() == 0){
            return;
        }
        //执行器
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        for (int i = 0; i < userShoppingOrders.size(); i++) {
            //机器人订单
            UserShoppingOrder vo = userShoppingOrders.get(i);
            //获取随机时间
            long time = RandomUtil.randomInt(60*60);
            executorService.schedule(()->{
                try {
                    userShoppingOrderService.userConfirmReceipt(vo);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            },time, TimeUnit.SECONDS);
        }
        executorService.shutdown();
    }
}
