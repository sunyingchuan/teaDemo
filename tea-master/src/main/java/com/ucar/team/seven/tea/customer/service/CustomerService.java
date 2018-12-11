package com.ucar.team.seven.tea.customer.service;

import com.ucar.team.seven.tea.common.exception.ServiceException;
import com.ucar.team.seven.tea.common.service.IBaseService;
import com.ucar.team.seven.tea.common.web.Result;
import com.ucar.team.seven.tea.customer.entity.Customer;
import com.ucar.team.seven.tea.customer.vo.CustomerVo;
import com.ucar.team.seven.tea.customer.vo.params.CustomerParamVo;
import com.ucar.team.seven.tea.order.entity.Order;

import java.math.BigDecimal;
import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Description 终端客户service接口
 * @Author Grant(gang.zeng @ ucarinc.com)
 * @Version v1.0
 * @Date 2018/10/8 17:40
 */
public interface CustomerService extends IBaseService<Customer>{


    /**
     * @description 新增一个用户
     * @date  2018/10/10 10:49
     * @param customer 用户实体
     * @return  Result
     */
    Result insertCustomer(Customer customer);

    /**
     * @description 启用/禁用账号
     * @date  2018/10/9 17:11
     * @param id      主键ID
     * @param status  状态值
     * @return  void
     */
    void updateStatus(Long id, Integer status);

    /**
     * @description 查询客户列表
     * @date  2018/10/9 17:11
     * @param customerParamVo   客户列表查询VO
     * @return  java.util.List<com.ucar.team.seven.tea.customer.vo.CustomerVo>
     */
    List<CustomerVo> findPage(CustomerParamVo customerParamVo);

    /**
     * @description 获取查询总数目
     * @date  2018/10/9 17:11
     * @param customerParamVo   客户列表查询VO
     * @return  java.lang.Long
     */
    Long getCount(CustomerParamVo customerParamVo);

    /**
     * @description 重置密码
     * @date  2018/10/9 17:11
     * @param id  主键ID
     * @return  void
     */
    void resetPwd(Long id);

    /**
     * @description 根据用户名查找用户
     * @date  2018/10/10 11:20
     * @param customer
     * @return  com.ucar.team.seven.tea.customer.entity.Customer
     */
    Customer getByUserName(Customer customer);

    /**
     * @description 根据微信ID查找用户
     * @date  2018/10/10 11:20
     * @param customer
     * @return  com.ucar.team.seven.tea.customer.entity.Customer
     */
    Customer getByWxId(Customer customer);

    /**
     * @description 根据用户ID充值余额
     * @date  2018/10/11 10:23
     * @param customerId 用户ID
     * @param figure    充值金额
     * @return  com.ucar.team.seven.tea.common.web.Result
     */
    Result recharge(Long customerId , BigDecimal figure);

    /**
     * @description 根据用户ID 扣除余额
     * @date  2018/10/11 10:23
     * @param Order 订单
     * @return  com.ucar.team.seven.tea.common.web.Result
     */
    Result expend(Order order);

    /**
     * @description 根据用户ID 退款
     * @date  2018/10/11 10:23
     * @param order    订单
     * @return  com.ucar.team.seven.tea.common.web.Result
     */
    Result refund(Order order);

    /**
     * @description 根据用户ID增加积分
     * @date  2018/10/11 10:23
     * @param customerId 用户ID
     * @param figure    增加积分数
     * @return  com.ucar.team.seven.tea.common.web.Result
     */
    Result pointAdd(Long customerId , Integer figure);

    /**
     * @description 根据用户ID批量增加积分
     * @date  2018/10/11 10:23
     * @param customerIdArray  用户ID数组
     * @param figure    增加积分数
     * @return  com.ucar.team.seven.tea.common.web.Result
     */
    void pointAdd(Long[] customerIdArray , Integer figure) throws ServiceException;

    /**
     * @description 根据用户ID 扣除积分
     * @date  2018/10/11 10:23
     * @param customerId 用户ID
     * @param figure    减少积分数
     * @return  com.ucar.team.seven.tea.common.web.Result
     */
    Result pointSub(Long customerId , Integer figure);
}
