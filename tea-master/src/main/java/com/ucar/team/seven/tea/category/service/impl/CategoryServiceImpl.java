package com.ucar.team.seven.tea.category.service.impl;

import com.ucar.team.seven.tea.category.dao.CategoryDao;
import com.ucar.team.seven.tea.category.entity.Category;
import com.ucar.team.seven.tea.category.entity.Node;
import com.ucar.team.seven.tea.category.service.CategoryService;
import com.ucar.team.seven.tea.category.vo.CategoryVo;
import com.ucar.team.seven.tea.common.dao.BaseDao;
import com.ucar.team.seven.tea.common.service.impl.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Kyrie(lingyi.huang @ ucarinc.com)
 * @Date 2018/10/12 15:10
 * @Version 1.0
 * @Description 描述
 */
@Service("categoryService")
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl extends BaseService<Category> implements CategoryService {
    @Resource
    private CategoryDao categoryDao;

    @Override
    public BaseDao<Category> getDao() {
        return categoryDao;
    }

    @Override
    public List<Node> findTree(Integer id){
       return categoryDao.findTree(id);
    }

    @Override
    public List<CategoryVo> findTopCategory(){
            return categoryDao.findTopCategory();
    }

    @Override
    public List<CategoryVo> findSecondCategory() {
        return categoryDao.findSecondCategory();
    }

    @Override
    public CategoryVo findCategoryById(Long categoryId) {
        return categoryDao.findCategoryById(categoryId);
    }

    @Override
    public int insertParent(Category category) {
        return categoryDao.insertParent(category);
    }

    @Override
    public int deletePre(CategoryVo categoryVo) {
        Long goodsNumber = categoryDao.deleteWhetherExistGoods(categoryVo);
        Long sonCategoryNumber = categoryDao.deleteWhetherExistSonCategory(categoryVo);
        if((goodsNumber==0)&&(sonCategoryNumber==0)){
            return 1;
        }else if(goodsNumber>0){
            return 2;
        }else{
            return 3;
        }
    }


}
