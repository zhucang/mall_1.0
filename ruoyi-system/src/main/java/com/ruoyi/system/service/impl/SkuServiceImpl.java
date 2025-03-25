package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Sku;
import com.ruoyi.system.mapper.SkuMapper;
import com.ruoyi.system.service.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品skuService业务层处理
 * 
 * @author ruoyi
 * @date 2024-12-24
 */
@Service
public class SkuServiceImpl implements ISkuService 
{
    @Autowired
    private SkuMapper skuMapper;

    /**
     * 查询商品sku
     * 
     * @param id 商品sku主键
     * @return 商品sku
     */
    @Override
    public Sku selectSkuById(Long id)
    {
        return skuMapper.selectSkuById(id);
    }

    /**
     * 查询商品sku列表
     * 
     * @param sku 商品sku
     * @return 商品sku
     */
    @Override
    public List<Sku> selectSkuList(Sku sku)
    {
        return skuMapper.selectSkuList(sku);
    }

    /**
     * 新增商品sku
     * 
     * @param sku 商品sku
     * @return 结果
     */
    @Override
    public int insertSku(Sku sku)
    {
        sku.setCreateTime(DateUtils.getNowDate());
        return skuMapper.insertSku(sku);
    }

    /**
     * 修改商品sku
     * 
     * @param sku 商品sku
     * @return 结果
     */
    @Override
    public int updateSku(Sku sku)
    {
        sku.setUpdateTime(DateUtils.getNowDate());
        return skuMapper.updateSku(sku);
    }

    /**
     * 批量删除商品sku
     * 
     * @param ids 需要删除的商品sku主键
     * @return 结果
     */
    @Override
    public int deleteSkuByIds(Long[] ids)
    {
        return skuMapper.deleteSkuByIds(ids);
    }

    /**
     * 删除商品sku信息
     * 
     * @param id 商品sku主键
     * @return 结果
     */
    @Override
    public int deleteSkuById(Long id)
    {
        return skuMapper.deleteSkuById(id);
    }
}
