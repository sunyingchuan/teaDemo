package com.ucar.team.seven.tea.goods.service;

import com.ucar.team.seven.tea.common.service.IBaseService;
import com.ucar.team.seven.tea.goods.entity.Goods;
import com.ucar.team.seven.tea.goods.vo.GoodsVo;
import com.ucar.team.seven.tea.goods.vo.params.GoodsParamVo;

import java.util.List;
import java.util.Map;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Kyrie(lingyi.huang @ ucarinc.com)
 * @Date 2018/10/8 14:36
 * @Version 1.0
 * @Description 商品Service接口
 */
public interface GoodsService extends IBaseService<Goods> {
    /**
     * 分页查询
     *
     * @param goodsParamVo
     * @return
     */
    List<GoodsVo> findPage(GoodsParamVo goodsParamVo);
    /**
     * 获取数量
     *
     * @param goodsParamVo
     * @return
     */
    Long getCount(GoodsParamVo goodsParamVo);

    /**
     * 商品上下架
     *
     * @param id,status
     * @return
     */
    void updateStatus(Long id, Integer status);

    /**
     * 批量商品上下架
     *
     * @param map
     * @return
     */
    void batchStatus(Map map);

    /**
     * 查询一级分类下的所有商品
     *
     * @param goodsParamVo
     * @return
     */
    List<GoodsVo> getByCode(GoodsParamVo goodsParamVo);

    /**
     * 根据ID查找商品
     *
     * @param id
     * @return
     */
    GoodsVo getByGoodsId(Long id);
}
