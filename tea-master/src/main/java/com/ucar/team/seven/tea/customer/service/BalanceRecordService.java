package com.ucar.team.seven.tea.customer.service;

import com.ucar.team.seven.tea.common.service.IBaseService;
import com.ucar.team.seven.tea.common.web.Result;
import com.ucar.team.seven.tea.customer.entity.BalanceRecord;
import com.ucar.team.seven.tea.customer.vo.params.BalanceRecordParamVo;
import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Description BalanceRecord Service接口
 * @Author Grant(gang.zeng @ ucarinc.com)
 * @Version v1.0
 * @Date 2018/10/11 14:18
 */
public interface BalanceRecordService extends IBaseService<BalanceRecord> {

    /**
     * @description 获取账单记录列表
     *
     * @date  2018/10/17 14:46
     * @param balanceRecordParamVo
     * @return  java.util.List<com.ucar.team.seven.tea.customer.entity.BalanceRecord>
     */
    List<BalanceRecord> findPage(BalanceRecordParamVo balanceRecordParamVo);

    /**
     * @description 获取账单记录数目
     *
     * @date  2018/10/17 14:46
     * @param balanceRecordParamVo
     * @return  java.lang.Long
     */
    Long getCount(BalanceRecordParamVo balanceRecordParamVo);

    /**
     * @description 插入一条账单记录
     *
     * @date  2018/10/17 14:50
     * @param balanceRecord
     * @return  com.ucar.team.seven.tea.common.web.Result
     */
    Result insertBalanceRecord(BalanceRecord balanceRecord);
}
