package com.ucar.team.seven.tea.system.entity;

import com.ucar.team.seven.tea.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author yufeng.lim@ucarinc.com
 * @Description  权限类
 * @Date 11:14 2018/9/27
 * @Param 
 * @return 
 **/
@Data
public class SysPermission extends BaseEntity implements Serializable {


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

    //    @Transient
    private final List<SysPermission> children = new ArrayList<>();


}
