package com.ucar.team.seven.tea.common.enums;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Kyrie(lingyi.huang @ ucarinc.com)
 * @Date 2018/10/11 13:43
 * @Version 1.0
 * @Description 商品状态枚举类
 */
public enum GoodsStatus {
    INVALID("下架",0),
    VALID("上架",1);

    private String status;
    private int value;

    GoodsStatus(String status, int value) {
        this.status = status;
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
