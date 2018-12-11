/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @program: tea-demo
 * @Author minmin.liu(minmin.liu @ ucarinc.com)
 * @Date 2018-10-10 11:00
 * @Version 1.0
 * @Description 订单操作controller
 */

package com.ucar.team.seven.tea.order.controller;

import com.ucar.team.seven.tea.common.controller.BaseController;
import com.ucar.team.seven.tea.common.exception.OrderException;
import com.ucar.team.seven.tea.common.util.StringUtil;
import com.ucar.team.seven.tea.common.web.Result;
import com.ucar.team.seven.tea.order.service.OperatorOrderService;
import com.ucar.team.seven.tea.order.vo.params.OrderParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("updateOrder")
public class OperatorOrderController extends BaseController {

    @Autowired
    OperatorOrderService operatorOrderService;
    /**
     * 后台订单发货操作
     * @param orderParamVo 订单号
     *
     * @return
     */
    @PostMapping("orderShip")
    @ResponseBody
    public Result orderShip(OrderParamVo orderParamVo){
        if (StringUtil.isEmpty(orderParamVo.getOrderNumber())){
            return BaseController.getErrorResult("订单号和修改者不能为空！");
        }
        try {
            int shipCount=operatorOrderService.shipOrder(orderParamVo);
            if (shipCount>0){
                return getResult();
            }
                return BaseController.getErrorResult("订单发货失败!");
        } catch (OrderException e) {
            return BaseController.getErrorResult(e.getMessage());
        }
    }

    /**
     * 订单支付操作
     * @param orderParamVo
     * @return
     */
   @PostMapping("/applyOrder")
    public Result applyOrder(OrderParamVo orderParamVo){
       if (StringUtil.isEmpty(orderParamVo.getOrderNumber())){
           return BaseController.getErrorResult("订单号不能为空！");
       }
       try {
           int applyCount=operatorOrderService.applyOrder(orderParamVo);
           if (applyCount>0){
               return getResult();
           }
               return BaseController.getErrorResult("订单支付失败");
       } catch (OrderException e) {
           return BaseController.getErrorResult(e.getMessage());
       }
   }

    /**
     * 订单确认收货
     * @param orderParamVo
     * @return
     */
   @PostMapping("/confirmReceipt")
    public Result confirmReceipt(OrderParamVo orderParamVo){
       if (StringUtil.isEmpty(orderParamVo.getOrderNumber())){
           return BaseController.getErrorResult("订单号不能为空！");
       }
       try {
           int confirmCount=operatorOrderService.confirmReceipt(orderParamVo);
           if (confirmCount>0){
               return getResult();
           }
               return BaseController.getErrorResult("订单确认收货失败！");
       } catch (OrderException e) {
           return BaseController.getErrorResult(e.getMessage());
       }
   }


    /**
     * 后台同意取消订单
     * @param orderParamVo
     * @return
     */
   @PostMapping("agreeOrderCancel")
   @ResponseBody
    public Result updateOrderStatus(OrderParamVo orderParamVo){
       if (StringUtil.isEmpty(orderParamVo.getOrderNumber())){
           return BaseController.getErrorResult("订单号不能为空！");
       }
       try {
           int updateCount=operatorOrderService.agreeOrderCancel(orderParamVo);
           if (updateCount>0){
               return getResult();
           }
               return BaseController.getErrorResult("同意取消订单申请操作失败！请重新操作！");

       } catch (OrderException e) {
           return BaseController.getErrorResult(e.getMessage());
       }
   }


    /**
     * 拒绝取消订单申请
     * @param orderParamVo
     * @return
     */
   @PostMapping("/refuseOrder")
    @ResponseBody
    public Result refuseOrder(OrderParamVo orderParamVo){
       if (StringUtil.isEmpty(orderParamVo.getOrderNumber())){
           return BaseController.getErrorResult("订单号不能为空！");
       }
       try {
           int updateCount=operatorOrderService.refuseOrder(orderParamVo);
           if (updateCount>0){
               return getResult();
           }
               return BaseController.getErrorResult("拒绝申请取消订单操作失败！请重新操作！");
       } catch (OrderException e) {
           return BaseController.getErrorResult(e.getMessage());
       }
   }

    /**
     * 修改订单总价
     * @param orderParamVo
     * @return
     */
   @PostMapping("updateOrderPirce")
   @ResponseBody
    public Result updateOrderPirce(OrderParamVo orderParamVo){
       if (StringUtil.isEmpty(orderParamVo.getOrderNumber())){
           return BaseController.getErrorResult("订单号不能为空！");
       }
       try {
           int updateCount=operatorOrderService.updateOrderPirce(orderParamVo);
           if (updateCount>0){
               return getResult();
           }
               return BaseController.getErrorResult("订单修改操作失败！请重新操作！");

       } catch (OrderException e) {
           return BaseController.getErrorResult(e.getMessage());
       }
   }

}
