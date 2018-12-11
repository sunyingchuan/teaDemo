package com.ucar.team.seven.tea.system.vo.params;

import com.ucar.team.seven.tea.common.web.Page;
import lombok.Data;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/29 17:26
 * @Version 1.0
 * @Description 用户查询参数vo
 */
@Data
public class UserParamVo extends Page {

    /**
     * 查询字符
     */
    private String query;

}
