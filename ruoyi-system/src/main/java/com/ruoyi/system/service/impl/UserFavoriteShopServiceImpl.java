package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.HintConstants;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.entity.UserInfo;
import com.ruoyi.common.exception.LangException;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.UserFavoriteShop;
import com.ruoyi.system.mapper.UserFavoriteShopMapper;
import com.ruoyi.system.mapper.UserInfoMapper;
import com.ruoyi.system.service.IShopInfoService;
import com.ruoyi.system.service.IUserFavoriteShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户店铺收藏Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-12-24
 */
@Service
public class UserFavoriteShopServiceImpl implements IUserFavoriteShopService 
{
    @Resource
    private UserFavoriteShopMapper userFavoriteShopMapper;

    @Autowired
    private IShopInfoService shopInfoService;

    @Resource
    private UserInfoMapper userInfoMapper;

    /**
     * 查询用户店铺收藏
     * 
     * @param id 用户店铺收藏主键
     * @return 用户店铺收藏
     */
    @Override
    public UserFavoriteShop selectUserFavoriteShopById(Long id)
    {
        return userFavoriteShopMapper.selectUserFavoriteShopById(id);
    }

    /**
     * 查询用户店铺收藏列表
     * 
     * @param userFavoriteShop 用户店铺收藏
     * @return 用户店铺收藏
     */
    @Override
    public List<UserFavoriteShop> selectUserFavoriteShopList(UserFavoriteShop userFavoriteShop)
    {
        return userFavoriteShopMapper.selectUserFavoriteShopList(userFavoriteShop);
    }

    /**
     * 新增用户店铺收藏
     * 
     * @param userFavoriteShop 用户店铺收藏
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertUserFavoriteShop(UserFavoriteShop userFavoriteShop)
    {
        //用户ID
        Long userId = SecurityUtils.getUserId();
        //用户信息
        UserInfo userInfo = userInfoMapper.selectUserInfoById(userId);
        //更新用户商品收藏数量
        userInfo.setUserFavoriteShopNum(userInfo.getUserFavoriteShopNum() + 1);
        int updateUserInfo = userInfoMapper.updateUserInfo(userInfo);
        if (updateUserInfo == 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"更新用户信息异常");
        }
        //店铺粉丝数量+1
        int updateShopFansNum = shopInfoService.updateShopFansNum(userFavoriteShop.getShopInfoId(), 1);
        if (updateShopFansNum <= 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"更新粉丝数量异常");
        }
        userFavoriteShop.setUserId(userId);
        userFavoriteShop.setCreateTime(DateUtils.getNowDate());
        int insertUserFavoriteShop = userFavoriteShopMapper.insertUserFavoriteShop(userFavoriteShop);
        if (insertUserFavoriteShop == 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"新增用户收藏数据异常");
        }
        return 1;
    }

    /**
     * 修改用户店铺收藏
     * 
     * @param userFavoriteShop 用户店铺收藏
     * @return 结果
     */
    @Override
    public int updateUserFavoriteShop(UserFavoriteShop userFavoriteShop)
    {
        return userFavoriteShopMapper.updateUserFavoriteShop(userFavoriteShop);
    }

    /**
     * 批量删除用户店铺收藏
     * 
     * @param ids 需要删除的用户店铺收藏主键
     * @return 结果
     */
    @Override
    public int deleteUserFavoriteShopByIds(Long[] ids)
    {
        return userFavoriteShopMapper.deleteUserFavoriteShopByIds(ids);
    }

    /**
     * 删除用户店铺收藏信息
     * 
     * @param id 用户店铺收藏主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteUserFavoriteShopById(Long id)
    {
        //用户ID
        Long userId = SecurityUtils.getUserId();
        //用户信息
        UserInfo userInfo = userInfoMapper.selectUserInfoById(userId);
        //更新用户商品收藏数量
        userInfo.setUserFavoriteShopNum(userInfo.getUserFavoriteShopNum() - 1);
        int updateUserInfo = userInfoMapper.updateUserInfo(userInfo);
        if (updateUserInfo == 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"更新用户信息异常");
        }
        //用户店铺收藏信息
        UserFavoriteShop userFavoriteShop = userFavoriteShopMapper.selectUserFavoriteShopById(id);
        //检验身份
        if (!userId.equals(userFavoriteShop.getUserId())){
            throw new ServiceException("身份校验异常", HttpStatus.UNAUTHORIZED);
        }
        //店铺粉丝数量-1
        int updateShopFansNum = shopInfoService.updateShopFansNum(userFavoriteShop.getShopInfoId(), -1);
        if (updateShopFansNum <= 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"更新粉丝数量异常");
        }
        return userFavoriteShopMapper.deleteUserFavoriteShopById(id);
    }

    /**
     * 获取用户是否收藏状态
     *
     * @param userId 用户ID
     * @param shopInfoId 店铺信息ID
     * @return 结果
     */
    public int getUserFavoriteStatus(Long userId, Long shopInfoId){
        return userFavoriteShopMapper.getUserFavoriteStatus(userId,shopInfoId);
    }
}
