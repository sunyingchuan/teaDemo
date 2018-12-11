package com.ucar.team.seven.tea.system.service;

import com.ucar.team.seven.tea.common.service.IBaseService;
import com.ucar.team.seven.tea.common.web.PageResult;
import com.ucar.team.seven.tea.common.web.Result;
import com.ucar.team.seven.tea.system.entity.SysRole;
import com.ucar.team.seven.tea.system.vo.SysRoleQuery;

import java.lang.reflect.InvocationTargetException;

/**
 * @描述
 * @参数 $
 * @返回值 $
 * @创建人 yufeng.lin@ucarinc.com
 * @创建时间 $
 * @修改人和其它信息
 */
public interface SysRoleService extends IBaseService<SysRole> {
    
    /**
     * @Author yufeng.lim@ucarinc.com
     * @Description  查询角色分页列表
     * @Date 10:26 2018/9/30
     * @Param [vo]
     * @return com.ucar.team.seven.tea.common.web.PageResult
     **/
    PageResult listPage(SysRoleQuery query);
    /**
     * @Author yufeng.lim@ucarinc.com
     * @Description  插入操作
     * @Date 13:27 2018/9/30
     * @Param [vo]
     * @return com.ucar.team.seven.tea.common.web.Result
     **/
    Result addRole(SysRoleQuery query) throws InvocationTargetException, IllegalAccessException;

    /**
     * @Author yufeng.lim@ucarinc.com
     * @Description  更新角色操作
     * @Date 13:36 2018/9/30
     * @Param [query]
     * @return com.ucar.team.seven.tea.common.web.Result
     **/
    Result updateRole(SysRoleQuery query) throws InvocationTargetException, IllegalAccessException;

    /**
     * @Author yufeng.lim@ucarinc.com
     * @Description  根据ID删除角色
     * @Date 13:52 2018/9/30
     * @Param [roleId]
     * @return com.ucar.team.seven.tea.common.web.Result
     **/
    Result deleteRoleByIds(String roleIds);

    void saveRolePermission(Long roleId, String permissionIds);
}





















