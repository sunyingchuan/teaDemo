package com.ucar.team.seven.tea.system.service.impl;

import com.ucar.team.seven.tea.common.dao.BaseDao;
import com.ucar.team.seven.tea.common.enums.UserStatus;
import com.ucar.team.seven.tea.common.service.impl.BaseService;
import com.ucar.team.seven.tea.common.util.Md5Util;
import com.ucar.team.seven.tea.common.web.Result;
import com.ucar.team.seven.tea.system.dao.SysUserDao;
import com.ucar.team.seven.tea.system.entity.SysUser;
import com.ucar.team.seven.tea.system.service.SysUserService;
import com.ucar.team.seven.tea.system.vo.SysUserRoleVo;
import com.ucar.team.seven.tea.system.vo.UserVo;
import com.ucar.team.seven.tea.system.vo.params.UserParamVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/26 8:53
 * @Version 1.0
 * @Description 用户service类
 */
@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends BaseService<SysUser> implements SysUserService {

    @Resource
    private SysUserDao userDao;

    @Override
    public BaseDao<SysUser> getDao() {
        return userDao;
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Integer newStatus;
        if( status == UserStatus.INVALID.getValue() ){
            newStatus = UserStatus.VALID.getValue();
        }else{
            newStatus = UserStatus.INVALID.getValue();
        }
        //记录操作人
        SysUser user = getById(id);

        user.setCreateEmp(getEmp());
        user.setModifyEmp(getEmp());

        user.setStatus(newStatus);
        userDao.update(user);
    }

    @Override
    public List<UserVo> findPage(UserParamVo userParamVo) {
        Long total = getCount(userParamVo);
        userParamVo.setRowCount(total);
        userParamVo.setStartIndex((userParamVo.getPageCurrent() - 1) * userParamVo.getPageSize());

        return userDao.findPage(userParamVo);
    }

    @Override
    public Long getCount(UserParamVo userParamVo) {
        return userDao.getCount(userParamVo);
    }

    @Override
    public SysUser getByUsername(String username) {
        return userDao.getByUserName(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result insertSysUser(SysUser user,Long roleId) {

        if(user.getUsername()== null || "".equals(user.getUsername())){
            return Result.failResult("创建失败！请填写用户名！");
        }
        if(user.getPassword().length()< 6 || user.getPassword().length() > 20){
            return Result.failResult("创建失败！密码长度应为6-20位！");
        }
        if(roleId == null || roleId == 0){
            return Result.failResult("创建失败！请为用户分配角色！");
        }

        SysUserRoleVo sysUserRoleVo = new SysUserRoleVo();

        if(getByUsername(user.getUsername())!=null){
            return Result.failResult("创建失败！该用户名已存在");
        }
        String pwd = Md5Util.getMD5(user.getPassword());
        user.setPassword(pwd);
        //记录操作人

        user.setCreateEmp(getEmp());
        user.setModifyEmp(getEmp());
        sysUserRoleVo.setCreateEmp(getEmp());
        sysUserRoleVo.setModifyEmp(getEmp());

        //插入用户表
        userDao.insert(user);

        //插入用户角色表
        Long userId = getByUsername(user.getUsername()).getId();
        sysUserRoleVo.setUserId(userId);
        sysUserRoleVo.setRoleId(roleId);
        userDao.insertUserRoleRecord(sysUserRoleVo);

        return Result.successResult();
    }

    @Override
    public void updateUserRole(SysUserRoleVo sysUserRoleVo) {
        userDao.updateUserRole(sysUserRoleVo);
    }

    @Override
    public Result updateSysUser(SysUser user, Long roleId) {

        if(user.getUsername()== null || "".equals(user.getUsername())){
            return Result.failResult("修改失败！请填写用户名！");
        }
        if(roleId == null || roleId == 0){
            return Result.failResult("修改失败！请为用户分配角色！");
        }

        SysUserRoleVo sysUserRoleVo = new SysUserRoleVo();
        if("".equals(user.getPassword())){
            String oldPwd = getById(user.getId()).getPassword();
            user.setPassword(oldPwd);
        }else{
            if(user.getPassword().length()<6||user.getPassword().length()>20){
                return Result.failResult("创建失败！密码长度应为6-20位！");
            }
            String pwd = Md5Util.getMD5(user.getPassword());
            user.setPassword(pwd);
        }

        user.setCreateEmp(getEmp());
        user.setModifyEmp(getEmp());

        sysUserRoleVo.setCreateEmp(getEmp());
        sysUserRoleVo.setModifyEmp(getEmp());
        sysUserRoleVo.setUserId(user.getId());
        sysUserRoleVo.setRoleId(roleId);

        updateUserRole(sysUserRoleVo);
        userDao.update(user);
        return Result.successResult();
    }
}
