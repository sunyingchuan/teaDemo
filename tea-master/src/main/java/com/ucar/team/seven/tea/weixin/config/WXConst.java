package com.ucar.team.seven.tea.weixin.config;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author 李清灿(qingcan.li @ ucarinc.com)
 * @Date 2018/10/10 14:47
 * @Version
 * @Description 微信小程序端需要使用的一些常量
 */
public interface WXConst {

    /**
     * 默认操作用户id，即系统自动创建的一些数据所使用的操作者的id
     */
    long DEFAULTEMPID = 0;

    /**
     * 订单状态代码：代付款
     */
    String DAIFUKUAN = "daifukuan";

    /**
     * 订单状态代码：待发货
     */
    String DAIFAHUO = "daifahuo";
    
    /**
     * 订单状态代码：待收货
     */
    String DAISHOUHUO = "daishouhuo";

    /**
     * 订单状态代码：处理中
     */
    String CHULIZHONG = "chulizhong";

    /**
     * 订单状态代码：已完成
     */
    String YIWANCHENG = "yiwancheng";
}
