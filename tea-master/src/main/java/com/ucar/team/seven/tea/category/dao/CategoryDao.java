package com.ucar.team.seven.tea.category.dao;

import com.ucar.team.seven.tea.category.entity.Category;
import com.ucar.team.seven.tea.category.entity.Node;
import com.ucar.team.seven.tea.category.vo.CategoryVo;
import com.ucar.team.seven.tea.common.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Kyrie(lingyi.huang @ ucarinc.com)
 * @Date 2018/10/12 15:12
 * @Version 1.0
 * @Description 描述
 */
public interface CategoryDao extends BaseDao<Category> {
    /**
     * 获取分类树
     *
     * @param
     * @return
     */
    List<Node> findTree(Integer id);

    /**
    * 获取一级分类
    *
    * @param
    * @return
    */
    List<CategoryVo> findTopCategory();

    /**
     * 获取二级分类
     *
     * @param
     * @return
     */
    List<CategoryVo> findSecondCategory();

    /**
     * 根据id查找分类
     *
     * @param
     * @return
     */
    CategoryVo findCategoryById(Long categoryId);

    int insertParent(Category category);
    /**
     * 删除分类前判断是否存在商品
     *
     * @param
     * @return
     */
   Long deleteWhetherExistGoods(@Param("params")CategoryVo categoryVo);
    /**
     * 删除分类前判断是否存在子级分类
     *
     * @param
     * @return
     */
    Long deleteWhetherExistSonCategory(@Param("params")CategoryVo categoryVo);
}
