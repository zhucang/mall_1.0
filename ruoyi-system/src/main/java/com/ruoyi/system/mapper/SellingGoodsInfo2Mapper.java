package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SellingGoodsInfo2;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统商品信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-12-06
 */
public interface SellingGoodsInfo2Mapper 
{
    /**
     * 查询系统商品信息
     * 
     * @param id 系统商品信息主键
     * @return 系统商品信息
     */
    public SellingGoodsInfo2 selectSellingGoodsInfo2ById(Long id);

    /**
     * 查询系统商品信息列表
     * 
     * @param sellingGoodsInfo2 系统商品信息
     * @return 系统商品信息集合
     */
    public List<SellingGoodsInfo2> selectSellingGoodsInfo2List(SellingGoodsInfo2 sellingGoodsInfo2);

    /**
     * 新增系统商品信息
     * 
     * @param sellingGoodsInfo2 系统商品信息
     * @return 结果
     */
    public int insertSellingGoodsInfo2(SellingGoodsInfo2 sellingGoodsInfo2);
    public int insertSellingGoodsInfo2s(@Param("sellingGoodsInfo2s") List<SellingGoodsInfo2> sellingGoodsInfo2s);

    /**
     * 修改系统商品信息
     * 
     * @param sellingGoodsInfo2 系统商品信息
     * @return 结果
     */
    public int updateSellingGoodsInfo2(SellingGoodsInfo2 sellingGoodsInfo2);

    /**
     * 删除系统商品信息
     * 
     * @param id 系统商品信息主键
     * @return 结果
     */
    public int deleteSellingGoodsInfo2ById(Long id);

    /**
     * 批量删除系统商品信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSellingGoodsInfo2ByIds(Long[] ids);
}
