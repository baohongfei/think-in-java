package com.baohongfei.tij.dp.strategy;

import java.math.BigDecimal;
import java.time.Clock;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * 测试用例
 *
 * Created by terry on 26/07/17.
 */
public class TestCase {

    public static void main(String[] args) {
        testCase01();
        testCase02();
        testCase03();

    }

    private static void testCase01() {
        RedPacketsContext context = new RedPacketsContext();
        float totalMoney = 100F;
        int totalPeople = 10;
        assert checkResult(totalMoney, totalPeople, context);
    }

    private static void testCase02() {
        RedPacketsContext context = new RedPacketsContext();
        float totalMoney = 10.111F;
        int totalPeople = 10;
        try {
            checkResult(totalMoney, totalPeople, context);
        } catch (Exception e) {
            assert e.getMessage().equals("illegal totalMoney");
        }
    }

    private static void testCase03() {
        RedPacketsContext context = new RedPacketsContext();
        Random rand = new Random();
        final Clock clockStart = Clock.systemUTC();
        long start = clockStart.millis();

        for (int i = 0; i < 1000000; i++) {
            int totalMoney = rand.nextInt(200) + 1;
            int totalPeople = rand.nextInt(100) + 1;
            checkResult(totalMoney, totalPeople, context);
        }

        final Clock clockEnd = Clock.systemUTC();
        long end = clockEnd.millis();

        System.out.println((end-start)/1000 + " Seconds passed");

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
