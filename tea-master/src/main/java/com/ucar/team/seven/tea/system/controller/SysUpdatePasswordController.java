package com.ucar.team.seven.tea.system.controller;

import com.ucar.team.seven.tea.common.controller.BaseController;
import com.ucar.team.seven.tea.common.web.Result;
import com.ucar.team.seven.tea.system.service.SysUserUpdatePasswordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * description: 密码修改controller
 * @author Henry（jian.zhang15 @ ucarinc.com）
 * @date 2018-10-09 10:34:45
 * @version 1.0
*/
@RequestMapping("user")
@Controller
public class SysUpdatePasswordController extends BaseController {
    @Resource
    private SysUserUpdatePasswordService sysUserUpdatePasswordService;

    /**
     * 修改用户密码
     *
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @return
     */
    @RequestMapping("/updateUserPwd.do")
    @ResponseBody
    public Result updateUserPwd(@RequestParam String oldPassword, @RequestParam String newPassword) {
        return sysUserUpdatePasswordService.updatePasswordCheck(oldPassword, newPassword);
    }
}
