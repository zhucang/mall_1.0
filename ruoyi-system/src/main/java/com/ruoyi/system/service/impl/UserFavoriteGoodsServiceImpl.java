package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.HintConstants;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.entity.UserInfo;
import com.ruoyi.common.exception.LangException;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.UserFavoriteGoods;
import com.ruoyi.system.mapper.UserFavoriteGoodsMapper;
import com.ruoyi.system.mapper.UserInfoMapper;
import com.ruoyi.system.service.IUserFavoriteGoodsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户商品收藏Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-12-24
 */
@Service
public class UserFavoriteGoodsServiceImpl implements IUserFavoriteGoodsService 
{
    @Resource
    private UserFavoriteGoodsMapper userFavoriteGoodsMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    /**
     * 查询用户商品收藏
     * 
     * @param id 用户商品收藏主键
     * @return 用户商品收藏
     */
    @Override
    public UserFavoriteGoods selectUserFavoriteGoodsById(Long id)
    {
        return userFavoriteGoodsMapper.selectUserFavoriteGoodsById(id);
    }

    /**
     * 查询用户商品收藏列表
     * 
     * @param userFavoriteGoods 用户商品收藏
     * @return 用户商品收藏
     */
    @Override
    public List<UserFavoriteGoods> selectUserFavoriteGoodsList(UserFavoriteGoods userFavoriteGoods)
    {
        return userFavoriteGoodsMapper.selectUserFavoriteGoodsList(userFavoriteGoods);
    }

    /**
     * 新增用户商品收藏
     * 
     * @param userFavoriteGoods 用户商品收藏
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertUserFavoriteGoods(UserFavoriteGoods userFavoriteGoods)
    {
        //用户ID
        Long userId = SecurityUtils.getUserId();
        //用户信息
        UserInfo userInfo = userInfoMapper.selectUserInfoById(userId);
        //更新用户商品收藏数量
        userInfo.setUserFavoriteGoodsNum(userInfo.getUserFavoriteGoodsNum() + 1);
        int updateUserInfo = userInfoMapper.updateUserInfo(userInfo);
        if (updateUserInfo == 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"更新用户信息异常");
        }
        userFavoriteGoods.setUserId(userId);
        userFavoriteGoods.setCreateTime(DateUtils.getNowDate());
        int insertUserFavoriteGoods = userFavoriteGoodsMapper.insertUserFavoriteGoods(userFavoriteGoods);
        if (insertUserFavoriteGoods == 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"新增用户收藏数据异常");
        }
        return 1;
    }

    /**
     * 修改用户商品收藏
     * 
     * @param userFavoriteGoods 用户商品收藏
     * @return 结果
     */
    @Override
    public int updateUserFavoriteGoods(UserFavoriteGoods userFavoriteGoods)
    {
        return userFavoriteGoodsMapper.updateUserFavoriteGoods(userFavoriteGoods);
    }

    /**
     * 批量删除用户商品收藏
     * 
     * @param ids 需要删除的用户商品收藏主键
     * @return 结果
     */
    @Override
    public int deleteUserFavoriteGoodsByIds(Long[] ids)
    {
        return userFavoriteGoodsMapper.deleteUserFavoriteGoodsByIds(ids);
    }

    /**
     * 删除用户商品收藏信息
     * 
     * @param id 用户商品收藏主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteUserFavoriteGoodsById(Long id)
    {
        //用户ID
        Long userId = SecurityUtils.getUserId();
        //用户信息
        UserInfo userInfo = userInfoMapper.selectUserInfoById(userId);
        //更新用户商品收藏数量
        userInfo.setUserFavoriteGoodsNum(userInfo.getUserFavoriteGoodsNum() - 1);
        int updateUserInfo = userInfoMapper.updateUserInfo(userInfo);
        if (updateUserInfo == 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"更新用户信息异常");
        }
        //用户商品收藏信息
        UserFavoriteGoods userFavoriteGoods = userFavoriteGoodsMapper.selectUserFavoriteGoodsById(id);
        //检验身份
        if (!userId.equals(userFavoriteGoods.getUserId())){
            throw new ServiceException("身份校验异常", HttpStatus.UNAUTHORIZED);
        }
        int deleteUserFavoriteGoodsById = userFavoriteGoodsMapper.deleteUserFavoriteGoodsById(id);
        if (deleteUserFavoriteGoodsById == 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"删除用户收藏数据异常");
        }
        return 1;
    }

    /**
     * 获取用户是否收藏状态
     *
     * @param userId 用户ID
     * @param sellingGoodsInfoId 在售商品信息ID
     * @return 结果
     */
    public int getUserFavoriteStatus(Long userId, Long sellingGoodsInfoId){
        return userFavoriteGoodsMapper.getUserFavoriteStatus(userId,sellingGoodsInfoId);
    }
}
