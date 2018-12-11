package com.ucar.team.seven.tea.system.service;

import com.ucar.team.seven.tea.common.web.Result;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Henry(jian.zhang15 @ ucarinc.com)
 * @Date 2018/10/9 8:53
 * @Version 1.0
 * @Description 系统用户修改密码Service接口
 */
public interface SysUserUpdatePasswordService {
    /**
     * 修改密码
     * @param password 用户密码
     * @param username 用户名
     * @return 用户实体
     */
    void updatePassword(String password,String username);

    /**
     * 修改密码验证
     * @param oldPassword 原密码
     * @param newPassword 新户名
     * @return 用户实体
     */
    Result updatePasswordCheck(String oldPassword,String newPassword);
}
