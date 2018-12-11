package com.ucar.team.seven.tea.weixin.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ucar.team.seven.tea.address.entity.Adress;
import com.ucar.team.seven.tea.category.vo.CategoryVo;
import com.ucar.team.seven.tea.common.web.Result;
import com.ucar.team.seven.tea.customer.entity.Customer;
import com.ucar.team.seven.tea.goods.vo.GoodsVo;
import com.ucar.team.seven.tea.order.entity.Order;
import com.ucar.team.seven.tea.order.vo.OrderDetailVo;
import com.ucar.team.seven.tea.order.vo.OrderVo;
import com.ucar.team.seven.tea.order.vo.params.GoodsOrderParamVo;
import com.ucar.team.seven.tea.weixin.service.WXService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author 李清灿(qingcan.li @ ucarinc.com)
 * @Date 22018/10/9 10:28
 * @Version
 * @Description 用于提供微信交互的控制器
 */
@RequestMapping("/wxapi")
@RestController
public class WXController {

    private static final Logger LOG = LoggerFactory.getLogger(WXController.class);

    @Autowired
    private WXService wxService;

    /**
     * 支付订单
     *
     * @param uid
     * @param orderNumber
     * @return
     */
    @PostMapping("payOrder")
    public Result payOrder(Long uid, String orderNumber) {
        if (wxService.payOrder(uid, orderNumber)) {
            return Result.suc(null);
        } else {
            return Result.err("支付发生错误");
        }
    }

    /**
     * 取消订单
     *
     * @param uid
     * @param orderNumber
     * @return
     */
    @PostMapping("cancleOrder")
    public Result cancleOrder(Long uid, String orderNumber) {
        if (wxService.cancleOrder(uid, orderNumber)) {
            return Result.suc(null);
        } else {
            return Result.err("支付发生错误");
        }
    }

    /**
     * 确认收货
     *
     * @param uid
     * @param orderNumber
     * @return
     */
    @PostMapping("confirmReceipt")
    public Result confirmReceipt(Long uid, String orderNumber) {
        if (wxService.confirmReceipt(uid, orderNumber)) {
            return Result.suc(null);
        } else {
            return Result.err("确认收货发生错误");
        }
    }

    /**
     * 删除订单
     *
     * @param uid
     * @param orderNumber
     * @return
     */
    @PostMapping("delOrder")
    public Result delOrder(Long uid, String orderNumber) {
        if (wxService.delOrder(uid, orderNumber)) {
            return Result.suc(null);
        } else {
            return Result.err("支付发生错误");
        }
    }

    /**
     * 增加一个新订单 即下单
     * json请求格式样例：{"goodsList":[{"quantity":100,"goodsId":1},{"quantity":50,"goodsId":2}],"order":{"receiptName":"李云龙","address":"平安县城","customerId":25,"receiptPhone":"13123185442","orderRemark":"这是我的意大利炮！"}}
     * 请求头中的Content-Type需要为application/json
     *
     * @return
     */
    @PostMapping("newOrder")
    public Result<Order> newOrder(@RequestBody String str) {
        JSONObject paras = JSONUtil.parseObj(str);
        //获取订单信息
        Order order = paras.getBean("order", Order.class);
        //获取订单中商品信息
        List<GoodsOrderParamVo> goodsList = new ArrayList<GoodsOrderParamVo>();
        JSONArray jgoodsList = paras.getJSONArray("goodsList");
        for (int i = 0; i < jgoodsList.size(); i++) {
            GoodsOrderParamVo goods = jgoodsList.getBean(i, GoodsOrderParamVo.class);
            goodsList.add(goods);
        }
        //参数获取完成
        if (order != null && !goodsList.isEmpty()) {
            int temp = wxService.insertOrder(order, goodsList);
            if (temp == 1) {
                return Result.suc(order);
            }
        }
        return Result.err("下单失败");
    }

    /**
     * 设置uid用户的默认地址为aid这个地址
     *
     * @param uid
     * @param aid
     * @return
     */
    @PostMapping("setDefaultAddress")
    public Result setDefaultAddress(Long uid, Long aid) {
        boolean b = wxService.setDefaultAddress(uid, aid);
        return b ? Result.suc(null) : Result.err("设置默认地址失败");
    }

    /**
     * 删除一个地址
     *
     * @param aid
     * @return
     */
    @PostMapping("delAddress")
    public Result delAddress(Long aid) {
        boolean b = wxService.delAddress(aid);
        return b ? Result.suc(null) : Result.err("删除地址失败");
    }

    /**
     * 新增收货地址
     *
     * @param adress
     * @return
     */
    @PostMapping("newAddress")
    public Result<Adress> newAddress(Adress adress) {
        LOG.info("微信增加收货地址Address={}", adress);
        Integer i = wxService.insertAddress(adress);
        if (i == 1) {
            return Result.suc(adress);
        } else {
            return Result.err("增加收货地址失败");
        }
    }

    /**
     * 通过uid获取收货地址列表
     *
     * @param uid
     * @return
     */
    @PostMapping("listAddress")
    public Result<List<Adress>> listAddress(Long uid) {
        List<Adress> adressList = wxService.listAddress(uid);
        return Result.suc(adressList);
    }

    /**
     * 获取用户的默认收货地址
     *
     * @param uid
     * @return
     */
    @PostMapping("getDefaultAddress")
    public Result<Adress> getDefaultAddress(Long uid) {
        Adress address = wxService.getDefaultAddress(uid);
        if (address != null) {
            return Result.suc(address);
        } else {
            return Result.err("该用户找不到收货地址");
        }
    }

    /**
     * 获取一级商品分类
     *
     * @return
     */
    @PostMapping("getTopCategory")
    public Result<List<CategoryVo>> getTopCategory() {
        List<CategoryVo> categoryVoList = wxService.getTopCategory();
        return Result.suc(categoryVoList);
    }

    /**
     * 通过分类分页获取商品
     *
     * @param categoryCode
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("listGoods")
    public Result<List<GoodsVo>> listGoods(String categoryCode, Long pageNum, Long pageSize) {
        LOG.info("微信获取用户订单列表categoryCode={},pageNum={},pageSize={}", categoryCode, pageNum, pageSize);
        List<GoodsVo> goodsVoList = wxService.listGoods(categoryCode, pageNum, pageSize);
        if (goodsVoList != null && !goodsVoList.isEmpty()) {
            return Result.suc(goodsVoList);
        } else {
            return Result.err("查询不到商品");
        }
    }

    /**
     * 通过gid获取商品详细信息
     *
     * @param gid
     * @return
     */
    @PostMapping("getGoods")
    public Result<GoodsVo> getGoods(Long gid) {
        LOG.info("获取商品详细信息gid={}", gid);
        GoodsVo goods = wxService.getGoods(gid);
        if (goods != null) {
            return Result.suc(goods);
        } else {
            return Result.err("获取商品信息出错");
        }
    }

    /**
     * 通过uid和状态获取uid用户的订单列表
     *
     * @param uid
     * @param status
     * @return
     */
    @PostMapping("listOrder")
    public Result<List<OrderVo>> listOrder(Long uid, String status, Long pageNum, Long pageSize) {
        LOG.info("微信获取用户订单列表uid={}，status={},pageNum={},pageSize={}", uid, status, pageNum, pageSize);
        List<OrderVo> orderVoList = wxService.listOrder(uid, status, pageNum, pageSize);
        if (orderVoList != null && !orderVoList.isEmpty()) {
            return Result.suc(orderVoList);
        } else {
            return Result.err("没有查询到数据");
        }
    }

    /**
     * 获取订单详细信息
     *
     * @param orderNumber
     * @return
     */
    @PostMapping("getOrder")
    public Result<OrderDetailVo> getOrder(String orderNumber) {
        LOG.info("获取订单详细信息orderNumber={}", orderNumber);
        OrderDetailVo orderDetailVo = wxService.getOrder(orderNumber);
        if (orderDetailVo != null) {
            return Result.suc(orderDetailVo);
        } else {
            return Result.err("获取订单信息出错");
        }
    }

    /**
     * 根据uid更新用户手机号信息
     *
     * @param uid
     * @param phone
     * @return
     */
    @PostMapping("/updateUserPhone.do")
    public Result<Customer> updateUserPhone(Long uid, String phone) {
        LOG.info("微信获取用户信息uid={}，phone={}", uid, phone);
        Customer customer = wxService.updateUserPhone(uid, phone);
        if (customer != null) {
            return Result.suc(customer);
        } else {
            return Result.err("更新手机号失败");
        }
    }

    /**
     * 根据uid获取用户信息
     *
     * @param uid
     * @return
     */
    @PostMapping("/getUserInfo")
    public Result<Customer> getUserInfo(Long uid) {
        LOG.info("微信获取用户信息uid={}", uid);
        Customer customer = wxService.getUserInfo(uid);
        if (customer != null) {
            return Result.suc(customer);
        } else {
            return Result.err("获取用户信息失败");
        }
    }


    /**
     * 余额充值接口
     *
     * @param uid    用户id
     * @param amount 充值金
     * @return
     */
    @PostMapping("/recharge")
    public Result<Customer> recharge(Long uid, BigDecimal amount) {
        LOG.info("微信进行充值uid={},amount={}", uid, amount);
        Customer customer = wxService.recharge(uid, amount);
        if (customer != null) {
            return Result.suc(customer);
        } else {
            return Result.err("充值失败");
        }
    }

    /**
     * 小程序登录接口
     *
     * @param code
     * @return
     */
    @PostMapping("/login")
    public Result<Customer> login(String code, String nickName, Integer gender) {
        LOG.info("微信进行登录code={},nickName={},gender={}", code, nickName, gender);
        Customer customer = wxService.login(code, nickName, gender);
        if (customer == null) {
            //请求失败
            return Result.err("请求失败请重试");
        } else {
            //请求成功
            return Result.suc(customer);
        }
    }

    /**
     * 测试访问
     *
     * @return
     */
    @GetMapping("/test")
    public Result<String> test() {
        return new Result<>("微信api测试");
    }

}
