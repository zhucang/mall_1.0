package com.ruoyi.system.utils;

import com.ruoyi.common.core.domain.ticker.TickerInfo;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 产品行情报价工具
 */
@Component
public class ProductQuoteUtils {
    private static RedisCache redisCache = SpringUtils.getBean(RedisCache.class);

    /**
     * 获取加密货币的简单报价（现价+涨跌幅）
     * @param productCodes 加密货币代码，多个用逗号隔开  例： X:TRXUSD,X:BTCUSD,X:CNYUSD
     * @param isGetDetail 是否获取详细报价
     * @return
     */
    public static Map<String, TickerInfo> getCryptoCurrencyQuote(String productCodes,Boolean isGetDetail){
        Map<String, TickerInfo> map = new HashMap<>();
        if (StringUtils.isEmpty(productCodes)){
            return map;
        }
        //产品代码集合
        List<String> productCodeList = Arrays.stream(productCodes.split(",")).distinct().collect(Collectors.toList());
        //获取第三方行情数据
        //行情map
        Map<String, TickerInfo> tickerInfoMap = MiddleQuoteUtil.getTickerInfosByCache(productCodeList.stream().collect(Collectors.joining(",")));
        //汇总行情map
        map.putAll(tickerInfoMap);
        return map;
    }

    /**
     * 获取外汇的简单报价（现价+涨跌幅）
     * @param productCodes 外汇代码，多个用逗号隔开  例： C:AEDUSD,C:ALLUSD,C:CNYUSD
     * @return
     */
    public static Map<String, TickerInfo> getForexQuote(String productCodes){
        Map<String, TickerInfo> map = new HashMap<>();
        if (StringUtils.isEmpty(productCodes)){
            return map;
        }
        //产品代码集合
        List<String> productCodeList = Arrays.stream(productCodes.split(",")).distinct().collect(Collectors.toList());
        //获取第三方行情数据
        //行情map
        Map<String, TickerInfo> tickerInfoMap = MiddleQuoteUtil.getTickerInfosByCache(productCodeList.stream().collect(Collectors.joining(",")));
        //汇总行情map
        map.putAll(tickerInfoMap);
        return map;
    }
}
