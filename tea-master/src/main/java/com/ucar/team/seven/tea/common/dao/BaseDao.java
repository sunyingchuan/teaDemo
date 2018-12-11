package com.ucar.team.seven.tea.common.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/26 8:43
 * @Version 1.0
 * @Description 封装一些基本的dao操作，具体要在对应的mapper中实现sql
 */

public interface BaseDao<T> {

    /**
     * 新增
     *
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     * 批量新增
     *
     * @param list
     * @return
     */
    int batchInsert(List<T> list);

    /**
     * 修改
     *
     * @param entity
     * @return
     */
    int update(T entity);

    /**
     * 根据主键id删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据主键id获取对象
     *
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 根据指定字段值获取对象
     *
     * @param key
     * @param value
     * @return
     */
    T getByKey(@Param("key") String key, @Param("value") Object value);

}
