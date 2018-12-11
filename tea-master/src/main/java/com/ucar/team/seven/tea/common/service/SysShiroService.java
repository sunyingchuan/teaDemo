package com.ucar.team.seven.tea.common.service;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/26 8:49
 * @Version 1.0
 * @Description
 */
public interface SysShiroService {

    /**
     * 登录
     *
     * @param username
     * @param password
     */
    void login(String username, String password);
}
