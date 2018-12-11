package com.ucar.team.seven.tea.order.service;

import com.ucar.team.seven.tea.order.entity.Order;
import com.ucar.team.seven.tea.order.vo.OrderDetailVo;
import com.ucar.team.seven.tea.order.vo.OrderVo;
import com.ucar.team.seven.tea.order.vo.params.GoodsOrderParamVo;
import com.ucar.team.seven.tea.order.vo.params.OrderParamVo;
import com.ucar.team.seven.tea.order.vo.params.OrderStatusParamVo;

import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @program: tea-demo
 * @Author minmin.liu(minmin.liu @ ucarinc.com)
 * @Date 2018-10-08 17:59
 * @Version 1.0
 * @Description 订单的service接口
 */

public interface OrderService {
    /**
     * 查询所有用户的订单
     * @return
     */
    public List<OrderVo> queryAllUserOrder(OrderParamVo orderParamVo);
    /**
     * 新增客户订单
     *
     */
    public int addOrder(Order order, List<GoodsOrderParamVo> goodsOrderParamVos);

    /**
     * 通过订单号查询订单
     * @param orderParamVo
     * @return
     */
    public OrderVo selectByOrderNumber(OrderParamVo orderParamVo);

    /**
     * 通过用户名查询订单
     * @param orderParamVo
     * @param userName
     * @return
     */
    public List<OrderVo> queryOrdersByUser(OrderParamVo orderParamVo,String userName);


//    以下为 客户通过订单的状态查询订单列表


    /**
     * 订单不同状态查询 待收货订单查询 待发货订单状态查询 前端未支付订单状态查询 已完成订单查询 处理中订单
     * @param orderStatusParamVo
     * @return
     */
    public List<OrderVo> queryOrderByStatus(OrderStatusParamVo orderStatusParamVo);


    /**
     * 得到订单详情
     * @param orderParamVo
     * @return
     */
    public OrderDetailVo orderDetail(OrderParamVo orderParamVo);

    /**
     * 客户查询自己所有的订单，不包括删除掉的订单
     * 需要用户id
     * @param orderParamVo
     * @return
     */
    public  List<OrderVo> customerOrder(OrderParamVo orderParamVo);

}
