package com.ucar.team.seven.tea.system.dao;

import com.ucar.team.seven.tea.common.dao.BaseDao;
import com.ucar.team.seven.tea.system.entity.SysPermission;
import com.ucar.team.seven.tea.system.vo.SysPermissionQuery;

import java.util.List;

public interface SysPermissionDao extends BaseDao<SysPermission> {
    
    /**
     * @Author yufeng.lim@ucarinc.com
     * @Description  根据用户id查询权限
     * @Date 14:10 2018/9/27
     * @Param [userId]
     * @return java.util.Map<java.lang.String,java.util.List<com.ucar.team.seven.tea.system.entity.SysPermission>>
     **/
    List<SysPermission> getPermissionList(Long userId);

    List<SysPermission> getPermissionListByRoleId(Long roleId);

    /**
     * @Author yufeng.lim@ucarinc.com
     * @Description 获取权限列表
     * @Date 14:10 2018/9/27
     * @Param [userId]
     * @return java.util.list
     **/
    List<SysPermission> getList();

    Long getTotalNum();

    List<SysPermission> getListByQuery(SysPermissionQuery query);
    
    /**
     * @Author yufeng.lim@ucarinc.com
     * @Description  获取父级目录的名称
     * @Date 11:37 2018/10/8
     * @Param [parentMenuList]
     * @return java.util.List<com.ucar.team.seven.tea.system.entity.SysPermission>
     **/
    List<SysPermission> getParentNameList(List list);

    Integer deleteBatch(String[] array);
}
