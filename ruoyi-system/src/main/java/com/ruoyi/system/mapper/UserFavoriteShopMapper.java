package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.UserFavoriteShop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户店铺收藏Mapper接口
 * 
 * @author ruoyi
 * @date 2024-12-24
 */
public interface UserFavoriteShopMapper 
{
    /**
     * 查询用户店铺收藏
     * 
     * @param id 用户店铺收藏主键
     * @return 用户店铺收藏
     */
    public UserFavoriteShop selectUserFavoriteShopById(Long id);

    /**
     * 查询用户店铺收藏列表
     * 
     * @param userFavoriteShop 用户店铺收藏
     * @return 用户店铺收藏集合
     */
    public List<UserFavoriteShop> selectUserFavoriteShopList(UserFavoriteShop userFavoriteShop);

    /**
     * 新增用户店铺收藏
     * 
     * @param userFavoriteShop 用户店铺收藏
     * @return 结果
     */
    public int insertUserFavoriteShop(UserFavoriteShop userFavoriteShop);

    /**
     * 修改用户店铺收藏
     * 
     * @param userFavoriteShop 用户店铺收藏
     * @return 结果
     */
    public int updateUserFavoriteShop(UserFavoriteShop userFavoriteShop);

    /**
     * 删除用户店铺收藏
     * 
     * @param id 用户店铺收藏主键
     * @return 结果
     */
    public int deleteUserFavoriteShopById(Long id);

    /**
     * 批量删除用户店铺收藏
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserFavoriteShopByIds(Long[] ids);

    /**
     * 获取用户是否收藏状态
     *
     * @param userId 用户ID
     * @param shopInfoId 店铺信息ID
     * @return 结果
     */
    public int getUserFavoriteStatus(@Param("userId") Long userId, @Param("shopInfoId") Long shopInfoId);
}
