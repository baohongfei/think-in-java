package com.baohongfei.dp.strategy;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Created by terry on 26/07/17.
 */
public class TestCase {

    public static void main(String[] args) {
        testCase01();

    }

    private static void testCase01() {
        RedPacketsContext context = new RedPacketsContext();
        float totalMoney = 100F;
        int totalPeople = 10;
        assert checkResult(totalMoney, totalPeople, context);
    }

    private static boolean checkResult(float totalMoney, int totalPeople, RedPacketsContext context) {
        List<BigDecimal> result = context.splitRedPackets(totalMoney, totalPeople);
        BigDecimal totalMoneyInResult = new BigDecimal(0);
        BigDecimal totalMoneyDecimal = new BigDecimal(totalMoney + "");
        for (BigDecimal money : result) {
            totalMoneyInResult = totalMoneyInResult.add(money);
        }
        if (totalMoneyInResult.compareTo(totalMoneyDecimal) != 0) {
            return false;
        }
        Collections.sort(result);
        BigDecimal maxMoney = result.get(result.size() - 1);

        if (maxMoney.compareTo(totalMoneyDecimal.multiply(RedPacketsConfig.LUCKY_LIMIT)) > 0) {
            return false;
        }
        return true;
    }
}
