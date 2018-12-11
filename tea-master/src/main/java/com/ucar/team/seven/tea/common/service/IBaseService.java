package com.ucar.team.seven.tea.common.service;
import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/26 8:49
 * @Version 1.0
 * @Description 基类接口
 */
public interface IBaseService<T> {

    /**
     * 新增
     *
     * @param entity
     * @return
     */
    public Integer insert(T entity);

    /**
     * 批量新增
     *
     * @param list
     * @return
     */
    public int batchInsert(List<T> list);

    /**
     * 修改
     *
     * @param entity
     * @return
     */
    public Integer update(T entity);

    /**
     * 根据主键id删除
     *
     * @param id
     * @return
     */
    public int deleteById(Long id);

    /**
     * 根据主键id获取
     *
     * @param id
     * @return
     */
    public T getById(Long id);

    /**
     * 根据指定字段值获取对象
     *
     * @param key
     * @param value
     * @return
     */
    public T getByKey(String key, Object value);

    /**
     * 获取当前操作用户ID
     *
     * @date  2018/10/18 11:30
     * @return  java.lang.Long
     */
    Long getEmp();
}
