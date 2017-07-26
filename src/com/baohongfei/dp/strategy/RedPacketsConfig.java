package com.baohongfei.dp.strategy;

import java.math.BigDecimal;

/**
 * 红包算法的配置文件。
 * <p/>
 * Created by terry on 26/07/17.
 */
public final class RedPacketsConfig {
    private RedPacketsConfig() {
    }


    /**
     * 手气最佳红包的金额占总金额大小的比例，取值范围 [0.5,1)，默认是0.9
     * 注意点1 ： 当前算法不支持小于 0.5 的取值
     * 注意点2 ： 因为有最新金额 0.01 元限制，如果 0.03元红包被两人份，手气最佳天然占比66.67%，没法做到50%
     */
    public static final BigDecimal LUCKY_LIMIT = new BigDecimal("0.9");

    /**
     * 分红包时每人分到手的最大金额，默认是200元。
     */
    public static final BigDecimal MAX_AVG_MONEY = new BigDecimal(200);

    /**
     * 分红包时每人分到手的最小金额，默认是一分钱。
     */
    public static final BigDecimal MIN_AVG_MONEY = new BigDecimal("0.01");

    /**
     * 金额精确到小数点后的位数，默认是小数点后两位，精确到0.01元。
     */
    public static final int MONEY_SCALE = 2;

    /**
     * 最大红包个数
     */
    public static final int MAX_PEOPLE = 100;

    /**
     * 最小红包个数
     */
    public static final int MIN_PEOPLE = 1;

    /**
     * 除法默认精度
     */
    public static final int DEFAULT_SCALE = 10;

}
