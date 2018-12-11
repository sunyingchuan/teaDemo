/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @program: tea-demo
 * @Author minmin.liu(minmin.liu @ ucarinc.com)
 * @Date 2018-10-10 16:27
 * @Version 1.0
 * @Description 商品订单关系返回vo
 */

package com.ucar.team.seven.tea.order.vo;

import com.ucar.team.seven.tea.common.entity.BaseEntity;
import com.ucar.team.seven.tea.goods.vo.GoodsVo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GoodsOrderVo extends BaseEntity {
    /**
     * 商品vo有价格
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品购买的数量
     */
    private Integer quantity;

    /**
     * 订单单件商品的总价
     */
    private BigDecimal totalPrice;

    /**
     * 商品图片链接
     */
    private String imageUrl;
}
