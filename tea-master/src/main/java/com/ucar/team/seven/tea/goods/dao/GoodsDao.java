package com.ucar.team.seven.tea.goods.dao;

import com.ucar.team.seven.tea.common.dao.BaseDao;
import com.ucar.team.seven.tea.goods.entity.Goods;
import com.ucar.team.seven.tea.goods.vo.GoodsVo;
import com.ucar.team.seven.tea.goods.vo.params.GoodsParamVo;
import com.ucar.team.seven.tea.order.vo.GoodsOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Kyrie(lingyi.huang @ ucarinc.com)
 * @Date 2018/10/8 11:21
 * @Version 1.0
 * @Description 商品Dao
 */
public interface GoodsDao extends BaseDao<Goods> {
    /**
     * 获取数量
     *
     * @param goodsParamVo
     * @return
     */
    Long getCount(@Param("params") GoodsParamVo goodsParamVo);
    /**
     * 分页查询
     *
     * @param goodsParamVo
     * @return
     */
    List<GoodsVo> findPage(@Param("params") GoodsParamVo goodsParamVo);


    /**
     * 商品订单多对多查询
     * @param orderId
     * @return
     */
    List<GoodsOrderVo> selectGoodsOrderByOrderId(@Param("orderId") int orderId);
    /**
     * @description 更新状态status
     * @param id
     * @param status
     * @return  void
     */
    void updateStatus(@Param("id")Long id,@Param("status") Integer status);

    /**
     * @description 批量更新状态status
     * @param map
     * @param
     * @return  void
     */
    void batchStatus(Map map);
    /**
     * @description 查询一级分类下的所有商品
     * @param goodsParamVo
     * @param
     * @return  GoodsVo
     */
    List<GoodsVo> getByCode(@Param("params") GoodsParamVo goodsParamVo);

    /**
     * @description 根据商品id查询商品
     * @param id
     * @param
     * @return  GoodsVo
     */
    GoodsVo getByGoodsId(@Param("id")Long id);

}
