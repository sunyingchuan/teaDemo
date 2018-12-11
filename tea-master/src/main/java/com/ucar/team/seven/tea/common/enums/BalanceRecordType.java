package com.ucar.team.seven.tea.common.enums;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Description 余额账单记录类型
 * @Author Grant(gang.zeng @ ucarinc.com)
 * @Version v1.0
 * @Date 2018/10/19 14:37
 */
public enum BalanceRecordType {
    /***
     * 记录类型:1-充值;2-消费;3-退款;
     */
    RECHARGE("充值",1),
    EXPEND("消费",2),
    REFUND("退款",3);

    private String type;
    private Integer value;

    BalanceRecordType(String s, Integer i) {
        this.type = s;
        this.value = i;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
