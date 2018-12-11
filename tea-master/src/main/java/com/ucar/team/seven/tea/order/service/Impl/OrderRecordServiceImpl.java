/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @program: tea-demo
 * @Author minmin.liu(minmin.liu @ ucarinc.com)
 * @Date 2018-10-24 14:27
 * @Version 1.0
 * @Description 订单记录表的service实现类
 */

package com.ucar.team.seven.tea.order.service.Impl;

import com.ucar.team.seven.tea.common.dao.BaseDao;
import com.ucar.team.seven.tea.common.service.impl.BaseService;
import com.ucar.team.seven.tea.order.dao.OrderRecordDao;
import com.ucar.team.seven.tea.order.entity.OrderRecord;
import com.ucar.team.seven.tea.order.service.OrderRecordService;
import com.ucar.team.seven.tea.order.vo.OrderRecordVo;
import com.ucar.team.seven.tea.order.vo.OrderVo;
import com.ucar.team.seven.tea.order.vo.params.OrderParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderRecordServiceImpl extends BaseService<OrderRecord> implements OrderRecordService {
    @Autowired
    private OrderRecordDao orderRecordDao;

    @Override
    public List<OrderRecordVo> queryByOrderNumber(OrderParamVo orderParamVo) {
        Long count=orderRecordDao.orderRecordNum(orderParamVo.getOrderNumber());
        orderParamVo.setRowCount(count);
        orderParamVo.setStartIndex((orderParamVo.getPageCurrent()-1)*orderParamVo.getPageSize());
        return orderRecordDao.queryByOrderNumber(orderParamVo);
    }

    @Override
    public BaseDao<OrderRecord> getDao() {
        return orderRecordDao;
    }
}
