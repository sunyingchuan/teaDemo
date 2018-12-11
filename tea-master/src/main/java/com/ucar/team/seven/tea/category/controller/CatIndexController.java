package com.ucar.team.seven.tea.category.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Kyrie(lingyi.huang @ ucarinc.com)
 * @Date 2018/10/12 14:20
 * @Version 1.0
 * @Description 商品分类主页
 */
@Controller
@RequestMapping("/category")
public class CatIndexController {

    @RequestMapping("/index.do")
    public String indexUI(){

        return "category/category_list";
    }
}
