package com.ucar.team.seven.tea.point.service;

import com.ucar.team.seven.tea.common.exception.ServiceException;
import com.ucar.team.seven.tea.common.service.IBaseService;
import com.ucar.team.seven.tea.order.entity.Order;
import com.ucar.team.seven.tea.point.entity.PointLog;
import com.ucar.team.seven.tea.point.vo.PointLogVo;
import com.ucar.team.seven.tea.point.vo.params.PointParamVo;

import java.util.List;


/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author 徐楷翔(kaixiang.xu@ucarinc.com)
 * @Date 2018/10/11 14:18
 * @Version 1.0
 * @Description 积分记录服务接口类
 */
public interface PointLogService extends IBaseService<PointLog> {

    /**
     * 获取列表
     *
     * @param pointParamVo
     * @return
     */
    List<PointLogVo> findPage(PointParamVo pointParamVo);

    /**
     * 获取数量
     *
     * @param pointParamVo
     * @return
     */
    Long getCount(PointParamVo pointParamVo);

    /**
     * 增加积分记录
     *
     * @param pointLog
     * @return
     */
    void addPointLog(PointLog pointLog) throws ServiceException;

    /**
     * 通过订单信息，增加积分记录
     *
     * @param order
     * @return
     */
    void addPointLogFromOrder(Order order);

    /**
     * 后台用户操作，批量增加积分记录
     *
     * @param pointLog
     * @return
     */
    void pointLogBatchInsert(Long[] customerIds, PointLog pointLog) throws ServiceException;
}
