package com.ucar.team.seven.tea.goods.vo.params;

import com.ucar.team.seven.tea.common.web.Page;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Kyrie(lingyi.huang @ ucarinc.com)
 * @Date 2018/10/8 17:26
 * @Version 1.0
 * @Description 商品查询参数vo
 */
@Data
public class GoodsParamVo extends Page {

    private String goodsName;

    private BigDecimal priceStart;

    private BigDecimal priceEnd;

    /**
     * 状态 0-无效(下架);1-有效(上架)
     */
    private Integer status;

    private Long id;

    private String code;


}
