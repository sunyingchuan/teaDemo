package com.ucar.team.seven.tea.common.exception;

import com.ucar.team.seven.tea.common.web.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/26 8:48
 * @Version 1.0
 * @Description 通过此注解声明此类为一个全局异常处理类型
 */

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 当spring发现系统出现异常了,且异常的
     * 类型为ServiceException类型,此时就会
     * 回调此方法,并将异常值传递给这个方法,
     * 这时我们就可以在此方法中对业务异常进行
     * 统一处理,例如封装到jsonResult,然后
     * 写到客户端告诉用户
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result handleServiceException(ServiceException e) {
        LOGGER.error("handleServiceException:", e);
        //将异常封装到JsonResult
        return new Result(e);
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleRuntimeException(RuntimeException e) {
        LOGGER.error("handleRuntimeException:", e);
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exp", e.getMessage());
        return mv;
    }
}
