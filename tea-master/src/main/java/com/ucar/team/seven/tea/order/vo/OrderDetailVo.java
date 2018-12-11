/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @program: tea-demo
 * @Author minmin.liu(minmin.liu @ ucarinc.com)
 * @Date 2018-10-16 11:20
 * @Version 1.0
 * @Description 订单详情返回参数
 */

package com.ucar.team.seven.tea.order.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDetailVo {
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
     * todo:保存商品关系对象
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

    /**
     * 订单记录集合
     */
    private List<OrderRecordVo> orderRecordVos;
}
