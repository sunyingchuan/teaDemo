package com.ucar.team.seven.tea.customer.vo;

import com.ucar.team.seven.tea.customer.entity.Customer;
import lombok.Data;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Description 终端客户VO
 * @Author Grant(gang.zeng @ ucarinc.com)
 * @Version v1.0
 * @Date 2018/10/8 17:40
 */
@Data
public class CustomerVo extends Customer {

    /**
     * 创建者
     */
    String createEmpName;

    /**
     * 修改者
     */
    String modifyEmpName;
}
