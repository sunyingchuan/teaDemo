/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @program: tea-demo
 * @Author minmin.liu(minmin.liu @ ucarinc.com)
 * @Date 2018-10-16 11:01
 * @Version 1.0
 * @Description 订单删除
 */

package com.ucar.team.seven.tea.common.enums;

public enum OrderDeleteEnums {
    ORDER_DELETE("订单删除",0),
    ORDER_NOT_DELETE("订单未删除",1);
    OrderDeleteEnums(String delete, Integer value) {
        this.delete = delete;
        this.value = value;
    }

    private String delete;
    private Integer value;

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
