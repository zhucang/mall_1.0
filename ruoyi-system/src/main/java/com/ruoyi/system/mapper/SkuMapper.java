package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Sku;

import java.util.List;

/**
 * 商品skuMapper接口
 * 
 * @author ruoyi
 * @date 2024-12-24
 */
public interface SkuMapper 
{
    /**
     * 查询商品sku
     * 
     * @param id 商品sku主键
     * @return 商品sku
     */
    public Sku selectSkuById(Long id);

    /**
     * 查询商品sku列表
     * 
     * @param sku 商品sku
     * @return 商品sku集合
     */
    public List<Sku> selectSkuList(Sku sku);

    /**
     * 新增商品sku
     * 
     * @param sku 商品sku
     * @return 结果
     */
    public int insertSku(Sku sku);
    public int insertSku2(Sku sku);

    /**
     * 修改商品sku
     * 
     * @param sku 商品sku
     * @return 结果
     */
    public int updateSku(Sku sku);

    /**
     * 删除商品sku
     * 
     * @param id 商品sku主键
     * @return 结果
     */
    public int deleteSkuById(Long id);

    /**
     * 批量删除商品sku
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSkuByIds(Long[] ids);
}
