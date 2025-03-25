package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SkuAttrValue;

/**
 * 商品SKU和属性值关联Service接口
 * 
 * @author ruoyi
 * @date 2024-12-26
 */
public interface ISkuAttrValueService 
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
     * 批量删除商品SKU和属性值关联
     * 
     * @param skuIds 需要删除的商品SKU和属性值关联主键集合
     * @return 结果
     */
    public int deleteSkuAttrValueBySkuIds(Long[] skuIds);

    /**
     * 删除商品SKU和属性值关联信息
     * 
     * @param skuId 商品SKU和属性值关联主键
     * @return 结果
     */
    public int deleteSkuAttrValueBySkuId(Long skuId);
}
