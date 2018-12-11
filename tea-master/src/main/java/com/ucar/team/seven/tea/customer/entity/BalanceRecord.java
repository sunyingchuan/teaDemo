package com.ucar.team.seven.tea.customer.entity;

import com.ucar.team.seven.tea.common.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Description BalanceRecord 实体
 * @Author Grant(gang.zeng @ ucarinc.com)
 * @Version v1.0
 * @Date 2018/10/11 14:09
 */
@Data
public class BalanceRecord extends BaseEntity {

    /**
     * 客户ID
     */
    Long customerId;
    /**
     * 记录类型
     */
    Integer type;
    /**
     * 总金额
     */
    BigDecimal amount;
    /**
     * 订单号
     */
    String orderNumber;
    /**
     * 旧余额
     */
    BigDecimal balanceOld;
    /**
     * 新余额
     */
    BigDecimal balanceNew;
}
