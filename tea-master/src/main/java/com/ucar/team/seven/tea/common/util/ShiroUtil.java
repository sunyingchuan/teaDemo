package com.ucar.team.seven.tea.common.util;

import com.ucar.team.seven.tea.system.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/27 14:52
 * @Version 1.0
 * @Description shiro工具类
 */
public class ShiroUtil {

    /**
     * 密码加密
     *
     * @param password
     * @param salt
     * @return
     */
    public static String md51024Pwd(String password, Object salt) {
        return new SimpleHash("MD5", password, salt, 1024).toString();
    }

    /**
     * 获取当前Session中的用户
     *
     * @return
     */
    public static SysUser getSessionUser() {

        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Object object = subject.getPrincipal();
            if (object != null) {
                SysUser sysUser = (SysUser) object;
                return sysUser;
            }
        }
        return null;
    }

}
