package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.constant.HintConstants;
import com.ruoyi.common.exception.LangException;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.Coupon;
import com.ruoyi.system.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.UserHoldCouponMapper;
import com.ruoyi.system.domain.UserHoldCoupon;
import com.ruoyi.system.service.IUserHoldCouponService;

import javax.annotation.Resource;

/**
 * 用户持有优惠券Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-28
 */
@Service
public class UserHoldCouponServiceImpl implements IUserHoldCouponService 
{
    @Resource
    private UserHoldCouponMapper userHoldCouponMapper;

    @Autowired
    private ICouponService couponService;

    /**
     * 查询用户持有优惠券
     * 
     * @param userHoldCouponId 用户持有优惠券主键
     * @return 用户持有优惠券
     */
    @Override
    public UserHoldCoupon selectUserHoldCouponByUserHoldCouponId(Long userHoldCouponId)
    {
        return userHoldCouponMapper.selectUserHoldCouponByUserHoldCouponId(userHoldCouponId);
    }

    /**
     * 查询用户持有优惠券列表
     * 
     * @param userHoldCoupon 用户持有优惠券
     * @return 用户持有优惠券
     */
    @Override
    public List<UserHoldCoupon> selectUserHoldCouponList(UserHoldCoupon userHoldCoupon)
    {
        return userHoldCouponMapper.selectUserHoldCouponList(userHoldCoupon);
    }

    /**
     * 新增用户持有优惠券
     * 
     * @param userHoldCoupon 用户持有优惠券
     * @return 结果
     */
    @Override
    public int insertUserHoldCoupon(UserHoldCoupon userHoldCoupon)
    {
        userHoldCoupon.setCreateTime(DateUtils.getNowDate());
        return userHoldCouponMapper.insertUserHoldCoupon(userHoldCoupon);
    }

    /**
     * 修改用户持有优惠券
     * 
     * @param userHoldCoupon 用户持有优惠券
     * @return 结果
     */
    @Override
    public int updateUserHoldCoupon(UserHoldCoupon userHoldCoupon)
    {
        userHoldCoupon.setUpdateTime(DateUtils.getNowDate());
        return userHoldCouponMapper.updateUserHoldCoupon(userHoldCoupon);
    }

    /**
     * 批量删除用户持有优惠券
     * 
     * @param userHoldCouponIds 需要删除的用户持有优惠券主键
     * @return 结果
     */
    @Override
    public int deleteUserHoldCouponByUserHoldCouponIds(Long[] userHoldCouponIds)
    {
        return userHoldCouponMapper.deleteUserHoldCouponByUserHoldCouponIds(userHoldCouponIds);
    }

    /**
     * 删除用户持有优惠券信息
     * 
     * @param userHoldCouponId 用户持有优惠券主键
     * @return 结果
     */
    @Override
    public int deleteUserHoldCouponByUserHoldCouponId(Long userHoldCouponId)
    {
        return userHoldCouponMapper.deleteUserHoldCouponByUserHoldCouponId(userHoldCouponId);
    }

    /**
     * 用户领取优惠券
     * @param userHoldCoupon
     * @return
     */
    @Override
    public int usersReceiveCoupons(UserHoldCoupon userHoldCoupon){
        //用户ID
        Long userId = SecurityUtils.getUserId();
        //优惠券ID
        Long couponId = userHoldCoupon.getCouponId();
        //优惠券信息
        Coupon coupon = couponService.selectCouponByCouponId(couponId);
        if (coupon == null){
            throw new LangException(HintConstants.SYSTEM_BUSY,"获取优惠券 信息异常");
        }
        //优惠券状态
        Integer couponStatus = coupon.getCouponStatus();
        if (couponStatus.equals(1)){
            throw new LangException(HintConstants.SYSTEM_BUSY,"优惠券已被下架");
        }else if (couponStatus.equals(2)){
            throw new LangException(HintConstants.SYSTEM_BUSY,"优惠券已过期");
        }
        userHoldCoupon.setUserId(userId);
        userHoldCoupon.setUseStatus(0);
        userHoldCoupon.setCreateTime(new Date());
        //检验优惠券是否领取过
        UserHoldCoupon userHoldCouponSearch = new UserHoldCoupon();
        userHoldCouponSearch.setCouponId(couponId);
        userHoldCouponSearch.setUserId(userId);
        List<UserHoldCoupon> userHoldCoupons = userHoldCouponMapper.selectUserHoldCouponList(userHoldCouponSearch);
        if (userHoldCoupons.size() > 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"已领取过该优惠券");
        }
        return userHoldCouponMapper.insertUserHoldCoupon(userHoldCoupon);
    }
}
