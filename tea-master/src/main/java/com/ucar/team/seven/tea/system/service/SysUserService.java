package com.ucar.team.seven.tea.system.service;

import com.ucar.team.seven.tea.common.service.IBaseService;
import com.ucar.team.seven.tea.common.web.Result;
import com.ucar.team.seven.tea.system.entity.SysUser;
import com.ucar.team.seven.tea.system.vo.SysUserRoleVo;
import com.ucar.team.seven.tea.system.vo.params.UserParamVo;
import com.ucar.team.seven.tea.system.vo.UserVo;

import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/26 8:53
 * @Version 1.0
 * @Description 系统用户Service接口
 */
public interface SysUserService extends IBaseService<SysUser> {

    /**
     * 更新用户状态
     *
     * @param id
     * @param valid
     */
    void updateStatus(Long id, Integer status);

    /**
     * 获取列表
     *
     * @param userParamVo
     * @return
     */
    List<UserVo> findPage(UserParamVo userParamVo);

    /**
     * 获取数量
     *
     * @param userParamVo
     * @return
     */
    Long getCount(UserParamVo userParamVo);

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户实体
     */
    SysUser getByUsername(String username);

    /**
     * 创建用户
     *
     * @param user 用户
     * @param roleId
     * @return 用户实体
     */
    Result insertSysUser(SysUser user, Long roleId);

    /**
     * @description 修改用户角色信息
     * @date  2018/10/12 14:02
     * @param sysUserRoleVo
     * @return  void
     */
    void updateUserRole(SysUserRoleVo sysUserRoleVo);

    /**
     * 更新用户
     *
     * @param user 用户
     * @param roleId
     * @return void
     */
    Result updateSysUser(SysUser user, Long roleId);
}
