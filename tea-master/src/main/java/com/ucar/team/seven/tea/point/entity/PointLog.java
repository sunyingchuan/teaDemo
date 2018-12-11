package com.ucar.team.seven.tea.point.entity;

import com.ucar.team.seven.tea.common.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author 徐楷翔(kaixiang.xu@ucarinc.com)
 * @Date 2018/10/11 14:18
 * @Version 1.0
 * @Description 积分记录实体类
 */
public class PointLog extends BaseEntity {

    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单金额
     */
    private BigDecimal money;

    /**
     * 积分
     */
    private Integer point;

    /**
     * 积分描述
     */
    private String description;

    /**
     * 订单编号
     */
    private String orderNumber;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}