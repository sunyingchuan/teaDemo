package com.ucar.team.seven.tea.point.vo;

import com.ucar.team.seven.tea.common.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author 徐楷翔(kaixiang.xu@ucarinc.com)
 * @Date 2018/10/8 14:22
 * @Version 1.0
 * @Description 积分记录Vo类
 */
public class PointLogVo extends BaseEntity {
    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 客户名称
     */
    private String username;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 积分
     */
    private Integer point;

    /**
     * 描述
     */
    private String description;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 创建人名称
     */
    private String createEmpName;


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
        this.description = description;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreateEmpName() {
        return createEmpName;
    }

    public void setCreateEmpName(String createEmpName) {
        this.createEmpName = createEmpName;
    }
}
