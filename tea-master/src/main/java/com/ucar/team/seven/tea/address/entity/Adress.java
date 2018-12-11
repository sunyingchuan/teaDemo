package com.ucar.team.seven.tea.address.entity;

import com.ucar.team.seven.tea.common.entity.BaseEntity;
import lombok.Data;

/**
 * @描述
 * @参数 $
 * @返回值 $
 * @创建人 yufeng.lin@ucarinc.com
 * @创建时间 $
 * @修改人和其它信息
 */
@Data
public class Adress extends BaseEntity {

    //客户id
    private Long customerId;
    //收货地址姓名
    private String name;
    //性别
    private Integer sex;
    //收货手机
    private String mobile;
    //省
    private String province;
    //市
    private String city;
    //区
    private String area;

    //排除选的省市区，用户自己填的详细地址
    private String detailAddress;

    //该地址是否默认地址
    private Integer isDef;








}
