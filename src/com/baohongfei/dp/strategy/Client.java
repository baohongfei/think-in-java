package com.baohongfei.dp.strategy;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by terry on 26/07/17.
 */
public class Client {

    public static void main(String[] args) {

        float totalMoney = 100F;
        int totalPeople = 10;

        RedPacketsStrategy averageStrategy = new AverageStrategy();
        RedPacketsContext context = new RedPacketsContext(averageStrategy);

        List<BigDecimal> averageStrategyResult = context.splitRedPackets(totalMoney,totalPeople);
        System.out.println(totalPeople+"人,分"+totalMoney+"元,使用"+context.getStrategyName()+"策略,结果是"+averageStrategyResult);

        RandomStrategy randomStrategy = new RandomStrategy();
        context.replaceStrategy(randomStrategy);

        List<BigDecimal> randomStrategyResult = context.splitRedPackets(totalMoney,totalPeople);
        System.out.println(totalPeople+"人,分"+totalMoney+"元,使用"+context.getStrategyName()+"策略,结果是"+randomStrategyResult);


    }
}
