package com.ucar.team.seven.tea.system.vo;

import com.ucar.team.seven.tea.common.web.QueryBase;
import lombok.Data;

import java.io.Serializable;
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
public class SysRoleQuery extends QueryBase implements Serializable {
    /**
     * 主键，自增
     */
    private Long id;

    //要查询以及插入的角色名
    private String name;

    //要查询以及插入的描述
    private String description;


    // 分页 每页返回数目 ,默认值
    private Integer pageLimit = 10 ;

    //分页起始行，默认值
    private Integer pageOffset = 0 ;



}
