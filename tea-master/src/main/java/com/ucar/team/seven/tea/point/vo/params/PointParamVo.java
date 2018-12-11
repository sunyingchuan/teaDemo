package com.ucar.team.seven.tea.point.vo.params;

import com.ucar.team.seven.tea.common.web.Page;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author 徐楷翔(kaixiang.xu@ucarinc.com)
 * @Date 2018/10/8 14:22
 * @Version 1.0
 * @Description 积分记录查询参数Vo类
 */
@Data
public class PointParamVo extends Page {

    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 积分
     */
    private Integer point;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
}
