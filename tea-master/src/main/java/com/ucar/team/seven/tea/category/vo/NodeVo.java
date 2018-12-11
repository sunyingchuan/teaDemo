package com.ucar.team.seven.tea.category.vo;

import com.ucar.team.seven.tea.category.entity.Node;
import com.ucar.team.seven.tea.common.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Kyrie(lingyi.huang @ ucarinc.com)
 * @Date 2018/10/8 8:52
 * @Version 1.0
 * @Description treeview返回json所需要的字段
 */
@Data
public class NodeVo extends BaseEntity{
    /**
     * tree的id
     */
    private Long id;
    /**
     * tree的父id
     */
    private Long pid;
    /**
     * tree的显示名称
     */
    private String text;
    /**
     * 分类下的结点集
     */
    private List<Node> nodes;


}