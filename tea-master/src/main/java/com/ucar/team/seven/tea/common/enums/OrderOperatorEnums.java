package com.ucar.team.seven.tea.common.enums;

public enum OrderOperatorEnums {
    ORDER("下单"),
    PAY_SUCCESS("支付成功"),
    SHIPED("商家发货"),
    RECEIPT_ORDER("确认收货"),
    ORDER_SUCCESS("订单完成"),
    APPLY_CANCEL("申请取消订单"),
    AUDIT_NOT_PASSED("审核不通过"),
    AUDIT_PASSED("审核通过"),
    REFUND_APPLICATION("退款申请"),
    REFUNDED("已退款"),
    ORDER_DELETE("订单删除"),
    UPDATE_DELETE("修改订单"),
    CANCELED_ORDER("订单已取消");

    /**
     *
     */
    private String operator;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    OrderOperatorEnums(String operator) {
        this.operator = operator;
    }
}
