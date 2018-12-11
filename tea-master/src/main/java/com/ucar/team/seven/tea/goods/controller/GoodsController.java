package com.ucar.team.seven.tea.goods.controller;

import com.ucar.team.seven.tea.common.controller.BaseController;
import com.ucar.team.seven.tea.common.enums.GoodsStatus;
import com.ucar.team.seven.tea.common.util.ShiroUtil;
import com.ucar.team.seven.tea.common.web.PageResult;
import com.ucar.team.seven.tea.common.web.Result;
import com.ucar.team.seven.tea.goods.entity.Goods;
import com.ucar.team.seven.tea.goods.service.GoodsService;
import com.ucar.team.seven.tea.goods.vo.GoodsVo;
import com.ucar.team.seven.tea.goods.vo.params.GoodsParamVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Kyrie(lingyi.huang @ ucarinc.com)
 * @Date 2018/9/26 8:52
 * @Version 1.0
 * @Description
 */
@Controller
@RequestMapping("goods")
public class GoodsController extends BaseController {
    @Resource
    private GoodsService goodsService;

    /**
     * 商品列表页面
     *
     * @return
     */
    @RequestMapping("list.do")
    public String listUI() {
        return "goods/goods_list";
    }
    /**
     * 查询商品列表
     *
     * @param goodsParamVo
     * @return
     */
    @RequestMapping(value = "list.do", method = RequestMethod.POST)
    @ResponseBody
    public PageResult find(GoodsParamVo goodsParamVo) {
        List<GoodsVo> list = goodsService.findPage(goodsParamVo);
        return getPageResult(list, goodsParamVo);
    }

    /**
     * 新增商品信息
     *
     * @param goods
     * @return
     */
    @RequestMapping("save.do")
    @ResponseBody
    public Result doSaveObject(Goods goods) {
        //获取用户角色
        goods.setCreateEmp(goodsService.getEmp());
        goods.setModifyEmp(goodsService.getEmp());
        //插入商品信息
        goodsService.insert(goods);
        return getResult();
    }

    /**
     * 修改商品信息
     *
     * @param goods
     * @return
     */
    @RequestMapping("update.do")
    @ResponseBody
    public Result update(Goods goods) {
        goods.setModifyEmp(goodsService.getEmp());
        goodsService.update(goods);
        return getResult();
    }
    /**
     * 上/下架商品
     *
     * @param id,status
     * @return
     */
    @RequestMapping("status.do")
    @ResponseBody
    public Result updateStatus(Long id, Integer status) {
        Integer newStatus;
        if( status == GoodsStatus.INVALID.getValue() ){
            newStatus = GoodsStatus.VALID.getValue();
        }else{
            newStatus = GoodsStatus.INVALID.getValue();
        }
        goodsService.updateStatus(id, newStatus);
        return getResult();
    }

    /**
     * 批量上/下架商品
     *
     * @param ids,status
     * @return
     */
    @RequestMapping("batchStatus.do")
    @ResponseBody
    public Result batchStatus(String ids, Integer status) {

        String[] ss = ids.split(",");
        //将String数组转化为Long数组
        Long[] ssLong = new Long[ss.length];
        for (int i = 0; i < ss.length; i++) {
            ssLong[i] = Long.valueOf(ss[i]);
        }
        //多个参数使用map传递
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("ids",ssLong); // ids = {1,2,4}
        map.put("status",status);// status = "0,1"
        goodsService.batchStatus(map);
        return getResult();
    }


    /**
     * 根据id商品信息
     *
     * @param goodsId
     * @return
     */
    @RequestMapping("getById.do")
    @ResponseBody
    public Result getById(Long goodsId) {
        GoodsVo goodsVo=goodsService.getByGoodsId(goodsId);
        return getEntityResult(goodsVo);
    }

    /**
     * 查询一级分类下的所有商品
     *
     * @param goodsParamVo
     * @return
     */
    @RequestMapping("getByCode.do")
    @ResponseBody
    public Result getByCode(GoodsParamVo goodsParamVo) {
        List<GoodsVo>  goodsVoList = goodsService.getByCode(goodsParamVo);
        return getEntityResult(goodsVoList);
    }

    /**
     * 上传商品图片
     *
     * @param request
     * @return
     */
    @RequestMapping("upload.do")
    @ResponseBody
    public String  upload(HttpServletRequest request) throws IllegalStateException, IOException
    {
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();

            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    String path="D:/upload/goodsUpload"+file.getOriginalFilename();
                    //上传
                    file.transferTo(new File(path));
                }

            }

        }
        return "/success";
    }
}