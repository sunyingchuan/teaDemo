package com.ucar.team.seven.tea.system.service.impl;

import com.ucar.team.seven.tea.common.dao.BaseDao;
import com.ucar.team.seven.tea.common.service.impl.BaseService;
import com.ucar.team.seven.tea.common.service.impl.SysShiroServiceImpl;
import com.ucar.team.seven.tea.common.util.Md5Util;
import com.ucar.team.seven.tea.common.web.Result;
import com.ucar.team.seven.tea.system.dao.SysUserUpdatePasswordDao;
import com.ucar.team.seven.tea.system.entity.SysUser;
import com.ucar.team.seven.tea.system.service.SysUserService;
import com.ucar.team.seven.tea.system.service.SysUserUpdatePasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import com.ucar.team.seven.tea.common.util.ShiroUtil;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Henry(jian.zhang15 @ ucarinc.com)
 * @Date 2018/10/9 11:00:56
 * @Version 1.0
 * @Description 用户修改密码Service类
 */
@Service("sysUserUpdatePasswordService")
@Transactional(rollbackFor = Exception.class)
public class SysUserUpdatePasswordServiceImpl extends BaseService<SysUser>  implements SysUserUpdatePasswordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysShiroServiceImpl.class);
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysUserUpdatePasswordService sysUserUpdatePasswordService;

    @Resource
    private SysUserUpdatePasswordDao userDao;

    @Override
    public void updatePassword(String password, String username) {
        userDao.updatePassword(password,username);
    }

    @Override
    public Result updatePasswordCheck(String oldPassword, String newPassword) {
        Result result;
        String username=ShiroUtil.getSessionUser().getUsername();
        String password=sysUserService.getByUsername(username).getPassword();
        if(password.equals(Md5Util.getMD5(oldPassword))){
            LOGGER.info(username+"修改密码成功！");
            sysUserUpdatePasswordService.updatePassword(Md5Util.getMD5(newPassword),username);
            result=new Result(1,"SUCCESS");
        }
        else{
            LOGGER.info(username+"修改密码成功！");
            result=new Result(2,"NOT OK");
        }
        return result;
    }


    @Override
    public BaseDao<SysUser> getDao() {
        return (BaseDao<SysUser>) userDao;
    }
}
