package com.ucar.team.seven.tea.system.controller;

import com.ucar.team.seven.tea.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/26 8:52
 * @Version 1.0
 * @Description 主页controller
 */
@Controller
public class IndexController {

    @RequestMapping("/index.do")
    public String indexUI() {
        System.out.println("indexUI");
        //返回的字符串会对应一个/WEB-INF/pages/index.jsp页面
        return "index";
    }
}
