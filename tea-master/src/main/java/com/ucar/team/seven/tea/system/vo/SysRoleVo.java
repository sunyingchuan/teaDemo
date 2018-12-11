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
public class SysRoleVo implements Serializable {

    private Long Id;

    private String name;

    private String description;

    private Boolean selected;



}
