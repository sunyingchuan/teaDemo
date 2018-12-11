package com.ucar.team.seven.tea.system.service.impl;

import com.ucar.team.seven.tea.common.dao.BaseDao;
import com.ucar.team.seven.tea.common.service.impl.BaseService;
import com.ucar.team.seven.tea.common.util.StringUtil;
import com.ucar.team.seven.tea.common.web.PageResult;
import com.ucar.team.seven.tea.common.web.Result;
import com.ucar.team.seven.tea.system.dao.SysPermissionDao;
import com.ucar.team.seven.tea.system.entity.SysPermission;
import com.ucar.team.seven.tea.system.enums.PermissionTypeEnums;
import com.ucar.team.seven.tea.system.service.SysPerssionService;
import com.ucar.team.seven.tea.system.vo.SysPermissionQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
@Service(value = "sysPermissionService")
public class SysPermissionServiceImpl extends BaseService<SysPermission> implements SysPerssionService {


    @Resource
    private SysPermissionDao sysPermissionDao;

    @Override
    public BaseDao<SysPermission> getDao() {
        return sysPermissionDao;
    }

    @Override
    public Map<String, List<SysPermission>> getPermissionMapByUserId(Long id) {
        List<SysPermission> permissionList = this.sysPermissionDao.getPermissionList(id);

        List<SysPermission> menuList = new ArrayList<>();
        Map<Long,SysPermission> map = new HashMap<>();

        // 筛选目录
        for (SysPermission permission : permissionList) {
            if (permission.getType() != 3 && permission.getPid() == 0L) {
                map.put(permission.getId(), permission);
                menuList.add(permission);
            }
        }

        // 封装菜单
        for (SysPermission permission : permissionList) {
            if (permission.getType() != 3 && map.get(permission.getPid()) != null) {
                SysPermission parent = map.get(permission.getPid());
                parent.getChildren().add(permission);
            }
        }

        Map<String,List<SysPermission>> resultMap = new HashMap<>(2);
        resultMap.put("menuList", menuList);
        resultMap.put("permissionList", permissionList);

        return resultMap;
    }

    @Override
    public List<SysPermission> getPermissionListByRoleId(Long roleId) {
        List<SysPermission> list = sysPermissionDao.getPermissionListByRoleId(roleId);
        List<SysPermission> permissionList = sysPermissionDao.getList();
        for (SysPermission permission : permissionList) {
            for (SysPermission p : list) {
                if (permission.getId() == p.getId()) {
                    permission.setChecked(true);
                }
            }
        }

        return permissionList;
    }

    @Override
    public PageResult getListByQuery(SysPermissionQuery query) {
        Long count = sysPermissionDao.getTotalNum();
        List<SysPermission> list= sysPermissionDao.getListByQuery(query);
        return PageResult.successPageResult(count,list);
    }

    @Override
    public List<SysPermission> getParentNameList() {
        List list = new ArrayList();
        list.add(PermissionTypeEnums.DIR_TYPE.getIndex());
        return sysPermissionDao.getParentNameList(list);
    }

    @Override
    public Result addPermission(SysPermissionQuery query) {
        if (query == null){
            return Result.failResult("插入参数为空");
        }
        SysPermission sysPermission = new SysPermission();
        BeanUtils.copyProperties(query,sysPermission);
        SysPermission permission = sysPermissionDao.getById(query.getPid());
        sysPermission.setParentName(permission.getName());
        int count = sysPermissionDao.insert(sysPermission);
        if (count > 0){
            return Result.successResult("插入成功");
        }
        return Result.failResult("插入失败");
    }


    @Override
    public Result updatePermission(SysPermissionQuery query){
        SysPermission sysPermission = new SysPermission();
        BeanUtils.copyProperties(query,sysPermission);
        int count = sysPermissionDao.update(sysPermission);
        if (count > 0){
            return Result.successResult("更新成功");
        }
        return Result.failResult("更新失败");
    }

    @Override
    public Result deletePermissionByIds(String roleIds) {
        if (StringUtil.isEmpty(roleIds)){
            return Result.failResult("参数为空");
        }
        //去掉单双引号
        roleIds = roleIds.replace("\"", "");
        roleIds = roleIds.replace("\'", "");
        String idArrs [] = roleIds.split(",");
        //去除两边多出来的引号
        //判断是否存在目录以及是否存在子权限
        for (int i = 0 ; i < idArrs.length ; i++){
            SysPermission permission = sysPermissionDao.getById(Long.valueOf(idArrs[i]));
            if (permission.getChildren().size() !=0 ){
                return Result.failResult("该权限存在子权限不能删除");
            }
        }

        int count = sysPermissionDao.deleteBatch(idArrs);
        if (count > 0){
            return Result.successResult("删除成功");
        }
        return Result.failResult("删除失败");
    }


}































