package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.LangMgr;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface WebBackgroundMapper {


    /**
     * 首页报告
     * @return
     */
    @MapKey("flag")
    Map<String,Map<String, Object>> systemHomePageReport(Long sellerId);

    /**
     * 商户过去12个月订单数据
     * @param orderType 查询类型 0：销售订单数量 1：售后订单数量
     * @return
     */
    @MapKey("month")
    List<Map> pastMonthOrderData(@Param("orderType") Integer orderType,@Param("sellerId") Long sellerId);

}
