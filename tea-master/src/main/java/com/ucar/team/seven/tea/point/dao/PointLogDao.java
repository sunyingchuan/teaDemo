package com.ucar.team.seven.tea.point.dao;

import com.ucar.team.seven.tea.common.dao.BaseDao;
import com.ucar.team.seven.tea.point.entity.PointLog;
import com.ucar.team.seven.tea.point.vo.PointLogVo;
import com.ucar.team.seven.tea.point.vo.params.PointParamVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author 徐楷翔(kaixiang.xu@ucarinc.com)
 * @Date 2018/10/11 14:18
 * @Version 1.0
 * @Description 积分记录Dao
 */
public interface PointLogDao extends BaseDao<PointLog> {

    /**
     * 分页查询
     *
     * @param pointParamVo
     * @return
     */
    List<PointLogVo> findPage(@Param("params") PointParamVo pointParamVo);

    /**
     * 获取数量
     *
     * @param pointParamVo
     * @return
     */
    Long getCount(@Param("params") PointParamVo pointParamVo);


}
