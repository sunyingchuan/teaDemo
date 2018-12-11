/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @program: tea-demo
 * @Author minmin.liu(minmin.liu @ ucarinc.com)
 * @Date 2018-10-08 16:22
 * @Version 1.0
 * @Description 订单查询返回结果vo
 */

package com.ucar.team.seven.tea.order.vo;

import com.ucar.team.seven.tea.common.entity.BaseEntity;
import com.ucar.team.seven.tea.goods.entity.Goods;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data
public class OrderVo extends BaseEntity {
   private Long orderId;
    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 客户昵称
     */
    private String userName;


    /**
     *
     * 购买商品
     */
    private List<GoodsOrderVo> goodsOrderVos;


    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     *
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 订单总价
     */
    private BigDecimal price;
    /**
     * 收货地址
     */
    private String address;
    /**
     * 收货人
     */
    private String receiptName;
    /**
     * 收货人电话
     */
    private String receiptPhone;
    /**
     * 订单备注
     */
    private String orderRemark;


}
