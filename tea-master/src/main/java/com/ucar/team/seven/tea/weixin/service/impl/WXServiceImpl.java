package com.ucar.team.seven.tea.weixin.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ucar.team.seven.tea.address.entity.Adress;
import com.ucar.team.seven.tea.address.service.AdressService;
import com.ucar.team.seven.tea.category.service.CategoryService;
import com.ucar.team.seven.tea.category.vo.CategoryVo;
import com.ucar.team.seven.tea.common.util.Md5Util;
import com.ucar.team.seven.tea.common.web.Result;
import com.ucar.team.seven.tea.customer.entity.Customer;
import com.ucar.team.seven.tea.customer.service.CustomerService;
import com.ucar.team.seven.tea.goods.service.GoodsService;
import com.ucar.team.seven.tea.goods.vo.GoodsVo;
import com.ucar.team.seven.tea.goods.vo.params.GoodsParamVo;
import com.ucar.team.seven.tea.order.entity.Order;
import com.ucar.team.seven.tea.order.service.OperatorOrderService;
import com.ucar.team.seven.tea.order.service.OrderService;
import com.ucar.team.seven.tea.order.vo.OrderDetailVo;
import com.ucar.team.seven.tea.order.vo.OrderVo;
import com.ucar.team.seven.tea.order.vo.params.GoodsOrderParamVo;
import com.ucar.team.seven.tea.order.vo.params.OrderParamVo;
import com.ucar.team.seven.tea.order.vo.params.OrderStatusParamVo;
import com.ucar.team.seven.tea.weixin.config.WXConfig;
import com.ucar.team.seven.tea.weixin.config.WXConst;
import com.ucar.team.seven.tea.weixin.service.WXService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author 李清灿(qingcan.li @ ucarinc.com)
 * @Date 2018/10/10 13:41
 * @Version
 * @Description
 */
@Service
public class WXServiceImpl implements WXService {

    /**
     * 获取微信openid等信息的url
     */
    private static final String code2SessionUrl = "https://api.weixin.qq.com/sns/jscode2session?" +
            "appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    private static final Logger LOG = LoggerFactory.getLogger(WXService.class);

    @Autowired
    private WXConfig wxConfig;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private AdressService adressService;

    @Autowired
    private OperatorOrderService operatorOrderService;

    @Override
    public Customer login(String code, String nickName, Integer gender) {
        //获取openid
        String res = null;
        String url = String.format(code2SessionUrl, wxConfig.getAppID(), wxConfig.getAppSecret(), code);
        LOG.info("请求的url为 {}", url);
        res = HttpUtil.get(url);
        LOG.info("请求结果 {}", res);

        //判断请求成功或者失败
        JSONObject jres = JSONUtil.parseObj(res);
        String openid = jres.getStr("openid");
        if (openid == null || "".equals(openid)) {
            //获取不到openid
            return null;
        }

        //成功的请求
//        构造查询参数
        Customer query = new Customer();
        query.setWxID(openid);

        Customer customer = customerService.getByWxId(query);
        if (customer == null) {
//            用户未注册->进行注册->返回用户信息
            customer = new Customer();
            customer.setWxID(openid);
            customer.setSex(gender);
            //根据参数自动生成一个用户名
            customer.setUsername(nickName + Md5Util.getMD5(openid).substring(0, 5));
            customer.setCreateEmp(WXConst.DEFAULTEMPID);
            customer.setCreateTime(new Date());
            customer.setModifyEmp(customer.getCreateEmp());
            customer.setModifyTime(customer.getModifyTime());
            //插入到数据库
            customerService.insert(customer);
            LOG.info("用户未注册，注册新用户{}", customer);
            //重新从数据库中获取一次数据。避免一些数据库默认的数据没有
            customer = customerService.getByWxId(query);
        }
//        用户已经注册->登录成功->返回用户信息
        return customer;
    }

    @Override
    public Customer recharge(Long id, BigDecimal amount) {
        //参数校验
        if (id == null || id < 0) {
            return null;
        }
        if (amount == null || amount.compareTo(new BigDecimal(0)) < 0) {
            //充值金额要大于0
            return null;
        }
        //进行充值
        Result res = customerService.recharge(id, amount);
        if (res.getState() == Result.ERROR) {
            //充值失败
            return null;
        }
        //充值成功，获取最新的用户信息
        return customerService.getById(id);
    }

    @Override
    public Customer getUserInfo(Long id) {
        //参数校验
        if (id == null || id < 0) {
            return null;
        }
        return customerService.getById(id);
    }

    @Override
    public Customer updateUserPhone(Long id, String phone) {
        //参数校验
        if (id == null || id < 0) {
            return null;
        }
        if (phone == null || "".equals(phone) || phone.length() != 11) {
            return null;
        }
        //修改用户信息
        Customer customer = customerService.getById(id);
        if (customer != null) {
            customer.setMobile(phone);
            customer.setModifyTime(new Date());
            customer.setModifyEmp(WXConst.DEFAULTEMPID);
            customerService.update(customer);
        }
        return customer;
    }

    @Override
    public List<OrderVo> listOrder(Long uid, String status, Long pageNum, Long pageSize) {
        List<OrderVo> res = new ArrayList<OrderVo>();
        //参数校验
        if (uid == null || uid < 0 || status == null) {
            return res;
        }
        //进行查询
        OrderStatusParamVo query = new OrderStatusParamVo();
        query.setUserId(uid);
        query.setPageSize(pageSize);
        query.setPageCurrent(pageNum);
        switch (status) {
            case WXConst.DAIFUKUAN:
                query.setSingleStauts(0);
                break;
            case WXConst.DAIFAHUO:
                query.setSingleStauts(1);
                break;
            case WXConst.DAISHOUHUO:
                query.setSingleStauts(2);
                break;
            case WXConst.CHULIZHONG:
                query.setSingleStauts(4);
                query.setPendingRefund(5);
                break;
            case WXConst.YIWANCHENG:
                query.setSingleStauts(3);
                query.setCacneled(6);
                break;
            default:
                break;
        }
        return orderService.queryOrderByStatus(query);
    }

    @Override
    public OrderDetailVo getOrder(String orderNumber) {
        OrderParamVo query = new OrderParamVo();
        query.setOrderNumber(orderNumber);
        return orderService.orderDetail(query);
    }

    @Override
    public List<CategoryVo> getTopCategory() {
        return categoryService.findTopCategory();
    }

    @Override
    public List<GoodsVo> listGoods(String categoryCode, Long pageNum, Long pageSize) {
        GoodsParamVo query = new GoodsParamVo();
        query.setPageCurrent(pageNum);
        query.setPageSize(pageSize);
        query.setCode(categoryCode);
        return goodsService.getByCode(query);
    }

    @Override
    public GoodsVo getGoods(Long gid) {
        return goodsService.getByGoodsId(gid);
    }

    @Override
    public List<Adress> listAddress(Long uid) {
        return adressService.getAdressesByUserId(uid);
    }

    @Override
    public Integer insertAddress(Adress entity) {
        entity.setCreateEmp(WXConst.DEFAULTEMPID);
        entity.setCreateTime(new Date());
        entity.setModifyEmp(entity.getCreateEmp());
        entity.setModifyTime(entity.getCreateTime());
        return adressService.insert(entity);
    }

    @Override
    public Integer insertOrder(Order order, List<GoodsOrderParamVo> goodsOrderParamVos) {
        return orderService.addOrder(order, goodsOrderParamVos);
    }

    @Override
    public Adress getDefaultAddress(Long uid) {
        List<Adress> addressList = listAddress(uid);
        if (addressList != null && !addressList.isEmpty()) {
            //有地址
            if (addressList.size() > 1) {
                //有多条地址，需要遍历获取默认地址
                for (Adress address : addressList) {
                    if (address.getIsDef() == 1) {
                        return address;
                    }
                }
            }
            //没有找到标记为默认地址的地址或者只有一条地址，返回第一条地址作为默认地址
            return addressList.get(0);
        } else {
            //没有地址
            return null;
        }
    }

    @Override
    public boolean setDefaultAddress(Long uid, Long aid) {
        return adressService.setDefaultAddr(aid, uid) == 0;
    }

    @Override
    public boolean delAddress(Long aid) {
        return adressService.deleteAddrById(aid) == 0;
    }

    @Override
    public boolean payOrder(Long uid,String orderNumber) {
        OrderParamVo query = new OrderParamVo();
        query.setOrderNumber(orderNumber);
        query.setUserId(uid);
        return operatorOrderService.applyOrder(query) > 0;
    }

    @Override
    public boolean cancleOrder(Long uid,String orderNumber) {
        OrderParamVo query = new OrderParamVo();
        query.setOrderNumber(orderNumber);
        query.setUserId(uid);
        return operatorOrderService.applyCancelOrder(query) > 0;
    }

    @Override
    public boolean delOrder(Long uid,String orderNumber) {
        OrderParamVo query = new OrderParamVo();
        query.setOrderNumber(orderNumber);
        query.setUserId(uid);
        return operatorOrderService.deleteOrder(query) > 0;
    }

    @Override
    public boolean confirmReceipt(Long uid, String orderNumber) {
        OrderParamVo query = new OrderParamVo();
        query.setOrderNumber(orderNumber);
        query.setUserId(uid);
        return operatorOrderService.confirmReceipt(query) > 0;
    }
}
