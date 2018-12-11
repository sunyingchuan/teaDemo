/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @program: tea-demo
 * @Author minmin.liu(minmin.liu @ ucarinc.com)
 * @Date 2018-10-24 10:02
 * @Version 1.0
 * @Description 订单状态的param
 */

package com.ucar.team.seven.tea.order.vo.params;

import com.ucar.team.seven.tea.common.web.Page;
import lombok.Data;

@Data
public class OrderStatusParamVo extends Page {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 0未支付 1待发货 2已发货 3完成订单 4申请取消
     */
    private Integer singleStauts;



    /**
     * 5 待退款
     */
    private Integer pendingRefund;
    /**
     * 6 已取消
     */
    private Integer cacneled;
}
