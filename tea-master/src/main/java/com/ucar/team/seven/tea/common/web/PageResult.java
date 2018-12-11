package com.ucar.team.seven.tea.common.web;

import java.io.Serializable;
import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/26 8:51
 * @Version 1.0
 * @Description 返回带分页信息的result
 */
public class PageResult<T> extends Result<T> {
    private static final long serialVersionUID = 768549219446295665L;

    /**
     * 总条数
     */
    private Long total;

    /**
     * 当前页显示数据
     */
    private List<T> records;


    public static PageResult  successPageResult(Long total,List records){
        PageResult pageResult = new PageResult<>();
        pageResult.setTotal(total);
        pageResult.setRecords(records);
        pageResult.setState(SUCCESS);
        return pageResult;
    }

    public static PageResult  failePageResult(){
        PageResult pageResult = new PageResult<>();
        pageResult.setState(ERROR);
        return pageResult;
    }
    public static PageResult  failePageResult(String message){
        PageResult pageResult = new PageResult<>();
        pageResult.setState(ERROR);
        pageResult.setMessage(message);
        return pageResult;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "total=" + total +
                ", records=" + records +
                '}';
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

}
