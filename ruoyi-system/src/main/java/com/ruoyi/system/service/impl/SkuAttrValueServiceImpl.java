package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SkuAttrValueMapper;
import com.ruoyi.system.domain.SkuAttrValue;
import com.ruoyi.system.service.ISkuAttrValueService;

import javax.annotation.Resource;

/**
 * 商品SKU和属性值关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-12-26
 */
@Service
public class SkuAttrValueServiceImpl implements ISkuAttrValueService 
{
    @Resource
    private SkuAttrValueMapper skuAttrValueMapper;

    /**
     * 查询商品SKU和属性值关联
     * 
     * @param skuId 商品SKU和属性值关联主键
     * @return 商品SKU和属性值关联
     */
    @Override
    public SkuAttrValue selectSkuAttrValueBySkuId(Long skuId)
    {
        return skuAttrValueMapper.selectSkuAttrValueBySkuId(skuId);
    }

    /**
     * 查询商品SKU和属性值关联列表
     * 
     * @param skuAttrValue 商品SKU和属性值关联
     * @return 商品SKU和属性值关联
     */
    @Override
    public List<SkuAttrValue> selectSkuAttrValueList(SkuAttrValue skuAttrValue)
    {
        return skuAttrValueMapper.selectSkuAttrValueList(skuAttrValue);
    }

    /**
     * 新增商品SKU和属性值关联
     * 
     * @param skuAttrValue 商品SKU和属性值关联
     * @return 结果
     */
    @Override
    public int insertSkuAttrValue(SkuAttrValue skuAttrValue)
    {
        return skuAttrValueMapper.insertSkuAttrValue(skuAttrValue);
    }

    /**
     * 修改商品SKU和属性值关联
     * 
     * @param skuAttrValue 商品SKU和属性值关联
     * @return 结果
     */
    @Override
    public int updateSkuAttrValue(SkuAttrValue skuAttrValue)
    {
        return skuAttrValueMapper.updateSkuAttrValue(skuAttrValue);
    }

    /**
     * 批量删除商品SKU和属性值关联
     * 
     * @param skuIds 需要删除的商品SKU和属性值关联主键
     * @return 结果
     */
    @Override
    public int deleteSkuAttrValueBySkuIds(Long[] skuIds)
    {
        return skuAttrValueMapper.deleteSkuAttrValueBySkuIds(skuIds);
    }

    /**
     * 删除商品SKU和属性值关联信息
     * 
     * @param skuId 商品SKU和属性值关联主键
     * @return 结果
     */
    @Override
    public int deleteSkuAttrValueBySkuId(Long skuId)
    {
        return skuAttrValueMapper.deleteSkuAttrValueBySkuId(skuId);
    }
}
