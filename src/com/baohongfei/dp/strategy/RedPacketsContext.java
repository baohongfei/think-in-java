package com.baohongfei.dp.strategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
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


    public List<BigDecimal> splitRedPackets(int totalMoney, int totalPeople) {
        List<BigDecimal> result = splitRedPackets(new BigDecimal(totalMoney), totalPeople);
        return result;
    }
    /**
     *
     * 根据总金额、总人数分配红包
     *
     * @param totalMoney 总金额
     * @param totalPeople 总人数
     * @return 红包分配结果的 List
     */
    public List<BigDecimal> splitRedPackets(float totalMoney, int totalPeople) {
        List<BigDecimal> result = splitRedPackets(new BigDecimal(totalMoney + ""), totalPeople);
        return result;
    }

    List<BigDecimal> splitRedPackets(BigDecimal totalMoney, int totalPeople) {
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
