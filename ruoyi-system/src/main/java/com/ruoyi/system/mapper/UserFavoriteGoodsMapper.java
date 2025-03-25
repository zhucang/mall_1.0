package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.UserFavoriteGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户商品收藏Mapper接口
 * 
 * @author ruoyi
 * @date 2024-12-24
 */
public interface UserFavoriteGoodsMapper 
{
    /**
     * 查询用户商品收藏
     * 
     * @param id 用户商品收藏主键
     * @return 用户商品收藏
     */
    public UserFavoriteGoods selectUserFavoriteGoodsById(Long id);

    /**
     * 查询用户商品收藏列表
     * 
     * @param userFavoriteGoods 用户商品收藏
     * @return 用户商品收藏集合
     */
    public List<UserFavoriteGoods> selectUserFavoriteGoodsList(UserFavoriteGoods userFavoriteGoods);

    /**
     * 新增用户商品收藏
     * 
     * @param userFavoriteGoods 用户商品收藏
     * @return 结果
     */
    public int insertUserFavoriteGoods(UserFavoriteGoods userFavoriteGoods);

    /**
     * 修改用户商品收藏
     * 
     * @param userFavoriteGoods 用户商品收藏
     * @return 结果
     */
    public int updateUserFavoriteGoods(UserFavoriteGoods userFavoriteGoods);

    /**
     * 删除用户商品收藏
     * 
     * @param id 用户商品收藏主键
     * @return 结果
     */
    public int deleteUserFavoriteGoodsById(Long id);

    /**
     * 批量删除用户商品收藏
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserFavoriteGoodsByIds(Long[] ids);

    /**
     * 获取用户是否收藏状态
     *
     * @param userId 用户ID
     * @param sellingGoodsInfoId 在售商品信息ID
     * @return 结果
     */
    public int getUserFavoriteStatus(@Param("userId") Long userId, @Param("sellingGoodsInfoId") Long sellingGoodsInfoId);
}
