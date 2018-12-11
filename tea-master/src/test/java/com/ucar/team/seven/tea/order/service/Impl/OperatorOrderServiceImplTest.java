package com.ucar.team.seven.tea.order.service.Impl;

import com.ucar.team.seven.tea.common.enums.OrderStatusEnums;
import com.ucar.team.seven.tea.order.service.OperatorOrderService;
import com.ucar.team.seven.tea.order.vo.params.OrderParamVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mybatis.xml",
        "classpath:spring/spring-mvc.xml"})
public class OperatorOrderServiceImplTest {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OperatorOrderService operatorOrderService;

    /**
     * 测试删除订单
     */
    @Test
    public void deleteOrderTest(){
        OrderParamVo orderParamVo=new OrderParamVo();
        orderParamVo.setOrderNumber("20181012134249713a6a");
        int n=operatorOrderService.deleteOrder(orderParamVo);
        logger.info("===================================   n="+n);
    }

    /**
     * 测试订单发货
     */
//    @Test
//    public void shipOrder() {
//        int n=operatorOrderService.shipOrder("20181012134248860038",OrderStatusEnums.SHIPED.getValue());
//        logger.info("n="+n);
//    }
    /**
     * 测试订单支付
     */
//    @Test
//    public void applyOrderTest(){
//        int n=operatorOrderService.applyOrder("201810121342494700e2");
//        logger.info("n="+n);
//    }
//    /**
//     * 测试订单确认收货操作
//     */
//    @Test
//    public void confirmReceiptTest(){
//        int n=operatorOrderService.confirmReceipt("20181012134249646d3e");
//        logger.info("n="+n);
//    }


    /**
     * 同意取消订单操作
     */
    @Test
    public void agreeOrderCancelTest(){
        OrderParamVo orderParamVo=new OrderParamVo();
        orderParamVo.setOrderNumber("201810121342495472e9");
        orderParamVo.setModifyId(0L);
        int n=operatorOrderService.agreeOrderCancel(orderParamVo);
        System.out.println("n"+n);
    }

    /**
     * 申请取消订单操作
     */
    @Test
    public void applyCancelOrderTest(){
        OrderParamVo orderParamVo=new OrderParamVo();
        orderParamVo.setOrderNumber("20181012143851642ecf");
//        orderParamVo.setOrderStatus(0);
        orderParamVo.setModifyId(0L);
        int n=operatorOrderService.applyCancelOrder(orderParamVo);
        System.out.println("n"+n);
    }
}