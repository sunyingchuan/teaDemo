package com.ucar.team.seven.tea.category.service;

import com.ucar.team.seven.tea.category.entity.Category;
import com.ucar.team.seven.tea.category.entity.Node;
import com.ucar.team.seven.tea.category.vo.CategoryVo;
import com.ucar.team.seven.tea.common.service.IBaseService;

import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Kyrie(lingyi.huang @ ucarinc.com)
 * @Date 2018/10/12 14:40
 * @Version 1.0
 * @Description 描述
 */
public interface CategoryService extends IBaseService<Category> {
    /**
     * 分类树查询
     *
     * @param
     * @return
     */
    List<Node> findTree(Integer id);

    /**
     * 查询一级分类
     *
     * @param
     * @return
     */
    List<CategoryVo> findTopCategory();

    /**
     * 查询二级分类
     *
     * @param
     * @return
     */
    List<CategoryVo> findSecondCategory();
    /**
     * 根据分类ID查询分类
     *
     *
     * @param
     * @return
     */
    CategoryVo findCategoryById(Long categoryId);

    /**
     * 添加根级分类
     *
     *
     * @param
     * @return
     */
    int insertParent(Category category);
    /**
     * 判断分类是否可以删除
     *
     *
     * @param
     * @return
     */
    int deletePre(CategoryVo categoryVo);


}
