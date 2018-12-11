package com.ucar.team.seven.tea.order.service;

import com.ucar.team.seven.tea.order.vo.OrderRecordVo;
import com.ucar.team.seven.tea.order.vo.params.OrderParamVo;

import java.util.List;

public interface OrderRecordService {
    public List<OrderRecordVo> queryByOrderNumber(OrderParamVo orderParamVo);
}
