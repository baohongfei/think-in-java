package com.baohongfei.dp.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by terry on 26/07/17.
 */
public class AverageStrategy implements RedPacketsStrategy {

    private static final String strategyName = "Average Strategy";


    @Override
    public List<BigDecimal> splitRedPackets(BigDecimal totalMoney, int totalPeople) {
        List<BigDecimal> result = new ArrayList<>();
        BigDecimal averageMoney = totalMoney.divide(new BigDecimal(totalPeople), SCALE, RoundingMode.DOWN);
        BigDecimal lastMoney = totalMoney.subtract(averageMoney.multiply(new BigDecimal(totalPeople - 1)));
        for (int i = 0; i < totalPeople - 1; i++) {
            result.add(averageMoney);
        }
        result.add(lastMoney);
        Collections.shuffle(result);
        return result;

    }

    @Override
    public String getStrategyName() {
        return strategyName;
    }
}
