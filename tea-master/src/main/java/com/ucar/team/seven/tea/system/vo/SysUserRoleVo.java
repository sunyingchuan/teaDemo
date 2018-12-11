package com.ucar.team.seven.tea.system.vo;

import com.ucar.team.seven.tea.common.entity.BaseEntity;
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
public class SysUserRoleVo extends BaseEntity {

    /**
     *  用户Id
     */
    Long userId;

    /**
     *  角色Id
     */
    Long roleId;

}
