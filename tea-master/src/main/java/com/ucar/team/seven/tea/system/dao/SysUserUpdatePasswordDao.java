package com.ucar.team.seven.tea.system.dao;

import org.apache.ibatis.annotations.Param;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Henry(jian.zhang15 @ ucarinc.com)
 * @Date 2018/10/9 8:52
 * @Version 1.0
 * @Description 系统用户修改密码Dao
 */
public interface SysUserUpdatePasswordDao {

    /**
     * description: 修改用户密码
     * @Author Henry (jian.zhang15 @ carinc.com)
     * @Date 2018/10/8 11:28
     * @param password 密码
     * @param username 用户名
     */
    void updatePassword(@Param("password") String password, @Param("username") String username);
}
