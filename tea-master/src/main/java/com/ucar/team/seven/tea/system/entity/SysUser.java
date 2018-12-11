/*
 * Copyright 2018 UCAR Inc. All rights reserved.
 */

package com.ucar.team.seven.tea.system.entity;

import com.ucar.team.seven.tea.common.entity.BaseEntity;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/26 8:52
 * @Version 1.0
 * @Description 用户实体类
 */
@Data
public class SysUser extends BaseEntity {

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 状态  0-禁用  1-启用
     */
    private Integer status;
    /**
     *
     */
    private String salt;

    /**
     * 性别：0-女，1-男，2-未选择
     */
    private Integer sex;

    /**
     * 封装目录+菜单
     */
    private List<SysPermission> menuList;

    /**
     * 封装目录+菜单+按钮
     */
    private List<SysPermission> permissionList;


}
