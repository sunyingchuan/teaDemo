package com.ucar.team.seven.tea.category.controller;



import com.ucar.team.seven.tea.category.entity.Category;
import com.ucar.team.seven.tea.category.entity.Node;
import com.ucar.team.seven.tea.category.service.CategoryService;
import com.ucar.team.seven.tea.category.vo.CategoryVo;
import com.ucar.team.seven.tea.category.vo.params.CategoryParamVo;
import com.ucar.team.seven.tea.common.controller.BaseController;
import com.ucar.team.seven.tea.common.enums.CategoryDelStatus;
import com.ucar.team.seven.tea.common.web.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Kyrie(lingyi.huang @ ucarinc.com)
 * @Date 2018/10/12 14:20
 * @Version 1.0
 * @Description 商品分类Controller
 */
@Controller
@RequestMapping("categoryTree")
public class CategoryController extends BaseController {
    @Resource
    private CategoryService categoryService;

    /**
     * 商品分类页面
     *
     * @return
     */
    @RequestMapping("list.do")
    public String listUI() {
        return "category/category_list";
    }
    /**
     * 商品分类列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "list.do", method = RequestMethod.POST)
    @ResponseBody
    public List<Node> find() {
        List<Node> result = categoryService.findTree(0);
        return result;
    }

    /**
     * 商品一级分类列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "findTopCategory.do", method = RequestMethod.POST)
    @ResponseBody
    public List<CategoryVo> findTopCategory() {
        List<CategoryVo> topCategory= categoryService.findTopCategory();
        return topCategory;
    }

    /**
     * 商品二级级分类列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "findSecondCategory.do", method = RequestMethod.POST)
    @ResponseBody
    public List<CategoryVo> findSecondCategory(Long id) {
        System.out.println(id);
        List<CategoryVo> secondCategory= categoryService.findSecondCategory();
        return secondCategory;
    }

    /**
     * 商品分类页面
     *
     * @return
     */
    @RequestMapping(value = "findCategoryById.do", method = RequestMethod.POST)
    @ResponseBody
    public Result findCategoryById(Long categoryId){
        CategoryVo categoryVo=categoryService.findCategoryById(categoryId);
        List<CategoryVo> topCategory= categoryService.findTopCategory();
        categoryVo.setTopCategory(topCategory);
        return getEntityResult(categoryVo);
    }

    /**
     * 修改商品分类信息
     *
     * @param category
     * @return
     */
    @RequestMapping("update.do")
    @ResponseBody
    public Result update(Category category) {
        category.setModifyEmp(categoryService.getEmp());
        categoryService.update(category);
        return getResult();
    }

    /**
     * 保存商品子集分类信息
     *
     * @param category
     * @return
     */
    @RequestMapping("save.do")
    @ResponseBody
    public Result save(Category category) {
        category.setCreateEmp(categoryService.getEmp());
        category.setModifyEmp(categoryService.getEmp());
        categoryService.insert(category);
        return getResult();
    }

    /**
     * 保存商品根分类信息
     *
     * @param category
     * @return
     */
    @RequestMapping("saveParent.do")
    @ResponseBody
    public Result saveParent(Category category) {
        category.setCreateEmp(categoryService.getEmp());
        category.setModifyEmp(categoryService.getEmp());
        categoryService.insertParent(category);
        return getResult();
    }

    /**
     * 判断是否可以删除商品分类
     *
     * @param categoryVo
     * @return
     */
    @RequestMapping("deletePre.do")
    @ResponseBody
    public Result deletePre(CategoryVo categoryVo) {
        int i = categoryService.deletePre(categoryVo);
        if (i == CategoryDelStatus.NO_EXIST_GOODSANDSONCATEGORY.getValue()) {
            categoryService.deleteById(categoryVo.getId());
            return getResult();
        } else if (i == CategoryDelStatus.EXIST_GOODS.getValue()){
            return getErrorResult(CategoryDelStatus.EXIST_GOODS.getStatus());
        }else{
            return getErrorResult(CategoryDelStatus.EXIST_SONCATEGORY.getStatus());
        }
    }

}
