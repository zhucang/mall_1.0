package com.ruoyi.common.utils;


import java.util.Random;

/**
 * CodeUtils
 */
public class CodeUtils {

    /**
     * 生成订单号
     * @return
     */
    public static String generateOrderCode(String prefix) {
        Random random = new Random();
        Integer number = random.nextInt(900) + 100;
        return (StringUtils.isNotEmpty(prefix)?prefix:"") + System.currentTimeMillis() + number;
    }

    /**
     * 生成邀请码
     * @return
     */
    public static String generateInviteCode() {
        Random random = new Random();
        Integer number = Integer.valueOf(random.nextInt(900000) + 100000);
        return String.valueOf(number);
    }



}
