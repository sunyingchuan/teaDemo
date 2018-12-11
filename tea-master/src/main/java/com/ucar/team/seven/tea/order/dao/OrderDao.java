/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @program: tea-demo
 * @Author minmin.liu(minmin.liu @ ucarinc.com)
 * @Date 2018-10-08 15:08
 * @Version 1.0
 * @Description 订单Dao
 */

package com.ucar.team.seven.tea.order.dao;

import com.ucar.team.seven.tea.common.dao.BaseDao;
import com.ucar.team.seven.tea.order.entity.Order;
import com.ucar.team.seven.tea.order.vo.OrderDetailVo;
import com.ucar.team.seven.tea.order.vo.OrderVo;
import com.ucar.team.seven.tea.order.vo.params.OrderParamVo;
import com.ucar.team.seven.tea.order.vo.params.OrderStatusParamVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao extends BaseDao<Order> {

    /**
     * 分页查询所有的订单
     * @param orderParamVo
     * @return
     */
    List<OrderVo> queryAllUserOrder(@Param("params") OrderParamVo orderParamVo);

    /**
     * 通过订单号查询订单 orderVo
     * @param orderParamVo
     * @return
     */
    OrderVo selectByOrderNumber(@Param("params") OrderParamVo orderParamVo);





    /**
     * 通过订单号查询订单实体
     * @param orderNumber
     * @return
     */
    Order queryByOrderNumber(@Param("orderNumber") String orderNumber);


    /**
     * todoed
     * 订单状态改变
     * 订单申请取消订单
     */
    int updateOrderStatus(@Param("params") OrderParamVo orderParamVo);

    /**
     * 根据用户名获取订单列表 分页查询
     */
    List<OrderVo> queryOrdersByUser(@Param("params") OrderParamVo orderParamVo,@Param("userName") String userName);

    /**
     * 通过订单不同状态查询订单 已发货 待发货订单查询 未支付订单查询
     * @param orderStatusParamVo
     * @return
     */
    List<OrderVo> queryOrderByStatus(@Param("params") OrderStatusParamVo orderStatusParamVo);

    /**
     * 查询所有订单条数
     * @return
     */
    public Long allCount();

    /**
     * 订单详情
     * @param orderParamVo
     * @return
     */
    public OrderDetailVo orderDetail(@Param("params") OrderParamVo orderParamVo);

    /**
     * 客户删除订单
     * @param orderParamVo
     * @return
     */
    int deleteOrder(@Param("params") OrderParamVo orderParamVo);

    /**
     * 客户所有订单
     * @param orderParamVo
     * @return
     */
    List<OrderVo> customerOrder(@Param("params") OrderParamVo orderParamVo);

    /**
     * 用户所有订单数
     * @return
     */
    Long customerAllCount(@Param("params") OrderParamVo orderParamVo);

    /**
     * 修改订单总价
     * @param orderParamVo
     * @return
     */
    int updateOrderPirce(@Param("params") OrderParamVo orderParamVo);
}
