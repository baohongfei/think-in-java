package com.baohongfei.dp.strategy;

import java.math.BigDecimal;

/**
 *
 * 红包算法的配置文件。
 *
 * Created by terry on 26/07/17.
 */
public final class RedPacketsConfig {
    private RedPacketsConfig() {
    }


    /**
     * 手气最佳红包的金额占总金额大小的比例，取值范围 [0.5,1)，默认是0.9
     */
    public static final BigDecimal LUCKY_LIMIT = new BigDecimal("0.9");

    /**
     * 分红包时每人分到手的最小金额，默认是一分钱。
     */
    public static final BigDecimal MINIMUM_MONEY = new BigDecimal("0.01");

    /**
     * 金额精确到小数点后的位数，默认是小数点后两位，精确到0.01元。
     */
    public static final int MONEY_SCALE = 2;
}
