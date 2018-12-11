package com.ucar.team.seven.tea.common.enums;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Kyrie(lingyi.huang @ ucarinc.com)
 * @Date 2018/10/17 11:09
 * @Version 1.0
 * @Description 描述
 */
public enum CategoryDelStatus {

    NO_EXIST_GOODSANDSONCATEGORY("分类不存在商品或者子分类",1),
    EXIST_GOODS("分类下存在关联商品",2),
    EXIST_SONCATEGORY("分类下存在关联子分类",3),;

    private String status;
    private int value;

    CategoryDelStatus(String status, int value) {
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
