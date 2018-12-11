/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @program: tea-demo
 * @Author minmin.liu(minmin.liu @ ucarinc.com)
 * @Date 2018-10-08 17:51
 * @Version 1.0
 * @Description 订单controller
 */

package com.ucar.team.seven.tea.order.controller;

import com.ucar.team.seven.tea.common.controller.BaseController;
import com.ucar.team.seven.tea.common.web.PageResult;
import com.ucar.team.seven.tea.common.web.Result;
import com.ucar.team.seven.tea.order.service.OrderService;
import com.ucar.team.seven.tea.order.vo.OrderDetailVo;
import com.ucar.team.seven.tea.order.vo.OrderVo;
import com.ucar.team.seven.tea.order.vo.params.OrderParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
//@Api(value = "订单相关业务的接口",tags = "订单相关业务的controller")
@RequestMapping("order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    /**
     * 获取订单详情
     * @param orderParamVo
     * @return
     */
    @RequestMapping("orderDetail")
    @ResponseBody
    public Result orderDetail(OrderParamVo orderParamVo){
        OrderDetailVo orderDetail=orderService.orderDetail(orderParamVo);
        List<OrderDetailVo> orderDetailVoList=new ArrayList<OrderDetailVo>();
        orderDetailVoList.add(orderDetail);
        PageResult result = getPageResult(orderDetailVoList,orderParamVo);
        result.setTotal((long) result.getRecords().size());
        return result;
//       return getEntityResult(orderDetail);
    }
    /**
     * 页面跳转
     * @return
     */
    @RequestMapping("list.do")
    public String listUI() {
        return "order/order_list";
    }

    /**
     * 查询所有用户的订单
     * @param orderParamVo
     * @return
     */
    @RequestMapping (value = "queryAllUserOrder",method = RequestMethod.POST)
    @ResponseBody
    public Result queryAllUserOrder(OrderParamVo orderParamVo){
        List<OrderVo> orderVos=orderService.queryAllUserOrder(orderParamVo);
        return getPageResult(orderVos,orderParamVo);
    }



}
