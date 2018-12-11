/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @program: tea-demo
 * @Author minmin.liu(minmin.liu @ ucarinc.com)
 * @Date 2018-10-24 14:30
 * @Version 1.0
 * @Description 订单记录表的controller
 */

package com.ucar.team.seven.tea.order.controller;

import com.ucar.team.seven.tea.common.controller.BaseController;
import com.ucar.team.seven.tea.common.web.Result;
import com.ucar.team.seven.tea.order.entity.OrderRecord;
import com.ucar.team.seven.tea.order.service.OrderRecordService;
import com.ucar.team.seven.tea.order.vo.OrderRecordVo;
import com.ucar.team.seven.tea.order.vo.params.OrderParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("orderRecord")
@Controller
public class OrderRecordController extends BaseController {
    @Autowired
    private OrderRecordService orderRecordService;

    @PostMapping("queryByOrderNumber")
    @ResponseBody
    public Result queryByOrderNumber(OrderParamVo orderParamVo){
        orderParamVo.setPageSize(20L);
        List<OrderRecordVo> orderRecordList = orderRecordService.queryByOrderNumber(orderParamVo);
        return getPageResult(orderRecordList,orderParamVo);
    }
}
