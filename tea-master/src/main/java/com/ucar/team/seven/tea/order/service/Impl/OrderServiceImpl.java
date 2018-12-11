/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @program: tea-demo
 * @Author minmin.liu(minmin.liu @ ucarinc.com)
 * @Date 2018-10-08 17:59
 * @Version 1.0
 * @Description orderService的实现类
 */

package com.ucar.team.seven.tea.order.service.Impl;

import com.ucar.team.seven.tea.common.constant.CommonConstant;
import com.ucar.team.seven.tea.common.dao.BaseDao;
import com.ucar.team.seven.tea.common.enums.OrderOperatorEnums;
import com.ucar.team.seven.tea.common.enums.OrderStatusEnums;
import com.ucar.team.seven.tea.common.exception.OrderException;
import com.ucar.team.seven.tea.common.service.impl.BaseService;
import com.ucar.team.seven.tea.common.util.DateUtil;
import com.ucar.team.seven.tea.goods.dao.GoodsDao;
import com.ucar.team.seven.tea.goods.vo.GoodsVo;
import com.ucar.team.seven.tea.order.dao.GoodsOrderDao;
import com.ucar.team.seven.tea.order.dao.OrderDao;
import com.ucar.team.seven.tea.order.dao.OrderRecordDao;
import com.ucar.team.seven.tea.order.entity.GoodsOrder;
import com.ucar.team.seven.tea.order.entity.Order;
import com.ucar.team.seven.tea.order.entity.OrderRecord;
import com.ucar.team.seven.tea.order.service.OrderService;
import com.ucar.team.seven.tea.order.vo.OrderDetailVo;
import com.ucar.team.seven.tea.order.vo.OrderRecordVo;
import com.ucar.team.seven.tea.order.vo.OrderVo;
import com.ucar.team.seven.tea.order.vo.params.GoodsOrderParamVo;
import com.ucar.team.seven.tea.order.vo.params.OrderParamVo;
import com.ucar.team.seven.tea.order.vo.params.OrderStatusParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl extends BaseService<Order> implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private GoodsOrderDao goodsOrderDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private OrderRecordDao orderRecordDao;

    //TODO分页查询所有用户的订单
    @Override
    public List<OrderVo> queryAllUserOrder(OrderParamVo orderParamVo) {
        Long count=orderDao.allCount();
        orderParamVo.setRowCount(count);
        orderParamVo.setStartIndex((orderParamVo.getPageCurrent()-1)*orderParamVo.getPageSize());
        return orderDao.queryAllUserOrder(orderParamVo);
    }

    //todomodify_emp新建时为0
    @Override
    public int addOrder(Order order, List<GoodsOrderParamVo> goodsOrderVos) throws OrderException {
        //增加一条订单并在关系表和记录表中增添一条记录
        //商品订单关系对象
        GoodsOrder goodsOrder=new GoodsOrder();
        OrderRecord orderRecord=new OrderRecord();
        //生成订单号
        order.setOrderNumber(orderNumberGenerate());
        BigDecimal orderPrice=new BigDecimal(0);
        for (GoodsOrderParamVo goodsOrderParamVo:goodsOrderVos){
//            得到商品对象
            GoodsVo goods=goodsDao.getByGoodsId(goodsOrderParamVo.getGoodsId());
            BigDecimal quantity=new BigDecimal(goodsOrderParamVo.getQuantity());
            //单价商品的总价
            BigDecimal goodsPrice=goods.getPrice().multiply(quantity);
            //订单总价
            orderPrice = orderPrice.add(goodsPrice);
        }
        order.setPrice(orderPrice);
        //订单为未支付
        order.setStatus(OrderStatusEnums.UNPAID.getValue());
        order.setCreateEmp(CommonConstant.AUTO_SYS_USER_ID);
        order.setModifyEmp(CommonConstant.AUTO_SYS_USER_ID);
        //增加一条订单
        int n=orderDao.insert(order);
        if (n<=0){
            throw  new OrderException("add order is fail!");
        }else {
            // 在关系表中添加记录
            for (GoodsOrderParamVo goodsOrderParamVo:goodsOrderVos){
                //得到商品对象
                GoodsVo goods=goodsDao.getByGoodsId(goodsOrderParamVo.getGoodsId());
                Order queryOrder=orderDao.queryByOrderNumber(order.getOrderNumber());
                goodsOrder.setGoodsId(goodsOrderParamVo.getGoodsId());
                goodsOrder.setOrderId(queryOrder.getId());
                goodsOrder.setUnitPrice(goods.getPrice());
                goodsOrder.setQuantity(goodsOrderParamVo.getQuantity());
                goodsOrder.setCreateEmp(0L);
                goodsOrder.setModifyEmp(0L);
                //得到单价商品的总价
                BigDecimal totalPrice=goods.getPrice().multiply(new BigDecimal(goodsOrderParamVo.getQuantity()));
                goodsOrder.setTotalPrice(totalPrice);
                n=goodsOrderDao.insert(goodsOrder);
                if (n<=0){
                    throw new OrderException("goodOrder: add order is fail!");}
            }
                //在记录表中添加一条记录
                orderRecord.setOrderNumber(order.getOrderNumber());
                orderRecord.setOperator(OrderOperatorEnums.ORDER.getOperator());
                orderRecord.setOperatorRemark("用户下单");
                orderRecord.setCreateEmp(0L);
                orderRecord.setModifyEmp(0L);
                n=orderRecordDao.insert(orderRecord);
                if (n<=0){
                    throw new OrderException("Record:add order is fail!");
                }
                return n;
            }
        }

    /**
     * 生成订单号
     * @return
     */
    private String orderNumberGenerate(){
        //生成订单号
        Timestamp timestamp=DateUtil.getNowTimestamp();
        //16位
        String timeStr=DateUtil.timestamp2Str(timestamp,"yyyyMMddHHmmssSSS");

        //通过uuid生成3位随机数
        String result=UUID.randomUUID().toString().replace("-", "").toLowerCase().substring(1,3);
        String orderNumber="T"+timeStr+result;
        return orderNumber;
    }


    @Override
    public OrderVo selectByOrderNumber(OrderParamVo orderParamVo){
        OrderVo orderVo=orderDao.selectByOrderNumber(orderParamVo);
        return orderVo;
    }

    @Override
    public List<OrderVo> queryOrdersByUser(OrderParamVo orderParamVo, String userName) {
        List<OrderVo> orderVoList=orderDao.queryOrdersByUser(orderParamVo,userName);
        return orderVoList;
    }

    @Override
    public List<OrderVo> queryOrderByStatus(OrderStatusParamVo orderStatusParamVo) {
        List<OrderVo> orderVoList=orderDao.queryOrderByStatus(orderStatusParamVo);
        return orderVoList;
    }

    @Override
    public OrderDetailVo orderDetail(OrderParamVo orderParamVo) {
        List<OrderRecordVo>orderRecordVoList=orderRecordDao.queryByOrderNumber(orderParamVo);
        OrderDetailVo orderDetailVo= orderDao.orderDetail(orderParamVo);
        orderDetailVo.setOrderRecordVos(orderRecordVoList);
        return orderDetailVo;
    }

    @Override
    public List<OrderVo> customerOrder(OrderParamVo orderParamVo) {
        Long count=orderDao.customerAllCount(orderParamVo);
        orderParamVo.setRowCount(count);
        orderParamVo.setStartIndex((orderParamVo.getPageCurrent()-1)*orderParamVo.getPageSize());
        return orderDao.customerOrder(orderParamVo);
    }


    @Override
    public BaseDao<Order> getDao() {
        return orderDao;
    }

}
