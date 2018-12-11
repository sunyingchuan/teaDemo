package com.ucar.team.seven.tea.weixin.service.impl;

import com.ucar.team.seven.tea.customer.entity.Customer;
import com.ucar.team.seven.tea.goods.vo.GoodsVo;
import com.ucar.team.seven.tea.weixin.service.WXService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author 李清灿(qingcan.li @ ucarinc.com)
 * @Date 2018/10/11 14:04
 * @Version
 * @Description
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mybatis.xml",
        "classpath:spring/spring-mvc.xml"})
public class WXServiceImplTest {

    @Autowired
    private WXService wxService;

    @Test
    public void recharge() {
        Customer customer = wxService.recharge(26L,new BigDecimal(100));
        System.out.println("充值测试后的结果："+customer);
    }

    @Test
    public void listOrder() {
//        List<OrderVo> list = wxService.listOrder(25L,0,1L,3L);
//        for (OrderVo o: list){
//            System.out.println("============"+o);
//        }
//        list = wxService.listOrder(25L,0,2L,3L);
//        for (OrderVo o: list){
//            System.out.println("============"+o);
//        }
//        list = wxService.listOrder(25L,0,3L,3L);
//        for (OrderVo o: list){
//            System.out.println("============"+o);
//        }
    }

    @Test
    public void listGoods() {

        List<GoodsVo> glist = wxService.listGoods("01",1L,10L);
        for(GoodsVo g:glist){
            System.out.println("ggggggggggggggggggggggg"+g);
        }

    }
}