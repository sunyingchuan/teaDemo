package com.ucar.team.seven.tea.system.aspect;

import com.ucar.team.seven.tea.common.web.QueryBase;
import com.ucar.team.seven.tea.system.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @描述 为后面6个字段添加信息
 * @参数 $
 * @返回值 $
 * @创建人 yufeng.lin@ucarinc.com
 * @创建时间 $
 * @修改人和其它信息
 */
@Aspect    //该标签把LoggerAspect类声明为一个切面
@Order(10)  //设置切面的优先级：如果有多个切面，可通过设置优先级控制切面的执行顺序（数值越小，优先级越高）
@Component //该标签把LoggerAspect类放到IOC容器中
public class SysAddOrUpdateAdditionAspect {


    /**
     * 定义一个方法，用于声明切入点表达式，方法中一般不需要添加其他代码
     * 使用@Pointcut声明切入点表达式
     * 后面的通知直接使用方法名来引用当前的切点表达式；如果是其他类使用，加上包名即可
     */
    @Pointcut("execution(public * com.ucar.team.seven.tea.system.service.impl.SysRoleServiceImpl.add*(..))")
    public void addRoleJoinPointExpression() {
    }

    @Pointcut("execution(public * com.ucar.team.seven.tea.system.service.impl.SysRoleServiceImpl.update*(..))")
    public void updateRoleJoinPointExpression() {
    }

    @Pointcut("execution(public * com.ucar.team.seven.tea.system.service.impl.SysPermissionServiceImpl.update*(..))")
    public void updatePermissionJoinPointExpression() {
    }

    @Pointcut("execution(public * com.ucar.team.seven.tea.system.service.impl.SysPermissionServiceImpl.add*(..))")
    public void addPermissionJoinPointExpression() {
    }

    @Pointcut("addRoleJoinPointExpression() || updateRoleJoinPointExpression() || updatePermissionJoinPointExpression() || addPermissionJoinPointExpression()")
    private void updateOrAddExpr() {
    }

    /**
     * 前置通知
     *
     * @param joinPoint
     */
    @Before("updateOrAddExpr()") //该标签声明次方法是一个前置通知：在目标方法开始之前执行
    public void beforMethod(JoinPoint joinPoint) {

        Object parm = joinPoint.getArgs()[0];
        QueryBase queryBase = (QueryBase) parm;

        //获取当前系统管理员信息
        Subject sub = SecurityUtils.getSubject();
        SysUser currLoginSysUser = (SysUser) sub.getPrincipal();

        //设置addition信息
        if (queryBase.getCreateTime() == null) {
            queryBase.setCreateTime(new Date());
        }
        if (queryBase.getCreateEmp() == null) {
            queryBase.setCreateEmp(currLoginSysUser.getId());
        }
        queryBase.setModifyTime(new Date());
        if (queryBase.getModifyEmp() == null) {
            queryBase.setModifyEmp(currLoginSysUser.getId());
        }
        if (queryBase.getRemark() == null || queryBase.getRemark() == "") {
            //没设置则记入入参
            queryBase.setRemark("后台管理人员操作");
        }


    }

    /**
     * 后置通知（无论方法是否发生异常都会执行,所以访问不到方法的返回值）
     *
     * @param joinPoint
     */
//    @After("declearJoinPointExpression()")
//    public void afterMethod(JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().getName();
//        System.out.println("this method " + methodName + " end.");
//    }

    /**
     * 返回通知（在方法正常结束执行的代码）
     * 返回通知可以访问到方法的返回值！
     *
     * @param result
     */
//    @AfterReturning(value = "declearJoinPointExpression()", returning = "result")
//    public void afterReturnMethod(JoinPoint joinPoint, Object result) {
//        String methodName = joinPoint.getSignature().getName();
//        System.out.println("this method " + methodName + " end.result<" + result + ">");
//    }

    /**
     * 异常通知（方法发生异常执行的代码）
     * 可以访问到异常对象；且可以指定在出现特定异常时执行的代码
     *
     * @param joinPoint
     * @param ex
     */
//    @AfterThrowing(value = "declearJoinPointExpression()", throwing = "ex")
//    public void afterThrowingMethod(JoinPoint joinPoint, Exception ex) {
//        String methodName = joinPoint.getSignature().getName();
//        System.out.println("this method " + methodName + " end.ex message<" + ex + ">");
//    }

    /**
     * 环绕通知(需要携带类型为ProceedingJoinPoint类型的参数)
     * 环绕通知包含前置、后置、返回、异常通知；ProceedingJoinPoin 类型的参数可以决定是否执行目标方法
     * 且环绕通知必须有返回值，返回值即目标方法的返回值
     *
     * @param point
     */
//    @Around(value = "declearJoinPointExpression()")
//    public Object aroundMethod(ProceedingJoinPoint point) {
//
//        Object result = null;
//        String methodName = point.getSignature().getName();
//        try {
//            //前置通知
//            System.out.println("The method " + methodName + " start. param<" + Arrays.asList(point.getArgs()) + ">");
//            //执行目标方法
//            result = point.proceed();
//            //返回通知
//            System.out.println("The method " + methodName + " end. result<" + result + ">");
//        } catch (Throwable e) {
//            //异常通知
//            System.out.println("this method " + methodName + " end.ex message<" + e + ">");
//            throw new RuntimeException(e);
//        }
//        //后置通知
//        System.out.println("The method " + methodName + " end.");
//        return result;
//    }


}




























