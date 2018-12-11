/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @program: tea-demo
 * @Author minmin.liu(minmin.liu @ ucarinc.com)
 * @Date 2018-10-10 18:07
 * @Version 1.0
 * @Description 商品订单关系实体
 */

package com.ucar.team.seven.tea.order.entity;

import com.ucar.team.seven.tea.common.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsOrder extends BaseEntity {
    /**
     * id
     */
    private Long id;
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 购买商品id
     */
    private Long goodsId;
    /**
     * 购买商品的数量
     */
    private Integer quantity;
    /**
     * 商品单价
     */
    private BigDecimal unitPrice;
    /**
     * 商品总价
     */
    private BigDecimal totalPrice;
    /**
     * 备注
     */
    private String remark;
}
