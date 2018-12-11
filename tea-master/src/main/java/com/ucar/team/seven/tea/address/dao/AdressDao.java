package com.ucar.team.seven.tea.address.dao;

import com.ucar.team.seven.tea.address.entity.Adress;
import com.ucar.team.seven.tea.common.dao.BaseDao;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @描述
 * @参数 $
 * @返回值 $
 * @创建人 yufeng.lin@ucarinc.com
 * @创建时间 $
 * @修改人和其它信息
 */
public interface AdressDao extends BaseDao<Adress> {


    List<Adress> getAdressesByUserId(Long id);

    Adress getUserDefaultReceveAdrr(Long id);

    Integer countAdressTotalByUserId(Long id);

    Integer deleteBatch(String[] array);

    Integer deleteAddrById(Long id);

    Integer setDefaultAddrById(@Param("id")Long id,@Param("defSign")Integer defSign);

    Integer cancleOldDefaultAddr(@Param("customerId")Long customerId, @Param("cancelSign")Integer cancelSign);

    @Override
    Adress getById(Long id);
}
