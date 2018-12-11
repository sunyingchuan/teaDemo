package com.ucar.team.seven.tea.common.service.impl;

import com.ucar.team.seven.tea.common.constant.CommonConstant;
import com.ucar.team.seven.tea.common.dao.BaseDao;
import com.ucar.team.seven.tea.common.service.IBaseService;
import com.ucar.team.seven.tea.common.util.ShiroUtil;

import java.util.List;
/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/26 8:48
 * @Version 1.0
 * @Description 实现IBaseService接口的方法
 */
public abstract class BaseService<T> implements IBaseService<T> {
    /**
     * 获取dao
     *
     * @return
     */
    public abstract BaseDao<T> getDao();

    /**
     * 新增
     *
     * @param entity
     * @return
     */
    @Override
    public Integer insert(T entity) {
        return getDao().insert(entity);
    }

    /**
     * 批量新增
     *
     * @param list
     * @return
     */
    @Override
    public int batchInsert(List<T> list) {
        return getDao().batchInsert(list);
    }

    /**
     * 修改
     *
     * @param entity
     * @return
     */
    @Override
    public Integer update(T entity) {
        return getDao().update(entity);
    }

    /**
     * 根据主键id删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(Long id) {
        return getDao().deleteById(id);
    }

    /**
     * 根据主键id获取
     *
     * @param id
     * @return
     */
    @Override
    public T getById(Long id) {
        return getDao().getById(id);
    }

    /**
     * 根据指定字段值获取对象
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public T getByKey(String key, Object value) {
        return getDao().getByKey(key, value);
    }

    /**
     * @description 获取当前操作用户ID
     * @date  2018/10/18 11:30
     * @return  java.lang.Long
     */
    @Override
    public Long getEmp() {
        if(ShiroUtil.getSessionUser() == null){
            return CommonConstant.AUTO_SYS_USER_ID;
        }
        return ShiroUtil.getSessionUser().getId();
    }

}
