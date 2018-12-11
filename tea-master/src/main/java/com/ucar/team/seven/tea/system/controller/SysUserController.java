package com.ucar.team.seven.tea.system.controller;

import com.ucar.team.seven.tea.common.controller.BaseController;
import com.ucar.team.seven.tea.common.util.StringUtil;
import com.ucar.team.seven.tea.common.web.PageResult;
import com.ucar.team.seven.tea.common.web.Result;
import com.ucar.team.seven.tea.system.entity.SysUser;
import com.ucar.team.seven.tea.system.service.SysRoleService;
import com.ucar.team.seven.tea.system.service.SysUserService;
import com.ucar.team.seven.tea.system.vo.SysRoleQuery;
import com.ucar.team.seven.tea.system.vo.UserVo;
import com.ucar.team.seven.tea.system.vo.params.UserParamVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/26 8:52
 * @Version 1.0
 * @Description 系统用户Controller
 */
@Controller
@RequestMapping("user")
public class SysUserController extends BaseController {
    @Resource
    private SysUserService userService;

    @Resource
    private SysRoleService sysRoleService;

    /**
     * 用户列表页面
     *
     * @return
     */
    @RequestMapping("list.do")
    public ModelAndView listUI() {
        ModelAndView mv = new ModelAndView();
        SysRoleQuery query = new SysRoleQuery();
        query.setPageLimit(null);
        List roleList = sysRoleService.listPage(query).getRecords();
        mv.addObject("roleList",roleList);
        mv.setViewName("system/user_list");
        return mv;
    }

    /**
     * 查询用户列表
     *
     * @param userParamVo
     * @return
     */
    @RequestMapping(value = "list.do", method = RequestMethod.POST)
    @ResponseBody
    public PageResult find(UserParamVo userParamVo) {
        List<UserVo> list = userService.findPage(userParamVo);
        return getPageResult(list, userParamVo);
    }

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @RequestMapping("save.do")
    @ResponseBody
    public Result doSaveSysUser(SysUser user,Long roleId) {

        Result result = userService.insertSysUser(user,roleId);
        return result;
    }

    /**
     * 根据id查询用户信息，用于回显
     *
     * @param userId
     * @return
     */
    @RequestMapping("getById.do")
    @ResponseBody
    public Result getById(Long userId) {
        SysUser entity = userService.getById(userId);
        return getEntityResult(entity);
    }

    /**
     * 修改更新用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping("update.do")
    @ResponseBody
    public Result update(SysUser user, Long roleId) {

        Result result = userService.updateSysUser(user,roleId);
        return result;
    }

    /**
     * 启用/禁用
     *
     * @param userId
     * @param status
     * @return
     */
    @RequestMapping("valid.do")
    @ResponseBody
    public Result updateValid(Long id, Integer status) {
        userService.updateStatus(id, status);
        return getResult();
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping("delete.do")
    @ResponseBody
    public Result delete(String id) {
        Long[] ids = StringUtil.parseToLongArray(id);
        for (Long i : ids) {
            userService.deleteById(i);
        }
        return getResult();
    }

    /**
     * 获取用户角色列表
     *
     * @return
     */
    @RequestMapping("getUserRoleList.do")
    @ResponseBody
    public PageResult getUserRoleList() {
        SysRoleQuery query = new SysRoleQuery();
        query.setPageLimit(null);
        return sysRoleService.listPage(query);
    }
}