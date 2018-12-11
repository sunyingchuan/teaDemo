package com.ucar.team.seven.tea.system.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @描述
 * @参数 $
 * @返回值 $
 * @创建人 yufeng.lin@ucarinc.com
 * @创建时间 $
 * @修改人和其它信息
 */
@Data
public class SysPermissionVo implements Serializable {


    private Long id;
    private String name;

    private Integer pid;

    private String parentName;

    private Integer type;

    private String url;

    private String code;

    private int sort;

    private String color;

    private String icon;

    private Boolean checked;

}
