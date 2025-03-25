package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.ShopInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 店铺信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-12-21
 */
public interface ShopInfoMapper 
{
    /**
     * 查询店铺信息
     * 
     * @param id 店铺信息主键
     * @return 店铺信息
     */
    public ShopInfo selectShopInfoById(Long id);

    /**
     * 查询店铺信息列表
     * 
     * @param shopInfo 店铺信息
     * @return 店铺信息集合
     */
    public List<ShopInfo> selectShopInfoList(ShopInfo shopInfo);

    /**
     * 新增店铺信息
     * 
     * @param shopInfo 店铺信息
     * @return 结果
     */
    public int insertShopInfo(ShopInfo shopInfo);

    /**
     * 修改店铺信息
     * 
     * @param shopInfo 店铺信息
     * @return 结果
     */
    public int updateShopInfo(ShopInfo shopInfo);

    /**
     * 删除店铺信息
     * 
     * @param id 店铺信息主键
     * @return 结果
     */
    public int deleteShopInfoById(Long id);

    /**
     * 批量删除店铺信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShopInfoByIds(Long[] ids);

    /**
     * 变更店铺粉丝数量
     */
    public int updateShopFansNum(@Param("shopInfoId") Long shopInfoId, @Param("changeNum") Integer changeNum);
}
