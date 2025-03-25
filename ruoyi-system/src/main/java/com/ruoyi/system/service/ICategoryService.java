package com.ruoyi.system.service;

import com.ruoyi.system.domain.Category;
import com.ruoyi.system.domain.LangMgr;

import java.util.List;

/**
 * 商品分类信息Service接口
 * 
 * @author ruoyi
 * @date 2024-12-03
 */
public interface ICategoryService 
{
    /**
     * 查询商品分类信息
     * 
     * @param id 商品分类信息主键
     * @return 商品分类信息
     */
    public Category selectCategoryById(String id);

    /**
     * 查询商品分类信息列表
     * 
     * @param category 商品分类信息
     * @return 商品分类信息集合
     */
    public List<Category> selectCategoryList(Category category);

    /**
     * 查询所有商品分类
     *
     * @return 商品分类信息集合
     */
    public List<Category> selectAllCategory();

    /**
     * 新增商品分类信息
     * 
     * @param category 商品分类信息
     * @return 结果
     */
    public int insertCategory(Category category);

    /**
     * 修改商品分类信息
     * 
     * @param category 商品分类信息
     * @return 结果
     */
    public int updateCategory(Category category);

    /**
     * 修改商品分类名称多语言配置
     * @param categoryId 商品分类ID
     * @param categoryNameLang 商品分类名称多语言
     * @return
     */
    public int updateCategoryNameLang(Long categoryId, LangMgr categoryNameLang);

    /**
     * 批量删除商品分类信息
     * 
     * @param ids 需要删除的商品分类信息主键集合
     * @return 结果
     */
    public int deleteCategoryByIds(String[] ids);

    /**
     * 删除商品分类信息信息
     * 
     * @param id 商品分类信息主键
     * @return 结果
     */
    public int deleteCategoryById(String id);
}
