package com.ucar.team.seven.tea.goods.service.impl;


import com.ucar.team.seven.tea.common.dao.BaseDao;
import com.ucar.team.seven.tea.common.service.impl.BaseService;
import com.ucar.team.seven.tea.goods.dao.GoodsDao;
import com.ucar.team.seven.tea.goods.entity.Goods;
import com.ucar.team.seven.tea.goods.service.GoodsService;
import com.ucar.team.seven.tea.goods.vo.GoodsVo;
import com.ucar.team.seven.tea.goods.vo.params.GoodsParamVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Kyrie(lingyi.huang @ ucarinc.com)
 * @Date 2018/10/8 14:39
 * @Version 1.0
 * @Description 商品Service接口实现
 */
@Service("goodsService")
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceImpl extends BaseService<Goods> implements GoodsService {
    @Resource
    private GoodsDao goodsDao;

    @Override
    public BaseDao<Goods> getDao() {
        return goodsDao;
    }
    @Override
    public List<GoodsVo> findPage(GoodsParamVo goodsParamVo) {

        Long total = getCount(goodsParamVo);
        goodsParamVo.setRowCount(total);
        goodsParamVo.setStartIndex((goodsParamVo.getPageCurrent() - 1) * goodsParamVo.getPageSize());

        return goodsDao.findPage(goodsParamVo);
    }
    @Override
    public Long getCount(GoodsParamVo goodsParamVo) {
        return goodsDao.getCount(goodsParamVo);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
         goodsDao.updateStatus(id,status);
    }

    @Override
    public void batchStatus(Map map) {
        goodsDao.batchStatus(map);
    }

    @Override
    public List<GoodsVo>  getByCode(GoodsParamVo goodsParamVo) {
        return goodsDao.getByCode(goodsParamVo);
    }

    @Override
    public GoodsVo getByGoodsId(Long id) {
        return goodsDao.getByGoodsId(id);
    }

}
