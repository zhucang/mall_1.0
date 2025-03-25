package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SellingGoodsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 在售商品信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-12-20
 */
public interface SellingGoodsInfoMapper 
{
    /**
     * 查询在售商品信息
     * 
     * @param id 在售商品信息主键
     * @return 在售商品信息
     */
    public SellingGoodsInfo selectSellingGoodsInfoById(Long id);

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
     * 批量新增在售商品信息
     *
     * @param sellingGoodsInfos 在售商品信息
     * @return 结果
     */
    public int insertSellingGoodsInfos(@Param("list") List<SellingGoodsInfo> sellingGoodsInfos);

    /**
     * 修改在售商品信息
     * 
     * @param sellingGoodsInfo 在售商品信息
     * @return 结果
     */
    public int updateSellingGoodsInfo(SellingGoodsInfo sellingGoodsInfo);

    /**
     * 删除在售商品信息
     * 
     * @param id 在售商品信息主键
     * @return 结果
     */
    public int deleteSellingGoodsInfoById(Long id);

    /**
     * 批量删除在售商品信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSellingGoodsInfoByIds(Long[] ids);
}
