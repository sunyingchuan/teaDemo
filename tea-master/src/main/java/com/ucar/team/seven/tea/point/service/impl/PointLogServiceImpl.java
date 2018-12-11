package com.ucar.team.seven.tea.point.service.impl;

import com.ucar.team.seven.tea.common.constant.CommonConstant;
import com.ucar.team.seven.tea.common.dao.BaseDao;
import com.ucar.team.seven.tea.common.exception.ServiceException;
import com.ucar.team.seven.tea.common.service.impl.BaseService;
import com.ucar.team.seven.tea.customer.service.CustomerService;
import com.ucar.team.seven.tea.order.entity.Order;
import com.ucar.team.seven.tea.point.dao.PointLogDao;
import com.ucar.team.seven.tea.point.entity.PointLog;
import com.ucar.team.seven.tea.point.service.PointLogService;
import com.ucar.team.seven.tea.point.vo.PointLogVo;
import com.ucar.team.seven.tea.point.vo.params.PointParamVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author 徐楷翔(kaixiang.xu @ ucarinc.com)
 * @Date 2018/10/11 14:18
 * @Version 1.0
 * @Description 积分记录服务具体实现
 */
@Service("pointLogService")
@Transactional(rollbackFor = Exception.class)
public class PointLogServiceImpl extends BaseService<PointLog> implements PointLogService {

    private static final int DESCRIPTION_MAX_LENGTH = 128;
    private static final int MAX_BATCH_INSERT_NUM = 1000;

    @Resource
    private PointLogDao pointLogDao;

    @Resource
    private CustomerService customerService;

    @Override
    public BaseDao<PointLog> getDao() {
        return pointLogDao;
    }

    @Override
    public List<PointLogVo> findPage(PointParamVo pointParamVo) {
        Long total = getCount(pointParamVo);
        pointParamVo.setRowCount(total);
        pointParamVo.setStartIndex((pointParamVo.getPageCurrent() - 1) * pointParamVo.getPageSize());

        return pointLogDao.findPage(pointParamVo);
    }

    @Override
    public Long getCount(PointParamVo pointParamVo) {
        return pointLogDao.getCount(pointParamVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addPointLog(PointLog pointLog) throws ServiceException {
        if (pointLog.getDescription().length() > DESCRIPTION_MAX_LENGTH) {
            throw new ServiceException("描述字符串长度不超过" + DESCRIPTION_MAX_LENGTH + "。");
        }

        Long[] pl = new Long[1];
        pl[0] = pointLog.getCustomerId();
        customerService.pointAdd(pl, pointLog.getPoint());
        pointLog.setCreateEmp(getEmp());
        pointLogDao.insert(pointLog);
    }

    @Override
    public void addPointLogFromOrder(Order order) {
        PointLog pointLog = new PointLog();
        pointLog.setCreateEmp(CommonConstant.AUTO_SYS_USER_ID);
        //默认为系统管理员创建订单积分记录
        pointLog.setCustomerId(order.getCustomerId());
        pointLog.setOrderId(order.getId());
        pointLog.setOrderNumber(order.getOrderNumber());
        pointLog.setMoney(order.getPrice());
        //积分计算暂时为订单金额向下取整
        pointLog.setPoint(pointLog.getMoney().setScale(0, BigDecimal.ROUND_DOWN).intValue());
        pointLogDao.insert(pointLog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pointLogBatchInsert(Long[] customerIds, PointLog pointLog) throws ServiceException {
        int n = 0;

        if (pointLog.getDescription().length() > DESCRIPTION_MAX_LENGTH) {
            throw new ServiceException("描述字符串长度不超过" + DESCRIPTION_MAX_LENGTH + "。");
        }

        customerService.pointAdd(customerIds, pointLog.getPoint());
        List<PointLog> pointLogList = new ArrayList<PointLog>(customerIds.length);
        for (Long i : customerIds) {
            PointLog p = new PointLog();
            p.setCustomerId(i);
            p.setCreateEmp(getEmp());
            p.setPoint(pointLog.getPoint());
            p.setDescription(pointLog.getDescription());
            pointLogList.add(p);

            //Mysql 对执行的SQL语句大小进行限制，默认允许最大SQL是 4M。可以通过 select @@max_allowed_packet; 查询
            //批量插入为防止语句大小超出限制，进行分段
            n++;
            if (n > MAX_BATCH_INSERT_NUM) {
                batchInsert(pointLogList);
                pointLogList.clear();
                n = 0;
            }
        }
        batchInsert(pointLogList);
    }
}
