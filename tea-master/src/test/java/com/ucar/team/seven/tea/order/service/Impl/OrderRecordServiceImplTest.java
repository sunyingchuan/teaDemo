/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @program: tea-demo
 * @Author minmin.liu(minmin.liu @ ucarinc.com)
 * @Date 2018-10-24 16:56
 * @Version 1.0
 * @Description 订单记录测试
 */

package com.ucar.team.seven.tea.order.service.Impl;

import com.ucar.team.seven.tea.order.entity.OrderRecord;
import com.ucar.team.seven.tea.order.service.OrderRecordService;
import com.ucar.team.seven.tea.order.vo.OrderRecordVo;
import com.ucar.team.seven.tea.order.vo.params.OrderParamVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mybatis.xml",
        "classpath:spring/spring-mvc.xml"})
public class OrderRecordServiceImplTest {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderRecordService orderRecordService;

    @Test
    public void queryByOrderNumberTest(){
        OrderParamVo orderParamVo=new OrderParamVo();
        orderParamVo.setOrderNumber("20181012134248860038");
        List<OrderRecordVo> orderRecordVoList=orderRecordService.queryByOrderNumber(orderParamVo);
        System.out.println("========================");
        for (OrderRecordVo orderRecordVo:orderRecordVoList){
            System.out.println(orderRecordVo.toString());
        }
        System.out.println("==================");
    }

}
