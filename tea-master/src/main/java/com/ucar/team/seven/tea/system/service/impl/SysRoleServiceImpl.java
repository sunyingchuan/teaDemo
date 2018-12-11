package com.ucar.team.seven.tea.system.service.impl;

import com.ucar.team.seven.tea.common.dao.BaseDao;
import com.ucar.team.seven.tea.common.service.impl.BaseService;
import com.ucar.team.seven.tea.common.util.StringUtil;
import com.ucar.team.seven.tea.common.web.PageResult;
import com.ucar.team.seven.tea.common.web.Result;
import com.ucar.team.seven.tea.system.dao.SysRoleDao;
import com.ucar.team.seven.tea.system.entity.SysRole;
import com.ucar.team.seven.tea.system.service.SysRoleService;
import com.ucar.team.seven.tea.system.vo.SysRoleQuery;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @描述
 * @参数 $
 * @返回值 $
 * @创建人 yufeng.lin@ucarinc.com
 * @创建时间 $
 * @修改人和其它信息
 */
@Service("sysRoleService")
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl extends BaseService<SysRole> implements SysRoleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleServiceImpl.class);
    /**
     * 获取dao
     *
     * @return
     */
    @Resource
    SysRoleDao sysRoleDao;

    @Override
    public BaseDao<SysRole> getDao() {
        return sysRoleDao;
    }


    @Override
    public PageResult listPage(SysRoleQuery query) {
        if (query == null) {
            LOGGER.info("listPage 查询参数为空");
            return PageResult.failePageResult("查询参数为空");
        }
        List<SysRole> list = sysRoleDao.listPage(query);
        Long total = sysRoleDao.countAll();
        return PageResult.successPageResult(total, list);
    }

    //todo:考虑统一的更新人操作人等如何操作
    @Override
    public Result addRole(SysRoleQuery query) throws InvocationTargetException, IllegalAccessException {
        if (query == null) {
            return Result.failResult("插入参数为空");
        }
        if (!StringUtils.isNotEmpty(query.getName())) {
            return Result.failResult("角色名不为空");
        }
        if (!StringUtils.isNotEmpty(query.getDescription())) {
            return Result.failResult("描述不为空");
        }
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(query, sysRole);
        //验证时间，防止插入失败
        sysRole = setNullDate(sysRole);
        int count = sysRoleDao.insert(sysRole);
        if (count > 0) {
            return Result.successResult("插入成功");
        }
        return Result.failResult("database插入失败");
    }


    /**
     * 当时间为空设置时间当前时间
     *
     * @param obj
     */
    private SysRole setNullDate(SysRole obj) {
        if (obj.getCreateTime() == null) {
            obj.setCreateTime(new Date());
        }
        if (obj.getModifyTime() == null) {
            obj.setModifyTime(new Date());
        }
        return obj;
    }

    @Override
    public Result updateRole(SysRoleQuery query) throws InvocationTargetException, IllegalAccessException {
        if (query == null || query.getId() == null || query.getId() == 0) {
            return Result.failResult("参数为空更新失败");
        }
        if (!StringUtils.isNotEmpty(query.getName())) {
            return Result.failResult("角色名不为空");
        }
        if (!StringUtils.isNotEmpty(query.getDescription())) {
            return Result.failResult("描述不为空");
        }
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(query, sysRole);
        //验证时间，防止插入失败
        sysRole = setNullDate(sysRole);
        int count = sysRoleDao.update(sysRole);
        if (count > 0) {
            return Result.successResult("更新成功");
        }
        return Result.failResult("更新失败");
    }
    
    /**
     * @Author yufeng.lim@ucarinc.com
     * @Description  验证是否是super管理员
     * @Date 17:47 2018/10/10
     * @Param []
     * @return boolean
     **/
    private boolean validateIsSuperAdmin(String[] idArrs ){
        for (String idStr : idArrs){
            SysRole role = sysRoleDao.getById(Long.valueOf(idStr));
            if (role.getId() == 1 || "超级管理员".equals(role.getName())){
                return  true;
            }
        }
        return false;
    }

    @Override
    public Result deleteRoleByIds(String roleIds) {
        if (StringUtil.isEmpty(roleIds)) {
            return Result.failResult("参数为空");
        }
        //去掉单双引号
        roleIds = roleIds.replace("\"", "");
        roleIds = roleIds.replace("\'", "");
        String idArrs[] = roleIds.split(",");
        if (validateIsSuperAdmin(idArrs)){
            return Result.failResult("超级管理员不可删除");
        }
        //去除两边多出来的引号
        int count = sysRoleDao.deleteBatch(idArrs);
        if (count > 0) {
            return Result.successResult("删除成功");
        }
        return Result.failResult("删除失败");
    }

    @Override
    public void saveRolePermission(Long roleId, String permissionIdsStr) {
        // 解绑
        this.sysRoleDao.deleteRolePermissionByRoleId(roleId);

        if (StringUtils.isNotEmpty(permissionIdsStr)) {
            // 绑定
            String[] permissionIds = permissionIdsStr.split(",");
            List<Map<String, Long>> params = new ArrayList<>(permissionIds.length);
            Map<String, Long> param = null;
            for (String permissionId : permissionIds) {
                param = new HashMap<>(2);
                param.put("roleId", roleId);
                param.put("permissionId", Long.valueOf(permissionId));
                params.add(param);
            }

            this.sysRoleDao.saveRolePermission(params);
        }
    }


}










