package com.ucar.team.seven.tea.system.dao;

import com.ucar.team.seven.tea.common.dao.BaseDao;
import com.ucar.team.seven.tea.system.entity.SysRole;
import com.ucar.team.seven.tea.system.vo.SysRoleQuery;

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
public interface SysRoleDao extends BaseDao<SysRole> {


    /**
     * @Author yufeng.lim@ucarinc.com
     * @Description  根据条件查询列表
     * @Date 11:31 2018/9/30
     * @Param [query]
     * @return java.util.List<com.ucar.team.seven.tea.system.entity.SysRole>
     **/
    List<SysRole> listPage(SysRoleQuery query);
    /**
     * @Author yufeng.lim@ucarinc.com
     * @Description  批量删除操作
     * @Date 14:03 2018/9/30
     * @Param [idArrs] id字符串 用逗号分隔
     * @return java.lang.Integer
     **/
    Integer deleteBatch(String[] array);
    
    /**
     * @Author yufeng.lim@ucarinc.com
     * @Description  查询总数
     * @Date 15:53 2018/9/30
     * @Param []
     * @return java.lang.Long
     **/
    Long countAll();

    void deleteRolePermissionByRoleId(Long roleId);

    void saveRolePermission(List<Map<String, Long>> params);
}
