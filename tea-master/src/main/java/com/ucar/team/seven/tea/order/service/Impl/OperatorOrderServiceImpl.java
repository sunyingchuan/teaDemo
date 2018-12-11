/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @program: tea-demo
 * @Author minmin.liu(minmin.liu @ ucarinc.com)
 * @Date 2018-10-10 11:32
 * @Version 1.0
 * @Description 订单操作service
 */

package com.ucar.team.seven.tea.order.service.Impl;

import com.ucar.team.seven.tea.common.constant.CommonConstant;
import com.ucar.team.seven.tea.common.dao.BaseDao;
import com.ucar.team.seven.tea.common.enums.OrderOperatorEnums;
import com.ucar.team.seven.tea.common.enums.OrderStatusEnums;
import com.ucar.team.seven.tea.common.exception.OrderException;
import com.ucar.team.seven.tea.common.service.impl.BaseService;
import com.ucar.team.seven.tea.customer.service.CustomerService;
import com.ucar.team.seven.tea.order.dao.OrderDao;
import com.ucar.team.seven.tea.order.dao.OrderRecordDao;
import com.ucar.team.seven.tea.order.entity.Order;
import com.ucar.team.seven.tea.order.entity.OrderRecord;
import com.ucar.team.seven.tea.order.service.OperatorOrderService;
import com.ucar.team.seven.tea.order.vo.params.OrderParamVo;
import com.ucar.team.seven.tea.point.service.PointLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
public class OperatorOrderServiceImpl extends BaseService<Order> implements OperatorOrderService {

    //日志
    private final Logger logger=LoggerFactory.getLogger(this.getClass());

    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderRecordDao orderRecordDao;
    @Autowired
    PointLogService pointLogService;
    @Autowired
    CustomerService customerService;

    @Override
    public BaseDao<Order> getDao() {
        return null;
    }

    //todoed发货时间
    //shanchu发货
    @Override
    public int shipOrder(OrderParamVo orderParamVo)throws OrderException{
        orderParamVo.setOrderStatus(OrderStatusEnums.SHIPED.getValue());
        orderParamVo.setModifyId(getEmp());
        int updateCount=orderDao.updateOrderStatus(orderParamVo);
        OrderRecord orderRecord=getOrderRecord(orderParamVo,OrderOperatorEnums.SHIPED.getOperator());
        if (updateCount<=0){
            logger.info("订单发货失败！");
            throw new OrderException("ship order is fail!");
        }else {
            int insertCount=orderRecordDao.insert(orderRecord);
            if (insertCount<=0){
                logger.info("订单记录失败！");
                throw new OrderException("orderRecord: ship order is fail!");
            }
            return updateCount;
        }
    }

    /**
     * 订单支付操作 支付-》订单操作记录
     * todoED支付时间
     * @param orderParamVo
     * @return
     */
    @Override
    public int applyOrder(OrderParamVo orderParamVo)throws OrderException {
        int n=0;
        orderParamVo.setOrderStatus(OrderStatusEnums.PENDING_SHIPMENT.getValue());
        orderParamVo.setModifyId(CommonConstant.AUTO_SYS_USER_ID);
        n = orderDao.updateOrderStatus(orderParamVo);
        Order order = orderDao.queryByOrderNumber(orderParamVo.getOrderNumber());
//        扣除金额
        customerService.expend(order);
        if(n<=0){
            logger.info("订单支付失败");
            throw new OrderException("apply is fail!");
        }else {
            //订单记录
            OrderRecord orderRecord=getOrderRecord(orderParamVo,OrderOperatorEnums.PAY_SUCCESS.getOperator());
            n=orderRecordDao.insert(orderRecord);
            if (n<=0){
                logger.info("记录订单支付失败");
                throw new OrderException("orderRecord: apply orderRecord is fail!");
            }
                return n;
        }
    }

    //todoED收货时间增加积分扣除余额
    @Override
    public int confirmReceipt(OrderParamVo orderParamVo) throws OrderException {
        int updateCount=0;
        orderParamVo.setOrderStatus(OrderStatusEnums.ORDER_COMPLETED.getValue());
        orderParamVo.setModifyId(CommonConstant.AUTO_SYS_USER_ID);
        updateCount=orderDao.updateOrderStatus(orderParamVo);
        if (updateCount<=0){
            logger.info("订单确认收货失败");
            //确认收货失败抛出异常
            throw new OrderException("confirm receipt order is fail!");
        }else {
            //订单记录确认收货
            OrderRecord orderRecord=getOrderRecord(orderParamVo,OrderOperatorEnums.RECEIPT_ORDER.getOperator());
            updateCount=orderRecordDao.insert(orderRecord);
            if (updateCount<=0){
                logger.info("订单确认收货发生错误");
                throw new OrderException("orderRecord:confirm receipt orderRecord is fail!");
            }else {
                //订单记录完成
                orderRecord.setOperator(OrderOperatorEnums.ORDER_SUCCESS.getOperator());
                orderRecord.setOperator(OrderOperatorEnums.ORDER_SUCCESS.getOperator());
                updateCount=orderRecordDao.insert(orderRecord);
                if (updateCount<=0){
                    logger.info("订单完成错误！");
                    throw new OrderException("orderRecord:order success orderRecord is fail!");
                }
                //确认收货，订单完成，增加用户积分
                Order order=orderDao.queryByOrderNumber(orderParamVo.getOrderNumber());
                pointLogService.addPointLogFromOrder(order);
                return updateCount;
            }
        }
    }

    /**
     *
     * 后台同意订单取消申请状态操作
     * @param orderParamVo
     * @return
     */
    @Override
    public int agreeOrderCancel(OrderParamVo orderParamVo) throws OrderException{
        //后台改变订单的订单状态
            //得到修改者id
            orderParamVo.setModifyId(getEmp());
            //订单状态改变
            orderParamVo.setOrderStatus(OrderStatusEnums.PENDING_REFUND.getValue());
            int cancelOrderUpdate=orderDao.updateOrderStatus(orderParamVo);
            if (cancelOrderUpdate<=0){
                logger.info("同意取消订单申请失败");
                //确认收货失败抛出异常
                throw new OrderException("agree cancel order is fail!");
            }else {
                //得到订单记录
                OrderRecord orderRecord=getOrderRecord(orderParamVo,OrderOperatorEnums.AUDIT_PASSED.getOperator());
                cancelOrderUpdate=orderRecordDao.insert(orderRecord);
                if (cancelOrderUpdate<=0){
                    throw new OrderException("record: agree cancel order is fail!");
                }
                return cancelOrderUpdate;
            }
    }

    /**
     * 后台拒绝申请取消订单操作
     * @param orderParamVo
     * @return
     */
    @Override
    public int refuseOrder(OrderParamVo orderParamVo)throws OrderException {
        orderParamVo.setModifyId(getEmp());
        //订单状态改变 未支付订单拒绝订单状态改为已发货
        orderParamVo.setOrderStatus(OrderStatusEnums.SHIPED.getValue());
        int refuseUpdate=orderDao.updateOrderStatus(orderParamVo);
        if (refuseUpdate<=0){
            logger.info("拒绝取消订单申请失败");
            //确认收货失败抛出异常
            throw new OrderException("refuse cancel order is fail!");
        }else {
            //得到订单记录审核不通过
            OrderRecord orderRecord=getOrderRecord(orderParamVo,OrderOperatorEnums.AUDIT_NOT_PASSED.getOperator());
            refuseUpdate=orderRecordDao.insert(orderRecord);
            if (refuseUpdate<=0){
                throw new OrderException("record: refuse cancel order is fail!");
            }
            return refuseUpdate;
        }
    }

    /**
     * 后台退款
     * 订单状态改为已取消，记录表记录，订单总价返回钱包
     * @param orderParamVo
     * @return
     */
    @Override
    public int refundOrder(OrderParamVo orderParamVo) throws OrderException {
        orderParamVo.setModifyId(getEmp());
        //订单状态改变 未支付订单拒绝订单状态改为已发货
        orderParamVo.setOrderStatus(OrderStatusEnums.CANCELED.getValue());
        int refundUpdate=orderDao.updateOrderStatus(orderParamVo);
        if (refundUpdate<=0){
            logger.info("订单退款失败！");
            //确认收货失败抛出异常
            throw new OrderException("订单退款失败，请重新操作！");
        }else {
            //得到订单记录退款
            OrderRecord orderRecord=getOrderRecord(orderParamVo,OrderOperatorEnums.CANCELED_ORDER.getOperator());
            refundUpdate=orderRecordDao.insert(orderRecord);
            if (refundUpdate<=0){
                throw new OrderException("record: refund order is fail!");
            }
//            退回余额
            Order order=orderDao.queryByOrderNumber(orderParamVo.getOrderNumber());
            customerService.refund(order);
            return refundUpdate;
        }
    }

    /**
     * 得到orderRecord
     * 设置操作，操作备注，创建者，修改者，订单号
     * @param orderParamVo
     * @param operator
     * @return
     */
    public OrderRecord getOrderRecord(OrderParamVo orderParamVo,String operator){
        OrderRecord orderRecord=new OrderRecord();
        orderRecord.setOperator(operator);
        orderRecord.setOperatorRemark(operator);
        orderRecord.setCreateEmp(orderParamVo.getModifyId());
        orderRecord.setModifyEmp(orderParamVo.getModifyId());
        orderRecord.setOrderNumber(orderParamVo.getOrderNumber());
        return orderRecord;
    }

    /**
     * 前端提交申请-》改变订单状态
     * 分为未支付订单取消和已支付订单取消，故如果是未支付订单须传入未支付的状态进行判断
     * 如果不为空，则为未支付状态
     */
    @Override
    public int applyCancelOrder(OrderParamVo orderParamVo)throws OrderException {
        if (orderParamVo.getOrderStatus() != null) {
            //未支付订单-》改变订单状态为申请和同意，记录为申请和同意
            //申请取消订单
            orderParamVo.setModifyId(CommonConstant.AUTO_SYS_USER_ID);
            orderParamVo.setOrderStatus(OrderStatusEnums.APPLY_FOR_CANCEL.getValue());
            int cancelOrder=orderDao.updateOrderStatus(orderParamVo);
            if (cancelOrder<=0){
                throw new OrderException("apply cancel order is fail！");
            }else {
                OrderRecord orderRecord = getOrderRecord(orderParamVo,OrderOperatorEnums.APPLY_CANCEL.getOperator());
                cancelOrder = orderRecordDao.insert(orderRecord);
                if (cancelOrder <= 0) {
                    throw new OrderException("record: apply cancel order is fail!");
                }
                //同意取消订单
                orderParamVo.setOrderStatus(OrderStatusEnums.CANCELED.getValue());
                cancelOrder=orderDao.updateOrderStatus(orderParamVo);
                if (cancelOrder<=0){
                    throw new OrderException("agree cancel order is fail!");
                }else {
                    orderRecord =getOrderRecord(orderParamVo,OrderOperatorEnums.CANCELED_ORDER.getOperator());
                    cancelOrder=orderRecordDao.insert(orderRecord);
                    if (cancelOrder <= 0) {
                        throw new OrderException("record: agree cancel order is fail!");
                    }
                    return cancelOrder;
                }
            }
        } else {
            //后台改变订单的订单状态
            orderParamVo.setModifyId(CommonConstant.AUTO_SYS_USER_ID);
            orderParamVo.setOrderStatus(OrderStatusEnums.APPLY_FOR_CANCEL.getValue());
            int cancelOrderUpdate = orderDao.updateOrderStatus(orderParamVo);
            if (cancelOrderUpdate <= 0) {
                logger.info("取消订单申请失败");
                //确认收货失败抛出异常
                throw new OrderException("apply cancel order is fail!");
            } else {
                //订单记录
                OrderRecord orderRecord = getOrderRecord(orderParamVo,OrderOperatorEnums.APPLY_CANCEL.getOperator());
                cancelOrderUpdate = orderRecordDao.insert(orderRecord);
                if (cancelOrderUpdate <= 0) {
                    throw new OrderException("record: apply cancel order is fail!");
                }
                return cancelOrderUpdate;
            }
        }
    }

    /**
     * 前端订单删除+记录
     * @param orderParamVo
     * @return
     */
    @Override
    public int deleteOrder(OrderParamVo orderParamVo) throws OrderException{
        orderParamVo.setModifyId(CommonConstant.AUTO_SYS_USER_ID);
        int deleteUpdate=orderDao.deleteOrder(orderParamVo);
        if (deleteUpdate<=0){
            throw new OrderException("delete order is fail!");
        }else {
            //订单记录删除
            OrderRecord orderRecord = getOrderRecord(orderParamVo,OrderOperatorEnums.ORDER_DELETE.getOperator());
            deleteUpdate = orderRecordDao.insert(orderRecord);
            if (deleteUpdate <= 0) {
                throw new OrderException("record: delete order is fail!");
            }
            return deleteUpdate;
        }
    }

    @Override
    public int updateOrderPirce(OrderParamVo orderParamVo) throws OrderException{
        orderParamVo.setModifyId(getEmp());
        int updateCount = orderDao.updateOrderPirce(orderParamVo);
        if (updateCount<=0){
            throw new OrderException("updateOrder is fail!");
        }else {
            //订单记录删除
            OrderRecord orderRecord = getOrderRecord(orderParamVo,OrderOperatorEnums.UPDATE_DELETE.getOperator());
            updateCount = orderRecordDao.insert(orderRecord);
            if (updateCount <= 0) {
                throw new OrderException("record: delete order is fail!");
            }
            return updateCount;
        }
    }

}
