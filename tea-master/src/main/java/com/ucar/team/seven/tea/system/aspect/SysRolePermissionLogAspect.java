package com.ucar.team.seven.tea.system.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @描述
 * @参数 $
 * @返回值 $
 * @创建人 yufeng.lin@ucarinc.com
 * @创建时间 $
 * @修改人和其它信息
 */
@Aspect    //该标签把LoggerAspect类声明为一个切面
@Order(1)  //设置切面的优先级：如果有多个切面，可通过设置优先级控制切面的执行顺序（数值越小，优先级越高）
@Component //该标签把LoggerAspect类放到IOC容器中
public class SysRolePermissionLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysRolePermissionLogAspect.class);

    @Pointcut("execution(public * com.ucar.team.seven.tea.system.controller.SysRoleController.*(..))")
    public void roleControllerLogJoinPoint() {
    }

    @Pointcut("execution(public * com.ucar.team.seven.tea.system.controller.SysPermissionController.*(..))")
    public void permissionControllerLog() {
    }

    @Pointcut("execution(public * com.ucar.team.seven.tea.system.service.impl.SysRoleServiceImpl.*(..))")
    public void sysRoleServiceImplLog() {
    }

    @Pointcut("execution(public * com.ucar.team.seven.tea.system.service.impl.SysPermissionServiceImpl.*(..))")
    public void sysPermissionServiceImplLog() {
    }


    @Pointcut("roleControllerLogJoinPoint() || permissionControllerLog() || sysRoleServiceImplLog() || sysPermissionServiceImplLog()")
    public void sysPermissionControllerLog() {
    }

    @Before("sysPermissionControllerLog()")
    public void beforeLogParms(JoinPoint joinPoint) {

        String targetName = joinPoint.getTarget().getClass().getName();
        LOGGER.info("(入参日志)方法名:" + targetName + "." + joinPoint.getSignature().getName());
        LOGGER.info("(入参日志)请求参数:" + joinPoint.getArgs());
    }


    /**
     * 返回通知（在方法正常结束执行的代码）
     * 返回通知可以访问到方法的返回值！
     *
     * @param result
     */
    @AfterReturning(value = "sysPermissionControllerLog()", returning = "result")
    public void afterReturnMethod(JoinPoint joinPoint, Object result) {
        LOGGER.info("(返回日志)方法名:" + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
        LOGGER.info("(返回日志)返回值:" + result);
    }

    /**
     * @return void
     * @Author yufeng.lim@ucarinc.com
     * @Description 异常打印日志
     * @Date 16:31 2018/10/18
     * @Param [joinPoint, ex]
     **/
    @AfterThrowing(value = "sysPermissionControllerLog()", throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();
        LOGGER.info("(抛异常)this method " + methodName + " end.ex message<" + ex + ">");
    }


}
























