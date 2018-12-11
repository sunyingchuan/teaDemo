package com.ucar.team.seven.tea.customer.entity;

import com.ucar.team.seven.tea.common.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Description 终端客户实体类
 * @Author Grant(gang.zeng @ ucarinc.com)
 * @Version v1.0
 * @Date 2018/10/8 17:13
 */

@Data
public class Customer extends BaseEntity {

    /**
     * 用户名
     */
    String username;
    /**
     * 密码
     */
    String password;
    /**
     * 邮箱
     */
    String email;
    /**
     * 手机号
     */
    String mobile;
    /**
     * 微信账号
     */
    String wxID;
    /**
     * 性别:  2-未选择; 1-男; 0-女
     */
    Integer sex;
    /**
     * 会员积分
     */
    Integer point;
    /**
     * 账户余额
     */
    BigDecimal balance;
    /**
     * 状态: 0-禁用; 1-可用;
     */
    Integer status;

}
