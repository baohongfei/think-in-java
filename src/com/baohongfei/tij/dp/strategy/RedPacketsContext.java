package com.baohongfei.tij.dp.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 红包算法
 * <p/>
 * Created by terry on 26/07/17.
 */
public class RedPacketsContext {

    private RedPacketsStrategy redPacketsStrategy;

    public RedPacketsContext() {
        this.redPacketsStrategy = new RandomStrategy();
    }

    public RedPacketsContext(RedPacketsStrategy redPacketsStrategy) {
        this.redPacketsStrategy = redPacketsStrategy;
    }

    public void replaceStrategy(RedPacketsStrategy redPacketsStrategy) {
        this.redPacketsStrategy = redPacketsStrategy;
    }

    /**
     * 根据总金额、总人数分配红包
     *
     * @param totalMoney  总金额
     * @param totalPeople 总人数
     * @return 红包分配结果的 List
     */
    public List<BigDecimal> splitRedPackets(int totalMoney, int totalPeople) {
        List<BigDecimal> result = splitRedPackets(new BigDecimal(totalMoney), totalPeople);
        return result;
    }

    /**
     * 根据总金额、总人数分配红包
     *
     * @param totalMoney  总金额
     * @param totalPeople 总人数
     * @return 红包分配结果的 List
     */
    public List<BigDecimal> splitRedPackets(float totalMoney, int totalPeople) {
        List<BigDecimal> result = splitRedPackets(new BigDecimal(totalMoney + ""), totalPeople);
        return result;
    }

    /**
     * 根据总金额、总人数分配红包
     *
     * @param totalMoney  总金额
     * @param totalPeople 总人数
     * @return 红包分配结果的 List
     */
    List<BigDecimal> splitRedPackets(BigDecimal totalMoney, int totalPeople) {
        if (totalPeople < RedPacketsConfig.MIN_PEOPLE || totalPeople > RedPacketsConfig.MAX_PEOPLE) {
            throw new IllegalArgumentException("totalPeople out of range");
        }

        // 判断总金额数值不能超过小数点后两位
        if (totalMoney.multiply(new BigDecimal(1000)).intValue() % 10 != 0) {
            throw new IllegalArgumentException("illegal totalMoney");
        }

        BigDecimal avgMoney = totalMoney.divide(new BigDecimal(totalPeople), RedPacketsConfig.DEFAULT_SCALE, RoundingMode.DOWN);
        if (avgMoney.compareTo(RedPacketsConfig.MIN_AVG_MONEY) < 0 || avgMoney.compareTo(RedPacketsConfig.MAX_AVG_MONEY) > 0) {
            throw new IllegalArgumentException("totalMoney out of range");
        }

        List<BigDecimal> result;
        if (1 == totalPeople) {
            result = new ArrayList<>();
            result.add(totalMoney);
        } else {
            result = redPacketsStrategy.splitRedPackets(totalMoney, totalPeople);
        }
        return result;
    }

    public String getStrategyName() {
        return redPacketsStrategy.getStrategyName();
    }


}
