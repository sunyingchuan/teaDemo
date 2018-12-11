package com.ucar.team.seven.tea.customer.dao;

import com.ucar.team.seven.tea.common.dao.BaseDao;
import com.ucar.team.seven.tea.customer.entity.Customer;
import com.ucar.team.seven.tea.customer.vo.CustomerVo;
import com.ucar.team.seven.tea.customer.vo.params.CustomerParamVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Description 终端客户Dao
 * @Author Grant(gang.zeng @ ucarinc.com)
 * @Version v1.0
 * @Date 2018/10/8 17:37
 */

public interface CustomerDao extends BaseDao<Customer> {

    /**
     * @description 更新状态status
     * @date  2018/10/9 17:14
     * @param id
     * @param status
     * @return  void
     */
    void updateStatus(@Param("id") Long id, @Param("status") Integer status);

    /**
     * @description 查询客户列表
     * @date  2018/10/9 17:14
     * @param customerParamVo
     * @return  java.util.List<com.ucar.team.seven.tea.customer.vo.CustomerVo>
     */
    List<CustomerVo> findPage(@Param("params") CustomerParamVo customerParamVo);

    /**
     * @description 获取查询数目
     * @date  2018/10/9 17:14
     * @param customerParamVo
     * @return  java.lang.Long
     */
    Long getCount(@Param("params") CustomerParamVo customerParamVo);

    /**
     * @description 重置密码
     * @date  2018/10/9 17:14
     * @param customer
     * @return  void
     */
    void resetPwd(@Param("params")Customer customer);

    /**
     * @description 根据用户名查找用户
     * @date  2018/10/10 11:22
     * @param customer
     * @return  com.ucar.team.seven.tea.customer.entity.Customer
     */
    Customer getByUserName(Customer customer);

    /**
     * @description 根据微信ID查找用户
     * @date  2018/10/10 13:52
     * @param customer
     * @return  com.ucar.team.seven.tea.customer.entity.Customer
     */
    Customer getByWxId(Customer customer);
}
