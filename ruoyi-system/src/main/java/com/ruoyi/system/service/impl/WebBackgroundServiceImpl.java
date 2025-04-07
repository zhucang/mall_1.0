package com.ruoyi.system.service.impl;


import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.UserInfo;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.PageUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.cache.CacheUtil;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.UserInfoMapper;
import com.ruoyi.system.mapper.UserRechargeMapper;
import com.ruoyi.system.mapper.UserWithdrawMapper;
import com.ruoyi.system.mapper.WebBackgroundMapper;
import com.ruoyi.system.service.*;
import com.ruoyi.system.utils.currencyExchangeRate.ExchangeRateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class WebBackgroundServiceImpl implements IWebBackgroundService {

    @Resource
    private WebBackgroundMapper webBackgroundMapper;

    /**
     * 首页报表
     */
    @Override
    public Map<String, Object> systemHomePageReport(Long sellerId){
        //报告map
        Map<String, Map<String, Object>> map = webBackgroundMapper.systemHomePageReport(sellerId);
        //总数据
        Map<String, Object> total = map.get("1");
        //当月数据
        Map<String, Object> currentMonth = map.get("2");
        //上月数据
        Map<String, Object> lastMonth = map.get("3");


        //总营业额
        BigDecimal salesAmountTotal = ((BigDecimal) total.get("salesAmount")).stripTrailingZeros();
        //总订单数
        Long salesVolumeTotal = (Long) total.get("salesVolume");
        //当月营业额
        BigDecimal salesAmountCurrentMonth = ((BigDecimal) currentMonth.get("salesAmount")).stripTrailingZeros();
        //当月订单数
        Long salesVolumeCurrentMonth =  (Long) currentMonth.get("salesVolume");
        //上月营业额
        BigDecimal salesAmountLastMonth = ((BigDecimal) lastMonth.get("salesAmount")).stripTrailingZeros();
        //上月订单数
        Long salesVolumeLastMonth = (Long) lastMonth.get("salesVolume");

//        2025年3月 销售订单占比
//        成交率
//        售后率
//        待付款率
//        退款订单数量


        //过去12个月的销售订单数量
        //过去12个月的退款订单数量
        List<Map> list1 = webBackgroundMapper.pastMonthOrderData(0,sellerId);
        List<Map> list2 = webBackgroundMapper.pastMonthOrderData(1,sellerId);
        for (int i = 0; i < list1.size(); i++) {
            list1.get(i).put("salesVolume",list1.get(i).get("volume"));
            list1.get(i).put("refundVolume",list2.get(i).get("volume"));
            list1.get(i).remove("volume");
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("salesAmountTotal",salesAmountTotal);
        resultMap.put("salesVolumeTotal",salesVolumeTotal);
        resultMap.put("salesAmountCurrentMonth",salesAmountCurrentMonth);
        resultMap.put("salesVolumeCurrentMonth",salesVolumeCurrentMonth);
        resultMap.put("salesAmountLastMonth",salesAmountLastMonth);
        resultMap.put("salesVolumeLastMonth",salesVolumeLastMonth);
        resultMap.put("pastMonthData",list1);
        return resultMap;
    }
}
