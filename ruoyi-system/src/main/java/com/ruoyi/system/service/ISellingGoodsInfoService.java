package com.ruoyi.system.service;

import com.ruoyi.system.domain.SellingGoodsInfo;

import java.util.List;

/**
 * 在售商品信息Service接口
 * 
 * @author ruoyi
 * @date 2024-12-20
 */
public interface ISellingGoodsInfoService 
{
    /**
     * 查询在售商品信息
     * 
     * @param id 在售商品信息主键
     * @return 在售商品信息
     */
    public SellingGoodsInfo selectSellingGoodsInfoById(Long id);

    /**
     * 填充其他信息
     * @param sellingGoodsInfo
     * @return
     */
    public void fillOtherInfo(SellingGoodsInfo sellingGoodsInfo);

    /**
     * 查询在售商品信息列表
     * 
     * @param sellingGoodsInfo 在售商品信息
     * @return 在售商品信息集合
     */
    public List<SellingGoodsInfo> selectSellingGoodsInfoList(SellingGoodsInfo sellingGoodsInfo);

    /**
     * 新增在售商品信息
     * 
     * @param sellingGoodsInfo 在售商品信息
     * @return 结果
     */
    public int insertSellingGoodsInfo(SellingGoodsInfo sellingGoodsInfo);

    /**
     * 修改在售商品信息
     * 
     * @param sellingGoodsInfo 在售商品信息
     * @return 结果
     */
    public int updateSellingGoodsInfo(SellingGoodsInfo sellingGoodsInfo);

    /**
     * 批量删除在售商品信息
     * 
     * @param ids 需要删除的在售商品信息主键集合
     * @return 结果
     */
    public int deleteSellingGoodsInfoByIds(Long[] ids);

    /**
     * 删除在售商品信息信息
     * 
     * @param id 在售商品信息主键
     * @return 结果
     */
    public int deleteSellingGoodsInfoById(Long id);

    /**
     * 商户添加商品到店铺
     */
    public int sellerAddGoodsToShop(List<Long> systemGoodsIds);

    /**
     * 商户移除店铺商品
     */
    public int sellerRemoveGoodsFromShop(List<Long> ids);
}
