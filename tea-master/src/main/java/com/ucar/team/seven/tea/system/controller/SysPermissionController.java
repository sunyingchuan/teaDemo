package com.ucar.team.seven.tea.system.controller;

import com.ucar.team.seven.tea.common.web.PageResult;
import com.ucar.team.seven.tea.common.web.Result;
import com.ucar.team.seven.tea.system.entity.SysPermission;
import com.ucar.team.seven.tea.system.enums.PermissionTypeEnums;
import com.ucar.team.seven.tea.system.service.SysPerssionService;
import com.ucar.team.seven.tea.system.vo.SysPermissionQuery;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述
 * @参数 $
 * @返回值 $
 * @创建人 yufeng.lin@ucarinc.com
 * @创建时间 $
 * @修改人和其它信息
 */
@Controller
@RequestMapping("permission")
public class SysPermissionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysPermissionController.class);

    @Autowired
    SysPerssionService sysPermissionService;

    @RequiresPermissions("permission:listUI")
    @RequestMapping("listUI")
    public String listUI() {
        return "system/permission_manager";
    }

    @RequiresPermissions("role:setPermission")
    @RequestMapping("getPermissionListWithChecked/{roleId}")
    @ResponseBody
    public Result getPermissionListWithChecked(@PathVariable("roleId") Long roleId) {
        List<SysPermission> permissionList = this.sysPermissionService.getPermissionListByRoleId(roleId);
        return Result.successResult(permissionList);
    }

    @RequiresPermissions("permission:listUI")
    @RequestMapping("list")
    @ResponseBody
    public PageResult list(SysPermissionQuery query) {
        return  this.sysPermissionService.getListByQuery(query);
    }


    @RequiresPermissions(value = "permission:add")
    @RequestMapping("add")
    @ResponseBody
    public Result addPermission(@RequestBody SysPermissionQuery query) throws Exception {
        LOGGER.info("begin -add permission --");
        if (query.getId() == null || query.getId() == 0){
            return sysPermissionService.addPermission(query);
        }
        return sysPermissionService.updatePermission(query);
    }


    @RequiresPermissions(value = "permission:delete")
    @RequestMapping("delete")
    @ResponseBody
    public Result deleteRoleById(@RequestBody  String roleIds) throws Exception {
        LOGGER.info("-delete permission --");
        return sysPermissionService.deletePermissionByIds(roleIds);
    }


    @RequiresPermissions(value = "permission:update")
    @RequestMapping("update")
    @ResponseBody
    public Result updateRole(@RequestBody SysPermissionQuery query) throws Exception {
        LOGGER.info("--update permission --");
        return sysPermissionService.updatePermission(query);
    }


    /**
     * @Author yufeng.lim@ucarinc.com
     * @Description  获取所以父级目录·名称，以及类型
     * @Date 11:38 2018/10/8
     * @return com.ucar.team.seven.tea.common.web.Result
     **/
    @RequestMapping("getParentNameAndPermissionType")
    @ResponseBody
    public Result getParentNameAndPermissionType(){
        Map<String,Object> map = new HashMap<>();
        List parentList = sysPermissionService.getParentNameList();
        List<SysPermission> rt = new ArrayList<>();
        recurData(parentList, rt, "━━");

        map.put("parentNameList",rt);
        map.put("permissionTypes",PermissionTypeEnums.getKeyValueList());
        return Result.successResult(map);
    }

    private void recurData(List<SysPermission> source, List<SysPermission> dest, String prefix) {
        for (SysPermission permission : source) {
            permission.setName("┣" + prefix + permission.getName());
            dest.add(permission);
            if (permission.getChildren() != null && !permission.getChildren().isEmpty()) {
                recurData(permission.getChildren(), dest, prefix + prefix);
            }
        }
    }



}











































