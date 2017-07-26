package com.baohongfei.dp.strategy;

import java.math.BigDecimal;
import java.util.List;

/**
 * 策略类接口
 *
 * Created by terry on 26/07/17.
 */
public interface RedPacketsStrategy {


    /**
     * 根据总金额、总人数分配红包
     *
     * @param totalMoney  总金额
     * @param totalPeople 总人数
     * @return 红包分配结果的 List
     */
    List<BigDecimal> splitRedPackets(BigDecimal totalMoney, int totalPeople);

    String getStrategyName();
}
