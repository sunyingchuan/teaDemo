/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @program: tea-demo
 * @Author minmin.liu(minmin.liu @ ucarinc.com)
 * @Date 2018-10-08 16:30
 * @Version 1.0
 * @Description 订单查询参数
 */

package com.ucar.team.seven.tea.order.vo.params;

import com.ucar.team.seven.tea.common.web.Page;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderParamVo extends Page {

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 客户id
     */
    private Long userId;

    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 客户名
     */
    private String userName;
    /**
     * 商品名
     */
    private String goodsName;
    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 修改者id
     */
    private Long modifyId;

    /**
     * 订单总价
     */
    private BigDecimal price;
}
