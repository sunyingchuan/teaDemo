package com.ucar.team.seven.tea.common.enums;

public enum OrderStatusEnums {
    UNPAID("未支付",0),
    PENDING_SHIPMENT("待发货",1),
    SHIPED("已发货",2),
    ORDER_COMPLETED("订单完成",3),
    APPLY_FOR_CANCEL("申请取消订单",4),
    PENDING_REFUND("待退款",5),
    CANCELED("已取消",6);

    private Integer value;
    private String status;


    OrderStatusEnums(String status, Integer value) {
        this.status = status;
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public int getValue() {
        return value;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
