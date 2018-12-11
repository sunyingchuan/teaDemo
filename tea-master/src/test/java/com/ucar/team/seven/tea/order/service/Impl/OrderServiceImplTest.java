package com.ucar.team.seven.tea.order.service.Impl;

import com.ucar.team.seven.tea.common.util.DateUtil;
import com.ucar.team.seven.tea.goods.dao.GoodsDao;
import com.ucar.team.seven.tea.order.dao.OrderDao;
import com.ucar.team.seven.tea.order.dao.OrderRecordDao;
import com.ucar.team.seven.tea.order.entity.Order;
import com.ucar.team.seven.tea.order.service.OperatorOrderService;
import com.ucar.team.seven.tea.order.service.OrderService;
import com.ucar.team.seven.tea.order.vo.GoodsOrderVo;
import com.ucar.team.seven.tea.order.vo.OrderDetailVo;
import com.ucar.team.seven.tea.order.vo.OrderRecordVo;
import com.ucar.team.seven.tea.order.vo.OrderVo;
import com.ucar.team.seven.tea.order.vo.params.GoodsOrderParamVo;
import com.ucar.team.seven.tea.order.vo.params.OrderParamVo;
import com.ucar.team.seven.tea.order.vo.params.OrderStatusParamVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mybatis.xml",
        "classpath:spring/spring-mvc.xml"})
public class OrderServiceImplTest {

    private final Logger logger=LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderService orderService;
    @Autowired
    private OperatorOrderService operatorOrderService;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderRecordDao orderRecordDao;

    /**
     * 测试dao订单多少条记录
     */
    @Test
    public void AllCountTest(){
        Long i=orderDao.allCount();
        System.out.println("------------------------");
        System.out.println(i);
        System.out.println("--------------------------");
    }

    /**
     * 测试用户订单多少条记录
     */
    @Test
    public void customerAllCountTest(){
        OrderParamVo orderParamVo=new OrderParamVo();
        orderParamVo.setUserId(25L);
        Long i=orderDao.customerAllCount(orderParamVo);
        System.out.println("------------------------");
        System.out.println(i);
        System.out.println("--------------------------");
    }

    @Test
    public void goodsOrderTest(){
        List<GoodsOrderVo> goodsOrderVos=goodsDao.selectGoodsOrderByOrderId(30);
        System.out.println("--------------------");
        for (GoodsOrderVo goodsOrderVo:goodsOrderVos){
            System.out.println(goodsOrderVo.toString());
        }
        System.out.println("-----------------------");
    }

    /**
     * 得到订单详情
     */
    @Test
    public void orderDetailTest(){
        OrderParamVo orderParamVo=new OrderParamVo();
        orderParamVo.setOrderNumber("20181012134248860038");
        OrderDetailVo orderDetailVo=orderDao.orderDetail(orderParamVo);
        System.out.println("--------------------------------");
        System.out.println(orderDetailVo.toString());
        System.out.println("---------------------------------");
    }

    /**
     * 通过订单号查询订单记录
     */
    @Test
    public void queryByOrderNumberTest(){
        OrderParamVo orderParamVo=new OrderParamVo();
        orderParamVo.setOrderNumber("20181012134249646d3e");
        List<OrderRecordVo> orderRecordVoList= orderRecordDao.queryByOrderNumber(orderParamVo);
        System.out.println("-------------------------------------");
        for (OrderRecordVo orderRecordVo:orderRecordVoList){
            System.out.println(orderRecordVo.toString());
        }
        System.out.println("-------------------------------------");
    }

    /**
     * 订单详情测试
     */
    @Test
    public void orderDetailServiceTest(){
        OrderParamVo orderParamVo=new OrderParamVo();
        orderParamVo.setOrderNumber("20181012134248860038");
        OrderDetailVo orderDetailVo=orderService.orderDetail(orderParamVo);
        System.out.println("--------------------------------");
        System.out.println(orderDetailVo.toString());
        System.out.println("---------------------------------");
    }
    /**
     * 获取所有用户的所有订单测试类
     */
    @Test
    public void queryAllUserOrderTest() {
        OrderParamVo orderParamVo=new OrderParamVo();
        orderParamVo.setPageCurrent(1L);
        orderParamVo.setPageSize(10L);
        //orderParamVo.setUserName("清");
        orderParamVo.setOrderStatus(1);
        System.out.println("-------------------------------------");
        List<OrderVo> orderVos=orderService.queryAllUserOrder(orderParamVo);
        for (OrderVo orderVo:orderVos){
            System.out.println(orderVo.toString());
        }
        System.out.println("-------------------------------------");
    }

    /**
     * 通过用户名查询订单测试
     */
    @Test
    public void queryOrdersByUserTest(){
        OrderParamVo orderParamVo=new OrderParamVo();
        orderParamVo.setPageCurrent(1L);
        orderParamVo.setPageSize(2L);
        List<OrderVo> orderVos=orderService.queryOrdersByUser(orderParamVo,"user");
        System.out.println("-----------------------------");
        for (OrderVo orderVo:orderVos){
            System.out.println(orderVo.toString());
        }
        System.out.println("-------------------------------------");

    }




    /**
     * 用户状态查询
     */

    @Test
    public void queryOrderShipedTest(){
        OrderStatusParamVo orderStatusParamVo=new OrderStatusParamVo();
        orderStatusParamVo.setStartIndex(0L);
        orderStatusParamVo.setPageSize(5L);
        orderStatusParamVo.setUserId(1L);
        orderStatusParamVo.setSingleStauts(3);
        orderStatusParamVo.setCacneled(6);

        List<OrderVo> orderVos=orderService.queryOrderByStatus(orderStatusParamVo);

        System.out.println("-----------------------------");
        for (OrderVo orderVo:orderVos){
            System.out.println(orderVo.toString());
        }
        System.out.println("-------------------------------------");
    }

    /**
     * 客户删除订单
     */
    @Test
    public void deleteOrderTest(){
        OrderParamVo orderParamVo=new OrderParamVo();
        orderParamVo.setOrderNumber("201810121342494700e2");
        operatorOrderService.deleteOrder(orderParamVo);
    }
    /**
     * 测试新增订单并批量新增
     */
    @Test
    public void insert(){
        for (int i=0;i<5;i++) {
            Order order = new Order();
            order.setCustomerId(25L);
            order.setAddress("福建省厦门市思明区");
            order.setOrderRemark("新增测试数据"+i);
            order.setPrice(BigDecimal.valueOf(i));
            order.setReceiptName("收获人"+i);
            order.setReceiptPhone("1577331361"+i);
            order.setRemark("这是一个微信测试批量生成订单");

            //
            List<GoodsOrderParamVo> goodsOrderParamVos=new ArrayList<GoodsOrderParamVo>();
            GoodsOrderParamVo goodsOrderParamVo=new GoodsOrderParamVo();
            goodsOrderParamVo.setGoodsId(1L);
            goodsOrderParamVo.setQuantity(1);

            GoodsOrderParamVo goodsOrderParamVo2=new GoodsOrderParamVo();
            goodsOrderParamVo2.setGoodsId(2L);
            goodsOrderParamVo2.setQuantity(2);

            goodsOrderParamVos.add(goodsOrderParamVo);
            goodsOrderParamVos.add(goodsOrderParamVo2);
            int n = orderService.addOrder(order,goodsOrderParamVos);
            logger.info("n="+ n);
        }
    }

    /**
     * 用户的所有订单查询
     */
    @Test
    public void customerOrderTest(){
        OrderParamVo orderParamVo=new OrderParamVo();
        orderParamVo.setUserId(25L);
        List<OrderVo> orderVoList=orderService.customerOrder(orderParamVo);
        logger.info("=============================================================");
        for (OrderVo orderVo:orderVoList){
            logger.info("orderVo="+orderVo.toString());
        }
        logger.info("================================================================");
    }



    @Test
    public void selectByOrderNumberTest(){
        OrderParamVo orderParamVo=new OrderParamVo();
        orderParamVo.setOrderNumber("20181012134248860038");
        OrderVo orderVo=orderService.selectByOrderNumber(orderParamVo);
        System.out.println("-----------------------");
        System.out.println(orderVo.toString());
        System.out.println("--------------------------------");
    }

    /**
     * 通过订单号得到订单实体
     */
    @Test
    public void queryByOrderNumberTest2(){
        Order order=orderDao.queryByOrderNumber("20181012134248860038");
        System.out.println(order.toString());
    }

}