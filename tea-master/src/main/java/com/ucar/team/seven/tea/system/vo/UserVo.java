package com.ucar.team.seven.tea.system.vo;

import com.ucar.team.seven.tea.system.entity.SysUser;
import lombok.Data;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Description class description
 * @Author gang.zeng @ ucarinc.com
 * @Version v1.0
 * @Date 2018/10/12 13:50
 */
@Data
public class UserVo extends SysUser {

    /**
     * 创建者
     */
    String createEmpName;

    /**
     * 修改者
     */
    String modifyEmpName;

    /**
     * 角色ID
     */
    Long roleId;

    /**
     * 角色名
     */
    String roleName;

}
