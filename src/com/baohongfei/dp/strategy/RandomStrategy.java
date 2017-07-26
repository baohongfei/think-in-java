package com.baohongfei.dp.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 加权随机分配策略
 * 算法实现步骤：首先每个红包随机一个1到 RANDOM_RANGE 的数字作为自己的权值
 * 再检查如果最大的权值如果占比超过最大允许占比权值，则调整最大权值为最大允许占比权值
 * 然后按照权值分配红包
 * <p/>
 * Created by terry on 26/07/17.
 */
public class RandomStrategy implements RedPacketsStrategy {

    private static final String strategyName = "Random Strategy";

    /**
     * 每个红包随机一个1到 RANDOM_RANGE 的数字作为自己的权值
     */
    private static final int RANDOM_RANGE = 100;


    @Override
    public List<BigDecimal> splitRedPackets(BigDecimal totalMoney, int totalPeople) {
        List<BigDecimal> result = new ArrayList<>();

        // 给每一个红包计算一个权值,返回的权值数组是从小到大排列的
        List<BigDecimal> randomWeight = getRandomWeight(totalPeople);

        // 总权值
        BigDecimal totalWeight = new BigDecimal(0);

        for (BigDecimal weight : randomWeight) {
            totalWeight = totalWeight.add(weight);
        }

        // 防止因为精确度问题导致手气最佳红包超过比例
        Collections.reverse(randomWeight);

        // 累计分配的红包金额
        BigDecimal splitMoney = new BigDecimal(0);
        for (int i = 0; i < randomWeight.size() - 1; i++) {
            // 按权值分配金额
            BigDecimal currentMoney = totalMoney.multiply(randomWeight.get(i)).divide(totalWeight, RedPacketsConfig.MONEY_SCALE, RoundingMode.DOWN);
            currentMoney = ensureMinimum(currentMoney);
            result.add(currentMoney);
            splitMoney = splitMoney.add(currentMoney);
        }
        result.add(totalMoney.subtract(splitMoney));
        Collections.shuffle(result);
        return result;
    }

    /**
     * 给每个红包分配一个权值，按权值从小到大排列
     *
     * @param totalPeople 总人数
     * @return
     */
    private List<BigDecimal> getRandomWeight(int totalPeople) {
        List<BigDecimal> retRandomWeight = new ArrayList();
        for (int i = 0; i < totalPeople; i++) {
            int random = ThreadLocalRandom.current().nextInt(RANDOM_RANGE) + 1;
            retRandomWeight.add(new BigDecimal(random));
        }

        Collections.sort(retRandomWeight);
        ensureLuckyLimit(retRandomWeight);


        return retRandomWeight;
    }

    /**
     * 确保手气最佳红包的金额占总金额大小的比例，不大于 RedPacketsConfig.LUCKY_LIMIT
     *
     * @param allRandomWeight
     */
    private void ensureLuckyLimit(List<BigDecimal> allRandomWeight) {
        BigDecimal totalWeight = new BigDecimal(0);
        for (BigDecimal weight : allRandomWeight) {
            totalWeight = totalWeight.add(weight);
        }

        BigDecimal maxWeight = allRandomWeight.get(allRandomWeight.size() - 1);

        /**
         * maxWeightLimit 表示手气最佳红包的最大权值能是多少
         * maxWeightLimit 应该是多少？可以做如下推算
         * RedPacketsConfig.LUCKY_LIMIT = maxWeightLimit / ((totalWeight-maxWeight)+maxWeightLimit)
         * 由上面的公式可以推到出 maxWeightLimit = RedPacketsConfig.LUCKY_LIMIT / (1-RedPacketsConfig.LUCKY_LIMIT) *(totalWeight-maxWeight)
         */
        BigDecimal maxWeightLimit = RedPacketsConfig.LUCKY_LIMIT.divide(new BigDecimal(1).subtract(RedPacketsConfig.LUCKY_LIMIT), RedPacketsConfig.DEFAULT_SCALE, RoundingMode.DOWN).multiply(totalWeight.subtract(maxWeight));
        if (maxWeight.compareTo(maxWeightLimit) > 0) {
            allRandomWeight.remove(allRandomWeight.size() - 1);
            allRandomWeight.add(maxWeightLimit);
        }
    }

    /**
     * 确保每一个红包金额不小于 RedPacketsConfig.MINIMUM_MONEY 。
     *
     * @param eachRedPacketsMoney
     * @return
     */
    private BigDecimal ensureMinimum(BigDecimal eachRedPacketsMoney) {
        if (eachRedPacketsMoney.compareTo(RedPacketsConfig.MIN_AVG_MONEY) < 0) {
            eachRedPacketsMoney = RedPacketsConfig.MIN_AVG_MONEY;
        }
        return eachRedPacketsMoney;
    }

    @Override
    public String getStrategyName() {
        return strategyName;
    }
}
