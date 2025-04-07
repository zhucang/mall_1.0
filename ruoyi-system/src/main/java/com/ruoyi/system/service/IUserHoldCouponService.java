package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.UserHoldCoupon;

/**
 * 用户持有优惠券Service接口
 * 
 * @author ruoyi
 * @date 2025-03-28
 */
public interface IUserHoldCouponService 
{
    /**
     * 查询用户持有优惠券
     * 
     * @param userHoldCouponId 用户持有优惠券主键
     * @return 用户持有优惠券
     */
    public UserHoldCoupon selectUserHoldCouponByUserHoldCouponId(Long userHoldCouponId);

    /**
     * 查询用户持有优惠券列表
     * 
     * @param userHoldCoupon 用户持有优惠券
     * @return 用户持有优惠券集合
     */
    public List<UserHoldCoupon> selectUserHoldCouponList(UserHoldCoupon userHoldCoupon);

    /**
     * 新增用户持有优惠券
     * 
     * @param userHoldCoupon 用户持有优惠券
     * @return 结果
     */
    public int insertUserHoldCoupon(UserHoldCoupon userHoldCoupon);

    /**
     * 修改用户持有优惠券
     * 
     * @param userHoldCoupon 用户持有优惠券
     * @return 结果
     */
    public int updateUserHoldCoupon(UserHoldCoupon userHoldCoupon);

    /**
     * 批量删除用户持有优惠券
     * 
     * @param userHoldCouponIds 需要删除的用户持有优惠券主键集合
     * @return 结果
     */
    public int deleteUserHoldCouponByUserHoldCouponIds(Long[] userHoldCouponIds);

    /**
     * 删除用户持有优惠券信息
     * 
     * @param userHoldCouponId 用户持有优惠券主键
     * @return 结果
     */
    public int deleteUserHoldCouponByUserHoldCouponId(Long userHoldCouponId);

    /**
     * 用户领取优惠券
     * @param userHoldCoupon
     * @return
     */
    public int usersReceiveCoupons(UserHoldCoupon userHoldCoupon);
}
