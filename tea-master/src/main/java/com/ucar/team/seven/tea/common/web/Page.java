package com.ucar.team.seven.tea.common.web;

import java.io.Serializable;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/26 8:50
 * @Version 1.0
 * @Description 封装具体的分页信息
 */
public class Page implements Serializable {
    /**
     * 当前页
     */
    private Long pageCurrent = 1L;
    /**
     * 每页最多能显示的记录数
     */
    private Long pageSize = 10L;
    /**
     * 总记录数
     */
    private Long rowCount;
    /**
     * 上一页的最后一条记录位置
     * 对应:limit startIndex,pageSize;
     */
    private Long startIndex;

    @Override
    public String toString() {
        return "Page{" +
                "pageCurrent=" + pageCurrent +
                ", pageSize=" + pageSize +
                ", rowCount=" + rowCount +
                ", startIndex=" + startIndex +
                '}';
    }

    public Long getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(Long pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getRowCount() {
        return rowCount;
    }

    public void setRowCount(Long rowCount) {
        this.rowCount = rowCount;
    }

    public Long getPageCount() {
        Long pages = rowCount / pageSize;
        if (0 != rowCount % pageSize) {
            pages += 1;
        }
        return pages;
    }

    public Long getStartIndex() {
        if (startIndex==null){
        return (pageCurrent-1)*pageSize;}
        return startIndex;
    }

    public void setStartIndex(Long startIndex) {
        this.startIndex = startIndex;
    }
}
