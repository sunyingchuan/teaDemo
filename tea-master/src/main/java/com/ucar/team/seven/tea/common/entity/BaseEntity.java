package com.ucar.team.seven.tea.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/26 8:47
 * @Version 1.0
 * @Description 实体基类
 */

@Data
public class BaseEntity implements Serializable {
    /**
     * 主键，自增
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人id
     */
    private Long createEmp;

    /**
     * 修改人id
     */
    private Long modifyEmp;

    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     *备注
     **/
    private String remark;
}
