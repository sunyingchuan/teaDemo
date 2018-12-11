package com.ucar.team.seven.tea.point.controller;

import com.ucar.team.seven.tea.common.controller.BaseController;
import com.ucar.team.seven.tea.common.exception.ServiceException;
import com.ucar.team.seven.tea.common.web.PageResult;
import com.ucar.team.seven.tea.common.web.Result;
import com.ucar.team.seven.tea.point.entity.PointLog;
import com.ucar.team.seven.tea.point.service.PointLogService;
import com.ucar.team.seven.tea.point.vo.PointLogVo;
import com.ucar.team.seven.tea.point.vo.params.PointParamVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author 徐楷翔(kaixiang.xu@ucarinc.com)
 * @Date 2018/10/11 14:18
 * @Version 1.0
 * @Description 积分记录控制类
 */
@Controller
@RequestMapping("point")
public class PointLogController extends BaseController {

    @Resource
    private PointLogService pointLogService;

    /**
     * 积分管理列表页面
     *
     * @return
     */
    @RequestMapping("list.do")
    public String listUI() {
        return "point/point_list";
    }

    /**
     * 查询积分记录列表
     *
     * @param pointParamVo
     * @return
     */
    @RequestMapping(value = "list.do", method = RequestMethod.POST)
    @ResponseBody
    public PageResult find(PointParamVo pointParamVo) {
        List<PointLogVo> list = pointLogService.findPage(pointParamVo);
        return getPageResult(list, pointParamVo);
    }

    /**
     * 保存积分记录
     *
     * @param pointLog
     * @return
     */
    @RequestMapping("save.do")
    @ResponseBody
    public Result doSaveObject(PointLog pointLog) {
        try{
            pointLogService.addPointLog(pointLog);
        }catch (ServiceException e){
            return getErrorResult(e.getMessage());
        }
        return getResult();
    }

    /**
     * 批量增加积分记录
     *
     * @param customerIds,pointLog
     * @return
     */
    @RequestMapping("batchInsert.do")
    @ResponseBody
    public Result doBatchInsert(@RequestParam("ids") Long[] customerIds, PointLog pointLog) {
        try {
            pointLogService.pointLogBatchInsert(customerIds, pointLog);
        }catch (ServiceException e){
            return getErrorResult(e.getMessage());
        }
        return getResult();
    }

}
