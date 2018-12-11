package com.ucar.team.seven.tea.system.entity;

import com.ucar.team.seven.tea.common.entity.BaseEntity;
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
public class SysRole extends BaseEntity implements Serializable {


    private String name;

    private String description;


}
