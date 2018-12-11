/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @program: tea-demo
 * @Author minmin.liu(minmin.liu @ ucarinc.com)
 * @Date 2018-10-16 11:16
 * @Version 1.0
 * @Description 订单记录表
 */

package com.ucar.team.seven.tea.order.vo;

import lombok.Data;

import java.util.Date;
@Data
public class OrderRecordVo {
    /**
     * 操作记录时间
     */
    private Date operatorTime;
    /**
     * 订单操作描述
     */
    private String operator;
    /**
     * 订单操作备注
     */
    private String operatorRemark;
    /**
     * 操作者
     */
    private Long createEmp;

    private String modifyName;
    /**
     * 记录创建时间
     */
    private Date createTime;
    /**
     * 记录修改者
     */
    private Long modifyEmp;
    /**
     * 记录修改时间
     */
    private Date modifyTime;
}
