package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Coupon;

/**
 * 优惠券Mapper接口
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public interface CouponMapper 
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
     * 删除优惠券
     * 
     * @param couponId 优惠券主键
     * @return 结果
     */
    public int deleteCouponByCouponId(Long couponId);

    /**
     * 批量删除优惠券
     * 
     * @param couponIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCouponByCouponIds(Long[] couponIds);
}
