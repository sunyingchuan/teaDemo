/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @program: tea-demo
 * @Author minmin.liu(minmin.liu @ ucarinc.com)
 * @Date 2018-10-08 17:27
 * @Version 1.0
 * @Description 订单记录表实体类
 */

package com.ucar.team.seven.tea.order.entity;

import com.ucar.team.seven.tea.common.entity.BaseEntity;
import lombok.Data;

import java.util.Date;
@Data
public class OrderRecord extends BaseEntity {
    private Long id;
    /**
     * 订单号
     */
    private String orderNumber;
    /**
     * 订单记录时间
     */
    private Date operatorTime;
    /**
     * 操作的动作
     */
    private String operator;
    /**
     * 操作备注
     */
    private String operatorRemark;
    /**
     * 系统备注
     */
    private String remark;

}
