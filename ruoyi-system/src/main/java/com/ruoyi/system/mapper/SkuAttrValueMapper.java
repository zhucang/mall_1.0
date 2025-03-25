package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SkuAttrValue;

import java.util.List;

/**
 * 商品SKU和属性值关联Mapper接口
 * 
 * @author ruoyi
 * @date 2024-12-26
 */
public interface SkuAttrValueMapper 
{
    /**
     * 查询商品SKU和属性值关联
     * 
     * @param skuId 商品SKU和属性值关联主键
     * @return 商品SKU和属性值关联
     */
    public SkuAttrValue selectSkuAttrValueBySkuId(Long skuId);

    /**
     * 查询商品SKU和属性值关联列表
     * 
     * @param skuAttrValue 商品SKU和属性值关联
     * @return 商品SKU和属性值关联集合
     */
    public List<SkuAttrValue> selectSkuAttrValueList(SkuAttrValue skuAttrValue);

    /**
     * 新增商品SKU和属性值关联
     * 
     * @param skuAttrValue 商品SKU和属性值关联
     * @return 结果
     */
    public int insertSkuAttrValue(SkuAttrValue skuAttrValue);

    /**
     * 修改商品SKU和属性值关联
     * 
     * @param skuAttrValue 商品SKU和属性值关联
     * @return 结果
     */
    public int updateSkuAttrValue(SkuAttrValue skuAttrValue);

    /**
     * 删除商品SKU和属性值关联
     * 
     * @param skuId 商品SKU和属性值关联主键
     * @return 结果
     */
    public int deleteSkuAttrValueBySkuId(Long skuId);

    /**
     * 批量删除商品SKU和属性值关联
     * 
     * @param skuIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSkuAttrValueBySkuIds(Long[] skuIds);
}
