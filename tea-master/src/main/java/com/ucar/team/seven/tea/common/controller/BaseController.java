package com.ucar.team.seven.tea.common.controller;

import com.ucar.team.seven.tea.common.web.Page;
import com.ucar.team.seven.tea.common.web.PageResult;
import com.ucar.team.seven.tea.common.web.Result;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/26 8:42
 * @Version 1.0
 * @Description controller基类
 */
public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    /**
     * 获取请求request中的参数
     *
     * @param request
     * @return
     * @throws Exception
     */
    public static HashMap<String, Object> getParameterMap(HttpServletRequest request) throws Exception {
        Enumeration paramNames = request.getParameterNames();

        HashMap paramMap = new HashMap(16);
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            paramMap.put(paramName, request.getParameter(paramName));
        }
        return paramMap;
    }

    /**
     * 返回分页result，用于前端bootstrap table
     *
     * @param list
     * @param page
     * @return
     */
    public static PageResult getPageResult(List list, Page page) {
        PageResult result = new PageResult<>();
        result.setState(Result.SUCCESS);
        result.setMessage("共查询出" + page.getRowCount() + "条数据!");
        result.setRecords(list);
        result.setTotal(page.getRowCount());
        return result;
    }

    /**
     * 返回带实体的result
     *
     * @param entity
     * @return
     */
    public static Result getEntityResult(Object entity) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("entity", entity);
        return new Result(map);
    }

    /**
     * 返回通用的successResult
     *
     * @return
     */
    public static Result getResult() {
        return new Result();
    }

    /**
     * 返回通用的ErrorResult
     *
     * @return
     */
    public static Result getErrorResult(String errorMsg) {
        return new Result(Result.ERROR,errorMsg);
    }
}
