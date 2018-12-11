package com.ucar.team.seven.tea.system.controller;

import com.ucar.team.seven.tea.common.controller.BaseController;
import com.ucar.team.seven.tea.common.service.SysShiroService;
import com.ucar.team.seven.tea.common.web.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/26 8:52
 * @Version 1.0
 * @Description 登录Controller
 */
@Controller
public class SysLoginController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysLoginController.class);

    @Autowired
    private SysShiroService loginService;

    @RequestMapping("/loginUI")
    public String login() {
        return "login";
    }

    /**
     * 登录操作
     */
    @RequestMapping("/login")
    @ResponseBody
    public Result login(String username, String password) {
        LOGGER.info(username + "/" + password);
        loginService.login(username, password);
        return getResult();
    }
}
