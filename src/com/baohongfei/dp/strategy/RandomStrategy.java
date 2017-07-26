package com.baohongfei.dp.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by terry on 26/07/17.
 */
public class RandomStrategy implements RedPacketsStrategy {

    private static final String strategyName = "Random Strategy";


    private static final int RANGE = 2000;

    private static final BigDecimal LUCKY_LIMIT = new BigDecimal("0.9");

    @Override
    public List<BigDecimal> splitRedPackets(BigDecimal totalMoney, int totalPeople) {
        List<BigDecimal> result = new ArrayList<>();
        List<Integer> percentageNums = new ArrayList();
        for (int i = 0; i < totalPeople; i++) {
            int random = ThreadLocalRandom.current().nextInt(RANGE) + 1;
            System.out.println(random);
            percentageNums.add(random);
        }
        int percentageSum = 0;
        for (Integer percent : percentageNums) {
            percentageSum += percent;
        }

        Collections.sort(percentageNums);

        Integer maxPercent = percentageNums.get(totalPeople - 1);

        if (maxPercent > LUCKY_LIMIT.multiply(new BigDecimal(percentageSum)).intValue()) {
            int limitMaxPercent = 9 * (percentageSum - maxPercent);
            percentageNums.remove(totalPeople - 1);
            percentageNums.add(limitMaxPercent);
            percentageSum = percentageSum - maxPercent + limitMaxPercent;
        }

        BigDecimal splitMoney = new BigDecimal("0");
        for (int i=0;i<totalPeople-1;i++){
            BigDecimal currentMoney = totalMoney.multiply(new BigDecimal(percentageNums.get(i))).divide(new BigDecimal(percentageSum), SCALE, RoundingMode.DOWN);
            result.add(currentMoney);
            splitMoney=splitMoney.add(currentMoney);
        }
        result.add(totalMoney.subtract(splitMoney));
        Collections.shuffle(result);
        return result;
    }

    @Override
    public String getStrategyName() {
        return strategyName;
    }
}
