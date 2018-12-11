package com.ucar.team.seven.tea.order.service;

import com.ucar.team.seven.tea.order.vo.params.OrderParamVo;

public interface OperatorOrderService {
    /**
     * 订单发货操作
     * 发货并记录
     * @param orderParamVo
     *
     *
     * @return
     */
    public int shipOrder(OrderParamVo orderParamVo);

    /**
     * 订单支付操作
     * @param orderParamVo
     * @return
     */
    public int applyOrder(OrderParamVo orderParamVo);

    /**
     * 订单确认收货，订单完成
     * @param orderParamVo
     * @return
     */
    public int confirmReceipt(OrderParamVo orderParamVo);

    /**
     * 后台订单状态操作
     * 订单取消申请后台同意操作
     * @return
     */
    int agreeOrderCancel(OrderParamVo orderParamVo);

    /**
     * 后台拒绝取消订单申请操作
     * @param orderParamVo
     * @return
     */
    int refuseOrder(OrderParamVo orderParamVo);

    /**
     * 后台退款到用户账号上
     * @param orderParamVo
     * @return
     */
    int refundOrder(OrderParamVo orderParamVo);

    /**
     * 前台用户提交取消订单申请
     * @param orderParamVo
     * @return
     */
    int applyCancelOrder(OrderParamVo orderParamVo);

    /**
     * 前台用户订单删除
     * @param orderParamVo
     * @return
     */
    int deleteOrder(OrderParamVo orderParamVo);

    /**
     * 修改订单总价
     * @param orderParamVo
     * @return
     */
    int updateOrderPirce(OrderParamVo orderParamVo);


}
