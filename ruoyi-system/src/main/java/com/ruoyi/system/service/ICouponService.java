package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Coupon;

/**
 * 优惠券Service接口
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public interface ICouponService 
{
    /**
     * 查询优惠券
     * 
     * @param couponId 优惠券主键
     * @return 优惠券
     */
    public Coupon selectCouponByCouponId(Long couponId);

    /**
     * 查询优惠券列表
     * 
     * @param coupon 优惠券
     * @return 优惠券集合
     */
    public List<Coupon> selectCouponList(Coupon coupon);

    /**
     * 填充其他信息
     * @param coupons
     */
    public void fillOtherInfo(List<Coupon> coupons);

    /**
     * 新增优惠券
     * 
     * @param coupon 优惠券
     * @return 结果
     */
    public int insertCoupon(Coupon coupon);

    /**
     * 修改优惠券
     * 
     * @param coupon 优惠券
     * @return 结果
     */
    public int updateCoupon(Coupon coupon);

    /**
     * 批量删除优惠券
     * 
     * @param couponIds 需要删除的优惠券主键集合
     * @return 结果
     */
    public int deleteCouponByCouponIds(Long[] couponIds);

    /**
     * 删除优惠券信息
     * 
     * @param couponId 优惠券主键
     * @return 结果
     */
    public int deleteCouponByCouponId(Long couponId);
}
