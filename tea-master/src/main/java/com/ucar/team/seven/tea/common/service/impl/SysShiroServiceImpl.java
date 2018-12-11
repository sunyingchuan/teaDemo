package com.ucar.team.seven.tea.common.service.impl;

import com.ucar.team.seven.tea.common.exception.ServiceException;
import com.ucar.team.seven.tea.common.service.SysShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/26 8:48
 * @Version 1.0
 * @Description 实现IBaseService接口的方法
 */
@Service
public class SysShiroServiceImpl implements SysShiroService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysShiroServiceImpl.class);

    @Override
    public void login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return;
        }
        // 把用户名和密码封装为 UsernamePasswordToken 对象
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            //登录认证 - 调用userRealm
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            LOGGER.error("密码错误！", ice);
            throw new ServiceException("密码错误！");
        } catch (AuthenticationException ae) {
            LOGGER.error("认证失败！", ae);
            throw new ServiceException("认证失败");
        }
    }
}
