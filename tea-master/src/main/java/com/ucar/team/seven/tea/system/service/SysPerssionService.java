package com.ucar.team.seven.tea.system.service;

import com.ucar.team.seven.tea.common.service.IBaseService;
import com.ucar.team.seven.tea.common.web.PageResult;
import com.ucar.team.seven.tea.common.web.Result;
import com.ucar.team.seven.tea.system.entity.SysPermission;
import com.ucar.team.seven.tea.system.vo.SysPermissionQuery;

import java.util.List;
import java.util.Map;

public interface SysPerssionService extends IBaseService<SysPermission> {
    /**
     * @Author yufeng.lim@ucarinc.com
     * @Description  根据用户id获取权限信息
     * @Date 14:47 2018/9/27
     * @Param [id]
     * @return java.util.Map<java.lang.String,java.util.List<com.ucar.team.seven.tea.system.entity.SysPermission>>
     **/
    Map<String,List<SysPermission>> getPermissionMapByUserId(Long id);

    /**
     * @Author yufeng.lim@ucarinc.com
     * @Description  根据用户id获取权限信息
     * @Date 14:47 2018/9/27
     * @Param [id]
     * @return java.util.Map<java.lang.String,java.util.List<com.ucar.team.seven.tea.system.entity.SysPermission>>
     **/
    List<SysPermission> getPermissionListByRoleId(Long roleId);

    PageResult getListByQuery(SysPermissionQuery query);

    List<SysPermission> getParentNameList();

    Result addPermission(SysPermissionQuery query);

    Result updatePermission(SysPermissionQuery query);

    Result deletePermissionByIds(String roleIds);
}
