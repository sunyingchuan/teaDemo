package com.ucar.team.seven.tea.address.service;

import com.ucar.team.seven.tea.address.entity.Adress;
import com.ucar.team.seven.tea.common.service.IBaseService;

import java.util.List;

/**
 * @描述  收货地址接口
 * @参数 $
 * @返回值 $
 * @创建人 yufeng.lin@ucarinc.com
 * @创建时间 $
 * @修改人和其它信息
 */
public interface AdressService extends IBaseService<Adress> {


    /**
     * @Author yufeng.lim@ucarinc.com
     * @Description  根据用户id获取 收货地址列表
     * @Date 10:02 2018/10/16
     * @Param [id] 用户id
     * @return java.util.List<com.ucar.team.seven.tea.address.entity.address>
     **/
     List<Adress> getAdressesByUserId(Long id);

    /**
     * @Author yufeng.lim@ucarinc.com
     * @Description  根据id获取某条 收货地址信息
     * @Date 10:03 2018/10/16
     * @Param [id] 列表的主键
     * @return com.ucar.team.seven.tea.address.entity.address
     **/
    @Override
    Adress getById(Long id);


    /**
     * @Author yufeng.lim@ucarinc.com
     * @Description  根据用户id获取用户的默认地址
     * @Date 10:06 2018/10/16
     * @Param [id] 用户ID
     * @return com.ucar.team.seven.tea.address.entity.address
     **/
    Adress getUserDefaultReceveAdrr(Long id);

    /**
     * @Author yufeng.lim@ucarinc.com
     * @Description  新增地址
     * @Date 10:06 2018/10/16
     * @Param [entity]
     * @return int
     **/
    @Override
    Integer insert(Adress entity);

    /**
     * @Author yufeng.lim@ucarinc.com
     * @Description  修改 ，null 值字段不修改
     * @Date 10:08 2018/10/16
     * @Param [entity] 包含更新信息 id不为空
     * @return int
     **/
    @Override
    Integer update(Adress entity);


    /**
     * @Author yufeng.lim@ucarinc.com
     * @Description  根据id删除收货地址
     * @Date 10:09 2018/10/16
     * @Param id 主键
     * @return int
     **/
    Integer deleteAddrById(Long id);

    /**
     * @Author yufeng.lim@ucarinc.com
     * @Description  批量删除
     * @Date 10:11 2018/10/16
     * @Param [delIds] 封装要删除的字符串 中间用 ‘逗号’分隔
     * @return int
     **/
    Integer deleteBatch(String delIds);
    
    /**
     * @Author yufeng.lim@ucarinc.com
     * @Description  根据用户id'获取该用户的收货地址数目
     * @Date 10:35 2018/10/16
     * @Param 用户id
     * @return java.lang.Integer
     **/
    Integer countAdressTotalByUserId(Long id);
    
    /**
     * @Author yufeng.lim@ucarinc.com
     * @Description  设置默认收货地址
     * @Date 13:48 2018/10/16
     * @Param [id] 收货地址id
     * @return java.lang.Integer
     **/
    Integer setDefaultAddr(Long id,Long customerId);
    

}




















