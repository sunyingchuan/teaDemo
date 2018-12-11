package com.ucar.team.seven.tea.common.aspect;


import com.ucar.team.seven.tea.common.annotation.Log;
import com.ucar.team.seven.tea.common.util.ShiroUtil;
import com.ucar.team.seven.tea.system.entity.SysUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/27 14:48
 * @Version 1.0
 * @Description 操作日志记录
 */
@Aspect
@Component
public class LogAdvice {

    public static final Logger LOG = LoggerFactory.getLogger(LogAdvice.class);

    /**
     * 定义切面
     */
    @Pointcut("@annotation(com.ucar.team.seven.tea.common.annotation.Log)")
    public void controllerAspect() {

    }

    /**
     * 当方法正常返回是执行
     *
     * @param joinPoint
     */
    @AfterReturning("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Log log = method.getAnnotation(Log.class);
        SysUser sysUser = ShiroUtil.getSessionUser();
        if (log != null) {
            // TODO: 2018/9/27 log insert
            LOG.debug("记录日志:" + sysUser.getUsername());
        }
    }
}
