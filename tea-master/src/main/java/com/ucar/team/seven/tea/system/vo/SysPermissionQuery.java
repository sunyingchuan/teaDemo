package com.ucar.team.seven.tea.system.vo;

import com.ucar.team.seven.tea.common.web.QueryBase;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysPermissionQuery extends QueryBase implements Serializable {

    private Long id;

    private String name;

    private Long pid;

    private String parentName;

    private Integer type;

    private String url;

    private String code;

    private int sort;

    private String color;

    private String icon;

    //    @Transient
    private Boolean checked;


    // 分页 每页返回数目 ,默认值
    private Integer pageLimit = 20 ;

    //分页起始行，默认值
    private Integer pageOffset = 0 ;



}
