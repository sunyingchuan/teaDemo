/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @program: tea-demo
 * @Author minmin.liu(minmin.liu @ ucarinc.com)
 * @Date 2018-10-10 16:34
 * @Version 1.0
 * @Description GoodOrder关系查询的参数
 */

package com.ucar.team.seven.tea.order.vo.params;



import java.math.BigDecimal;

public class GoodsOrderParamVo {
    /**
     * 订单号
     */
    private String orderNumber;
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 订单购买商品的数量
     */
    private int quantity;

    /**
     * 商品单价
     */
    private BigDecimal unitPrice;

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
