package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Category;
import com.ruoyi.system.domain.LangMgr;
import com.ruoyi.system.mapper.CategoryMapper;
import com.ruoyi.system.service.ICategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品分类信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-12-03
 */
@Service
public class CategoryServiceImpl implements ICategoryService 
{
    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 查询商品分类信息
     * 
     * @param id 商品分类信息主键
     * @return 商品分类信息
     */
    @Override
    public Category selectCategoryById(String id)
    {
        return categoryMapper.selectCategoryById(id);
    }

    /**
     * 查询商品分类信息列表
     * 
     * @param category 商品分类信息
     * @return 商品分类信息
     */
    @Override
    public List<Category> selectCategoryList(Category category)
    {
        return categoryMapper.selectCategoryList(category);
    }

    /**
     * 查询商品分类信息列表
     *
     * @return 商品分类信息
     */
    @Override
    public List<Category> selectAllCategory()
    {
        //商品分类信息
        Category category = new Category();
        category.setVisibleFlag(1);
        List<Category> categories = categoryMapper.selectCategoryList(category);
        //商品分类信息列表map
        Map<Long, List<Category>> categoryMap = categories.stream().collect(Collectors.groupingBy(a -> a.getParentId()));

        //一级商品分类
        List<Category> level1Category = categoryMap.get(0L);
        if (level1Category == null){
            throw new RuntimeException();
        }
        for (int i = 0; i < level1Category.size(); i++) {
            //一级分类ID
            Long id = level1Category.get(i).getId();
            //二级分类
            List<Category> level2Category = categoryMap.get(id);
            level1Category.get(i).getParams().put("sonCategory",level2Category);
        }
        return level1Category;
    }

    /**
     * 新增商品分类信息
     * 
     * @param category 商品分类信息
     * @return 结果
     */
    @Override
    public int insertCategory(Category category)
    {
        category.setCreateTime(DateUtils.getNowDate());
        return categoryMapper.insertCategory(category);
    }

    /**
     * 修改商品分类信息
     * 
     * @param category 商品分类信息
     * @return 结果
     */
    @Override
    public int updateCategory(Category category)
    {
        category.setUpdateTime(DateUtils.getNowDate());
        return categoryMapper.updateCategory(category);
    }

    /**
     * 修改商品分类名称多语言配置
     * @param categoryId 商品分类ID
     * @param categoryNameLang 商品分类名称多语言
     * @return
     */
    @Override
    public int updateCategoryNameLang(Long categoryId, LangMgr categoryNameLang){
        Category category = new Category();
        category.setId(categoryId);
        category.setCategoryNameLang(categoryNameLang);
        category.setUpdateTime(DateUtils.getNowDate());
        return categoryMapper.updateCategory(category);
    }

    /**
     * 批量删除商品分类信息
     * 
     * @param ids 需要删除的商品分类信息主键
     * @return 结果
     */
    @Override
    public int deleteCategoryByIds(String[] ids)
    {
        return categoryMapper.deleteCategoryByIds(ids);
    }

    /**
     * 删除商品分类信息信息
     * 
     * @param id 商品分类信息主键
     * @return 结果
     */
    @Override
    public int deleteCategoryById(String id)
    {
        return categoryMapper.deleteCategoryById(id);
    }
}
