package com.ucar.team.seven.tea.address.service.Impl;

import com.ucar.team.seven.tea.address.entity.Adress;
import com.ucar.team.seven.tea.address.service.AdressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.awt.*;

/**
 * @描述
 * @参数 $
 * @返回值 $
 * @创建人 yufeng.lin@ucarinc.com
 * @创建时间 $
 * @修改人和其它信息
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mybatis.xml",
        "classpath:spring/spring-mvc.xml"})
public class AdressServiceImplTest  {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdressServiceImplTest.class);

    @Autowired
    private AdressService adressService;

    @Test
    public void testSelect(){
        Adress adress = adressService.getById((long) 3);
        System.out.println(adress.toString());
    }

    @Test
    public void test_getAdressesByUserId(){
        System.out.println(adressService.getAdressesByUserId((long) 3));
    }

    @Test
    public void  test_insert(){
        Adress adress = new Adress();
        adress.setArea("99999");
        adress.setIsDef(0);
        adress.setArea("999");
        adress.setCity("9999");
        adress.setCustomerId(30L);
        adress.setDetailAddress("999999999");
        adress.setMobile("13023754315");
        adress.setName("9999");
        adress.setProvince("9999");
        adress.setSex(1);
        int c = adressService.insert(adress);
        LOGGER.info("------------------:" + c);
    }

    @Test
    public void test_getUserDefaultReceveAdrr(){
        System.out.println(adressService.getUserDefaultReceveAdrr((long) 3));
    }

    @Test
    public void test_update(){
        Adress adress = new Adress();
        adress.setId(1L);
        adress.setArea("99999");
        adress.setIsDef(1);
        adress.setArea("999");
        adress.setCity("9999");
        adress.setCustomerId(30L);
        adress.setDetailAddress("999999999");
        adress.setMobile("13023754315");
        adress.setName("9999");
        adress.setProvince("9999");
        adress.setSex(1);
        LOGGER.info("--------:" + adressService.update(adress) );

    }

    @Test
    public void test_deleteBatch(){
        System.out.println(adressService.deleteBatch("5,6,7"));
    }
    @Test
    public void test_setDefaultAddr(){
        System.out.println(adressService.setDefaultAddr(100L,25L));
    }


    @Test
    public void test_deleteAddrById(){
        System.out.println(adressService.deleteAddrById(11L));
    }


}































