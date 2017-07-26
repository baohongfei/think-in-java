package com.baohongfei.dp.strategy;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by terry on 26/07/17.
 */
public interface RedPacketsStrategy {

    static final int SCALE = 2;

    List<BigDecimal> splitRedPackets(BigDecimal totalMoney,int totalPeople);

    String getStrategyName();
}
