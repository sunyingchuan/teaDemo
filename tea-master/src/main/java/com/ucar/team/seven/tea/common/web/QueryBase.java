package com.ucar.team.seven.tea.common.web;

import lombok.Data;

import java.util.Date;

/**
 * @描述
 * @参数 $
 * @返回值 $
 * @创建人 yufeng.lin@ucarinc.com
 * @创建时间 $
 * @修改人和其它信息
 */
@Data
public class QueryBase {


    // 分页 每页返回数目 ,默认值
    private Integer pageLimit = 20 ;

    //分页起始行，默认值
    private Integer pageOffset = 0 ;

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
