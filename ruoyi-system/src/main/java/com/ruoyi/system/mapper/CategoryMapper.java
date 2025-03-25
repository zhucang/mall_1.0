package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Category;

import java.util.List;

/**
 * 商品分类信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-12-03
 */
public interface CategoryMapper 
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
     * 删除商品分类信息
     * 
     * @param id 商品分类信息主键
     * @return 结果
     */
    public int deleteCategoryById(String id);

    /**
     * 批量删除商品分类信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCategoryByIds(String[] ids);
}
