package com.ucar.team.seven.tea.common.service.impl;

import com.ucar.team.seven.tea.common.util.StringUtil;
import com.ucar.team.seven.tea.system.dao.SysUserDao;
import com.ucar.team.seven.tea.system.entity.SysPermission;
import com.ucar.team.seven.tea.system.entity.SysUser;
import com.ucar.team.seven.tea.system.service.SysPerssionService;
import com.ucar.team.seven.tea.system.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 版本声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com),yufeng.lin
 * @Date 2018/9/26 8:48
 * @Vsersion 1.0
 * @Description 实现IBaseService接口的方法
 */
public class ShiroUserRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroUserRealm.class);

    @Resource
    private SysUserDao sysUserDao;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysPerssionService sysPerssionService;


    /*****
     *授权
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser user = (SysUser) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // （目录+菜单+按钮）
        List<SysPermission> permissionList = user.getPermissionList();

        //授权操作
        for (SysPermission permission : permissionList) {
            if (StringUtil.isNotEmpty(permission.getCode())) {
                info.addStringPermission(permission.getCode());
            }
        }

        return info;
    }

    /**
     *r认证 前面调用login需要认证操作
     * 访问资源则需要授权操作
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        LOGGER.info("==doGetAuthenticationInfo==");
        //1. 把 AuthenticationToken 转换为 UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        //2. 从 UsernamePasswordToken 中来获取 username
        String username = upToken.getUsername();
        //判断用户名是否存在，若存在，返回user对象
        SysUser user = sysUserDao.getByUserName(username);
        if (user == null){
            return null;
        }

        // 通过 userId 获取该用户拥有的所有权限，返回值根据自己要求设置，并非固定值。
        Map<String,List<SysPermission>> permissionMap = this.sysPerssionService.getPermissionMapByUserId(user.getId());

        // （目录+菜单，分层级）
        user.setMenuList(permissionMap.get("menuList"));
        // （目录+菜单+按钮）
        user.setPermissionList(permissionMap.get("permissionList"));

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());

        SecurityUtils.getSubject().getSession().setAttribute("currentUser", user);
        return info;
    }



}
