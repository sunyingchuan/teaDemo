package com.ucar.team.seven.tea.common.exception;
/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @program: tea-demo
 * @Author minmin.liu(minmin.liu @ ucarinc.com)
 * @Date 2018-10-11 14:06
 * @Version 1.0
 * @Description 订单相关业务异常
 */
public class OrderException extends RuntimeException {
    public OrderException(String message) {
        super(message);
    }
    public OrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
