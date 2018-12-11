package com.ucar.team.seven.tea.system.dao;

import com.ucar.team.seven.tea.common.dao.BaseDao;
import com.ucar.team.seven.tea.system.entity.SysUser;
import com.ucar.team.seven.tea.system.vo.SysUserRoleVo;
import com.ucar.team.seven.tea.system.vo.params.UserParamVo;
import com.ucar.team.seven.tea.system.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/26 8:52
 * @Version 1.0
 * @Description 系统用户Dao
 */
public interface SysUserDao extends BaseDao<SysUser> {
    /**
     * 根据id更新用户状态
     *
     * @param id
     * @param status
     */
    void updateStatus(@Param("id") Long id, @Param("status") Integer status);

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    SysUser getByUserName(@Param("username") String username);

    /**
     * 分页查询
     *
     * @param userParamVo
     * @return
     */
    List<UserVo> findPage(@Param("params") UserParamVo userParamVo);

    /**
     * 获取数量
     *
     * @param userParamVo
     * @return
     */
    Long getCount(@Param("params") UserParamVo userParamVo);

    /**
     * @description 保存用户角色信息
     * @date  2018/10/12 14:02
     * @param sysUserRoleVo
     * @return  void
     */
    void insertUserRoleRecord(SysUserRoleVo sysUserRoleVo);

    /**
     * @description 修改用户角色信息
     * @date  2018/10/12 14:02
     * @param sysUserRoleVo
     * @return  void
     */
    void updateUserRole(SysUserRoleVo sysUserRoleVo);
}
