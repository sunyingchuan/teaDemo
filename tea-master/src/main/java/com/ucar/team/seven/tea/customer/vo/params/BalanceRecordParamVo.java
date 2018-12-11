package com.ucar.team.seven.tea.customer.vo.params;

import com.ucar.team.seven.tea.common.web.Page;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Description 终端客户查询参数VO
 * @Author Grant(gang.zeng @ ucarinc.com)
 * @Version v1.0
 * @Date 2018/10/8 17:41
 */
@Data
public class BalanceRecordParamVo extends Page {

    /**
     * 客户ID
     */
    private Long customerId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 记录类型
     */
    private Integer type;
    /**
     * 开始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    /**
     * 结束日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
}
