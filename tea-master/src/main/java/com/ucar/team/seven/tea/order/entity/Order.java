/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @program: tea-demo
 * @description: 订单实体类
 * @author: minmin.liu(minmin.liu @ ucarinc.com)
 * @Version 1.0
 * @create: 2018-10-08 14:44
 **/
package com.ucar.team.seven.tea.order.entity;

import com.ucar.team.seven.tea.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "订单对象",description = "这是订单对象")
@Data

public class Order extends BaseEntity {
    /**
     * 订单id
     */
    private Long id;
    /**
     * 订单号
     */
    @ApiModelProperty(hidden = true)
    private String orderNumber;
    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id",name = "customerId",example = "1",required =true )
    private Long customerId;
    /**
     * 订单总价
     */
    @ApiModelProperty(value = "订单总价",name = "price",example = "100",required =true )
    private BigDecimal price;
    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态",name = "status",example = "1",required =true )
    private Integer status;
    /**
     * 订单收货地址
     */
    @ApiModelProperty(value = "订单收货地址",name = "address",example = "福建省厦门市思明区特房波特曼财富中心",required =true )
    private String address;
    /**
     *订单收货人姓名
     */
    @ApiModelProperty(value = "订单收货人姓名",name = "receiptName",example = "myflower",required =true )
    private String receiptName;
    /**
     *订单收货人电话
     */
    @ApiModelProperty(value = "订单收货人电话",name = "receiptPhone",example = "15773313613",required =true )
    private String receiptPhone;
    /**
     *订单备注信息
     */
    @ApiModelProperty(value = "订单备注信息",name = "orderRemark",example = "",required =true )
    private String orderRemark;
    /**
     *订单付款时间
     */
    @ApiModelProperty(hidden = true)
    private Date payTime;
    /**
     *订单发货时间
     */
    @ApiModelProperty(hidden = true)
    private Date shipTime;
    /**
     *订单收货时间
     */
    @ApiModelProperty(hidden = true)
    private Date receiptTime;
    /**
     *订单是否删除 0、删除，1、未删除
     */
    @ApiModelProperty(hidden = true)
    private Integer isDelete;

    private String remark;


}
