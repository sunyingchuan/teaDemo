package com.ucar.team.seven.tea.customer.dao;

import com.ucar.team.seven.tea.common.dao.BaseDao;
import com.ucar.team.seven.tea.customer.entity.BalanceRecord;
import com.ucar.team.seven.tea.customer.vo.params.BalanceRecordParamVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Description 账户余额记录Dao
 * @Author Grant(gang.zeng @ ucarinc.com)
 * @Version v1.0
 * @Date 2018/10/8 17:37
 */

public interface BalanceRecordDao extends BaseDao<BalanceRecord> {

    /**
     * @description 查询账单记录列表
     * @date  2018/10/9 17:14
     * @param balanceRecordParamVo
     * @return  java.util.List<com.ucar.team.seven.tea.customer.vo.CustomerVo>
     */
    List<BalanceRecord> findPage(@Param("params")BalanceRecordParamVo balanceRecordParamVo);

    /**
     * @description 获取查询数目
     * @date  2018/10/9 17:14
     * @param balanceRecordParamVo
     * @return  java.lang.Long
     */
    Long getCount(@Param("params") BalanceRecordParamVo balanceRecordParamVo);

}
