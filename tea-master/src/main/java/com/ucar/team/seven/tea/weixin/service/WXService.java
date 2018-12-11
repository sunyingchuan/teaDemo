package com.ucar.team.seven.tea.weixin.service;

import com.ucar.team.seven.tea.address.entity.Adress;
import com.ucar.team.seven.tea.category.vo.CategoryVo;
import com.ucar.team.seven.tea.customer.entity.Customer;
import com.ucar.team.seven.tea.goods.vo.GoodsVo;
import com.ucar.team.seven.tea.order.entity.Order;
import com.ucar.team.seven.tea.order.vo.OrderDetailVo;
import com.ucar.team.seven.tea.order.vo.OrderVo;
import com.ucar.team.seven.tea.order.vo.params.GoodsOrderParamVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author 李清灿(qingcan.li @ ucarinc.com)
 * @Date 2018/10/9 11:24
 * @Version
 * @Description 微信小程序服务类
 */
public interface WXService {

    /**
     * 进行微信授权登录操作
     * 该方法会先通过code与微信服务器通讯获取openid，之后通过openid获取用户信息（未注册则进行注册操作）
     *
     * @param code     微信小程序端传过来的一个临时授权代码，用于获取openid
     * @param nickName 微信的昵称
     * @param gender   微信的性别
     * @return
     */
    Customer login(String code, String nickName, Integer gender);

    /**
     * 余额充值服务
     * 通过id和amount进行充值，充值后返回最新的客户信息
     *
     * @param id
     * @param amount
     * @return
     */
    Customer recharge(Long id, BigDecimal amount);

    /**
     * 获取用户信息服务
     *
     * @param id
     * @return
     */
    Customer getUserInfo(Long id);

    /**
     * 修改用户手机号信息
     *
     * @param id
     * @param phone
     * @return
     */
    Customer updateUserPhone(Long id, String phone);

    /**
     * 通过uid和状态查询订单列表
     *
     * @param uid
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<OrderVo> listOrder(Long uid, String status, Long pageNum, Long pageSize);

    /**
     * 通过订单id查询订单详情
     *
     * @param orderNumber
     * @return
     */
    OrderDetailVo getOrder(String orderNumber);

    /**
     * 获取一级分类
     *
     * @return
     */
    List<CategoryVo> getTopCategory();


    /**
     * 通过分类id分页查询商品
     *
     * @param categoryCode
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<GoodsVo> listGoods(String categoryCode, Long pageNum, Long pageSize);

    /**
     * 通过商品id获取商品详细信息
     *
     * @param gid
     * @return
     */
    GoodsVo getGoods(Long gid);

    /**
     * 通过uid获取收货地址
     *
     * @param uid
     * @return
     */
    List<Adress> listAddress(Long uid);

    /**
     * 添加一个收货地址
     *
     * @param entity
     * @return
     */
    Integer insertAddress(Adress entity);

    /**
     * 增加一个新订单。即下单
     *
     * @param order
     * @param goodsOrderParamVos
     * @return
     */
    Integer insertOrder(Order order, List<GoodsOrderParamVo> goodsOrderParamVos);

    /**
     * 通过uid获取用户的默认地址
     *
     * @param uid
     * @return
     */
    Adress getDefaultAddress(Long uid);

    /**
     * 设置用户的默认地址
     *
     * @param uid
     * @param aid
     * @return
     */
    boolean setDefaultAddress(Long uid, Long aid);

    /**
     * 删除地址
     *
     * @param aid
     * @return
     */
    boolean delAddress(Long aid);

    /**
     * 支付一个订单
     *
     * @param uid
     * @param orderNumber
     * @return
     */
    boolean payOrder(Long uid, String orderNumber);

    /**
     * 取消一个订单
     *
     * @param uid
     * @param orderNumber
     * @return
     */
    boolean cancleOrder(Long uid, String orderNumber);

    /**
     * 删除一个订单
     *
     * @param uid
     * @param orderNumber
     * @return
     */
    boolean delOrder(Long uid, String orderNumber);

    /**
     * 确认收货
     * @param uid
     * @param orderNumber
     * @return
     */
    boolean confirmReceipt(Long uid, String orderNumber);
}
