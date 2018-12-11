package com.ucar.team.seven.tea.common.web;

import java.io.Serializable;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/26 8:52
 * @Version 1.0
 * @Description Controller 统一返回值类型,便于在页面上进行统一处理
 */
public class Result<T> implements Serializable {
    public static final int SUCCESS = 1;
    public static final int ERROR = 0;
    /**
     * 状态
     */
    private int state;
    /**
     * 对应状态的消息
     */
    private String message;
    /**
     * 具体业务数据
     */
    private T data;

    public Result(int state, String message, T data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    /**
     * 此构造方法应用于data为null的场景
     */
    public Result() {
        this.state = SUCCESS;
        this.message = "OK";
    }

    public Result(int state, String message) {
        this.state = state;
        this.message = message;
    }

    /**
     * 有具体业务数据返回时,使用此构造方法
     */
    public Result(T data) {
        this();
        this.data = data;
    }

    /**
     * 出现异常以后要调用此方法封装异常信息
     */
    public Result(Throwable t) {
        this.state = ERROR;
        this.message = t.getMessage();
    }

    /**
     * 成功的请求
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> suc(T data){
        return new Result<>(SUCCESS,"OK",data);
    }

    /**
     * 失败的请求
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> err(String msg){
        return new Result<>(ERROR,msg,null);
    }
    public static Result failResult (){
        Result result = new Result();
        result.setState(ERROR);
        return result;
    }
    public static Result failResult (String message){
        Result result = new Result();
        result.setState(ERROR);
        result.setMessage(message);
        return result;
    }
    public static Result failResult (Throwable t){
        Result result = new Result();
        result.setState(ERROR);
        result.setData(t);
        return result;
    }

    public static Result successResult (){
        Result result = new Result();
        result.setState(SUCCESS);
        return result;
    }
    public static Result successResult (String message){
        Result result = new Result();
        result.setState(SUCCESS);
        result.setMessage(message);
        return result;
    }
    public static Result successResult (Object data){
        Result result = new Result();
        result.setState(SUCCESS);
        result.setData(data);
        return result;
    }
    public static Result successResult (Object data,String message){
        Result result = new Result();
        result.setState(SUCCESS);
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "state=" + state +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
