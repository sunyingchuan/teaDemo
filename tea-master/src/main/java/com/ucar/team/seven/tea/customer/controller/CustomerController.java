package com.ucar.team.seven.tea.customer.controller;

import com.ucar.team.seven.tea.common.controller.BaseController;
import com.ucar.team.seven.tea.common.web.PageResult;
import com.ucar.team.seven.tea.common.web.Result;
import com.ucar.team.seven.tea.customer.entity.Customer;
import com.ucar.team.seven.tea.customer.service.CustomerService;
import com.ucar.team.seven.tea.customer.vo.CustomerVo;
import com.ucar.team.seven.tea.customer.vo.params.CustomerParamVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Description 终端客户Controller
 * @Author Grant(gang.zeng @ ucarinc.com)
 * @Version v1.0
 * @Date 2018/10/8 17:36
 */
@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController{

    @Resource
    CustomerService customerService;

    /**
     * @description 查询客户列表
     * @date  2018/10/9 17:09
     * @param customerParamVo   客户列表查询VO
     * @return  com.ucar.team.seven.tea.common.web.PageResult
     */
    @RequestMapping(value = "list.do", method = RequestMethod.POST)
    @ResponseBody
    public PageResult find(CustomerParamVo customerParamVo){

        List<CustomerVo> list = customerService.findPage(customerParamVo);
        return getPageResult(list, customerParamVo);
    }

    /**
     * @description 新增一个客户账号
     * @date  2018/10/9 17:08
     * @param customer 客户实体类
     * @return  com.ucar.team.seven.tea.common.web.Result
     */
    @RequestMapping("save.do")
    @ResponseBody
    public Result doSaveCustomer(Customer customer){

        Result result = customerService.insertCustomer(customer);
        return result;
    }

    /**
     * @description 启用/禁用客户账号
     * @date  2018/10/9 17:07
     * @param id       客户ID
     * @param status   账号当前状态
     * @return  com.ucar.team.seven.tea.common.web.Result
     */
    @RequestMapping("valid.do")
    @ResponseBody
    public Result updateValid(Long id, Integer status) {
        customerService.updateStatus(id, status);
        return getResult();
    }

    /**
     * @description 根据传入客户ID重置密码（123456）
     * @date  2018/10/9 17:05
     * @param id   客户ID
     * @return  com.ucar.team.seven.tea.common.web.Result
     */
    @RequestMapping("resetPwd.do")
    @ResponseBody
    public Result resetPwd(Long id){
        customerService.resetPwd(id);
        return getResult();
    }

}
