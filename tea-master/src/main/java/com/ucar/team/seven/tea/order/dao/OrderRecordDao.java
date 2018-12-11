package com.ucar.team.seven.tea.order.dao;

import com.ucar.team.seven.tea.common.dao.BaseDao;
import com.ucar.team.seven.tea.order.entity.OrderRecord;
import com.ucar.team.seven.tea.order.vo.OrderRecordVo;
import com.ucar.team.seven.tea.order.vo.params.OrderParamVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderRecordDao extends BaseDao<OrderRecord> {
    /**
     * 通过订单号查询订单记录
     * @param orderParamVo
     * @return
     */
    public List<OrderRecordVo> queryByOrderNumber(@Param("params")OrderParamVo orderParamVo);

    /**
     * 查询某条订单号总记录条数
     * @param orderNumber
     * @return
     */
    public Long orderRecordNum(@Param("orderNumber") String orderNumber);
}
