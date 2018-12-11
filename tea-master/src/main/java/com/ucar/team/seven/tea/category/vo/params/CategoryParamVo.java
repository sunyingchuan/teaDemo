package com.ucar.team.seven.tea.category.vo.params;

import com.ucar.team.seven.tea.common.web.Page;
import lombok.Data;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Kyrie(lingyi.huang @ ucarinc.com)
 * @Date 2018/10/16 11:11
 * @Version 1.0
 * @Description 分类param
 */
@Data
public class CategoryParamVo extends Page {
    /**
     * 商品类别父结点id
     */
    private Long pid;
    /**
     * 商品类别名称
     */
    private String categoryName;
    /**
     * 商品类别编码
     */
    private String code;
    /**
     * 商品类别排序优先级
     */
    private Integer sort;
}
