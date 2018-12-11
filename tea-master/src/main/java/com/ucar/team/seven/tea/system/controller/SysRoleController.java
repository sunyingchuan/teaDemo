package com.ucar.team.seven.tea.system.controller;

import com.ucar.team.seven.tea.common.controller.BaseController;
import com.ucar.team.seven.tea.common.web.PageResult;
import com.ucar.team.seven.tea.common.web.Result;
import com.ucar.team.seven.tea.system.service.SysRoleService;
import com.ucar.team.seven.tea.system.vo.SysRoleQuery;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @描述
 * @参数 $
 * @返回值 $
 * @创建人 yufeng.lin@ucarinc.com
 * @创建时间 $
 * @修改人和其它信息
 */
@Controller
@RequestMapping("role")
public class SysRoleController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private SysRoleService sysRoleService;

    @RequiresPermissions("role:listUI")
    @RequestMapping("listUI.do")
    public String listUI() {
        return "system/role_manager";
    }


    @RequiresPermissions("role:listUI")
    @RequestMapping("list.do")
    @ResponseBody
    public PageResult list(@RequestBody  SysRoleQuery query) throws Exception {
        return sysRoleService.listPage(query);

    }

    @RequiresPermissions(value = "role:add")
    @RequestMapping("add")
    @ResponseBody
    public Result addRole(@RequestBody SysRoleQuery query) throws Exception {
        LOGGER.info("begin -add Role --");
        return sysRoleService.addRole(query);
    }

    @RequiresPermissions(value = "role:update")
    @RequestMapping("update")
    @ResponseBody
    public Result updateRole(@RequestBody SysRoleQuery query) throws Exception {
        LOGGER.info("pudate Role --");
        return sysRoleService.updateRole(query);
    }

    @RequiresPermissions(value = "role:delete")
    @RequestMapping("delete")
    @ResponseBody
    public Result deleteRoleById(@RequestBody  String roleIds) throws Exception {
        LOGGER.info("-delete Role --");
        return sysRoleService.deleteRoleByIds(roleIds);
    }

    @RequiresPermissions("role:setPermission")
    @RequestMapping("setPermission")
    @ResponseBody
    public Result setPermission(Long roleId, String permissionIds) {
        this.sysRoleService.saveRolePermission(roleId,permissionIds);
        return Result.successResult();
    }
    

}



















