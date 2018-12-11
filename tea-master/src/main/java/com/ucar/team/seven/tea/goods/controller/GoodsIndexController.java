package com.ucar.team.seven.tea.goods.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Kyrie(lingyi.huang @ ucarinc.com)
 * @Date 2018/10/12 14:20
 * @Version 1.0
 * @Description 商品治展示主页
 */
@Controller
@RequestMapping("/goods")
public class GoodsIndexController {

    @RequestMapping("/index.do")
    public String indexUI(){

        return "goods/goods_list";
    }
}
