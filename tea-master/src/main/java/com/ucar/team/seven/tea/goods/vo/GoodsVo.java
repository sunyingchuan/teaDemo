package com.ucar.team.seven.tea.goods.vo;

import com.ucar.team.seven.tea.common.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Kyrie(lingyi.huang @ ucarinc.com)
 * @Date 2018/10/8 8:52
 * @Version 1.0
 * @Description 商品查询返回结果vo
 */
@Data
public class GoodsVo extends BaseEntity {
    /**
     * 商品类别id
     */
    private Long goodsCategoryId;
    /**
     * 名称
     */
    private String goodsName;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 库存数量
     */
    private Integer stock;
    /**
     * 状态 0-无效(下架);1-有效(上架)
     */
    private Integer status;
    /**
     * 图片路径
     */
    private String picUrl;
    /**
     * 描述
     */
    private String description;
    /**
     * 备注
     */
    private String remark;
    /**
     * 商品类别名称
     */
    private String categoryName;

    /**
     * 创建人名称
     */
    String createEmpName;
    /**
     * 修改人名称
     */
    String modifyEmpName;

}