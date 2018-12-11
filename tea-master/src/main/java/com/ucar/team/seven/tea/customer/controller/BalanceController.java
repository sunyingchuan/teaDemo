package com.ucar.team.seven.tea.customer.controller;

import com.ucar.team.seven.tea.common.controller.BaseController;
import com.ucar.team.seven.tea.common.web.PageResult;
import com.ucar.team.seven.tea.customer.entity.BalanceRecord;
import com.ucar.team.seven.tea.customer.service.BalanceRecordService;
import com.ucar.team.seven.tea.customer.vo.params.BalanceRecordParamVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Description 客户账户Controller
 * @Author Grant(gang.zeng @ ucarinc.com)
 * @Version v1.0
 * @Date 2018/10/10 17:32
 */
@Controller
@RequestMapping("balance")
public class BalanceController extends BaseController {

    @Resource
    BalanceRecordService balanceRecordService;

    /**
     * @description 账单明细列表页面
     * @date  2018/10/8 18:03
     * @return  java.lang.String
     */
    @RequestMapping("list.do")
    public String balanceListUI(){
        return "customer/balance_list";
    }


    /**
     * @description 获取账单明细列表
     * @date  2018/10/17 14:43
     * @param balanceRecordParamVo
     * @return  com.ucar.team.seven.tea.common.web.PageResult
     */
    @RequestMapping(value = "getBalanceRecordList.do", method = RequestMethod.POST)
    @ResponseBody
    public PageResult getBalanceRecordList(BalanceRecordParamVo balanceRecordParamVo){
        List<BalanceRecord> list = balanceRecordService.findPage(balanceRecordParamVo);
        return getPageResult(list, balanceRecordParamVo);
    }

}
