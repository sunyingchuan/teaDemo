package com.ucar.team.seven.tea.customer.service.impl;

import com.ucar.team.seven.tea.common.constant.CommonConstant;
import com.ucar.team.seven.tea.common.dao.BaseDao;
import com.ucar.team.seven.tea.common.service.impl.BaseService;
import com.ucar.team.seven.tea.common.web.Result;
import com.ucar.team.seven.tea.customer.dao.BalanceRecordDao;
import com.ucar.team.seven.tea.customer.entity.BalanceRecord;
import com.ucar.team.seven.tea.customer.service.BalanceRecordService;
import com.ucar.team.seven.tea.customer.vo.params.BalanceRecordParamVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Description class description
 * @Author Grant(gang.zeng @ ucarinc.com)
 * @Version v1.0
 * @Date 2018/10/11 14:21
 */
@Service("balanceRecordService")
public class BalanceRecordServiceImpl extends BaseService<BalanceRecord> implements BalanceRecordService {

    @Resource
    BalanceRecordDao balanceRecordDao;

    @Override
    public BaseDao<BalanceRecord> getDao() {
        return balanceRecordDao;
    }

    @Override
    public List<BalanceRecord> findPage(BalanceRecordParamVo balanceRecordParamVo) {
        Long total = getCount(balanceRecordParamVo);
        balanceRecordParamVo.setRowCount(total);
        balanceRecordParamVo.setStartIndex((balanceRecordParamVo.getPageCurrent()-1) * balanceRecordParamVo.getPageSize());
        return balanceRecordDao.findPage(balanceRecordParamVo);
    }

    @Override
    public Long getCount(BalanceRecordParamVo balanceRecordParamVo){
        return  balanceRecordDao.getCount(balanceRecordParamVo);
    }

    @Override
    public Result insertBalanceRecord(BalanceRecord balanceRecord) {
        balanceRecord.setCreateEmp(CommonConstant.AUTO_SYS_USER_ID);
        balanceRecord.setModifyEmp(CommonConstant.AUTO_SYS_USER_ID);
        balanceRecordDao.insert(balanceRecord);
        return Result.successResult();
    }
}
