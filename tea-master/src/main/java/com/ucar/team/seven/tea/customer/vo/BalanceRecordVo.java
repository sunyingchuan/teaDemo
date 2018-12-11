package com.ucar.team.seven.tea.customer.vo;

import com.ucar.team.seven.tea.customer.entity.BalanceRecord;
import lombok.Data;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Description class description
 * @Author Grant(gang.zeng @ ucarinc.com)
 * @Version v1.0
 * @Date 2018/10/11 14:35
 */
@Data
public class BalanceRecordVo extends BalanceRecord {

    /**
     * 用户名
     */
    String username;
    /**
     * 创建者
     */
    String createEmpName;
    /**
     * 修改者
     */
    String modifyEmpName;
}
