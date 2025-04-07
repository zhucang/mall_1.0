package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.UserHoldCoupon;
import com.ruoyi.system.service.IUserHoldCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CouponMapper;
import com.ruoyi.system.domain.Coupon;
import com.ruoyi.system.service.ICouponService;

import javax.annotation.Resource;

/**
 * 优惠券Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
@Service
public class CouponServiceImpl implements ICouponService 
{
    @Resource
    private CouponMapper couponMapper;

    @Autowired
    private IUserHoldCouponService userHoldCouponService;

    /**
     * 查询优惠券
     * 
     * @param couponId 优惠券主键
     * @return 优惠券
     */
    @Override
    public Coupon selectCouponByCouponId(Long couponId)
    {
        return couponMapper.selectCouponByCouponId(couponId);
    }

    /**
     * 查询优惠券列表
     * 
     * @param coupon 优惠券
     * @return 优惠券
     */
    @Override
    public List<Coupon> selectCouponList(Coupon coupon)
    {
        return couponMapper.selectCouponList(coupon);
    }

    /**
     * 填充其他信息
     * @param coupons
     */
    @Override
    public void fillOtherInfo(List<Coupon> coupons){
        //填充用户领取状态
        try{
            //用户ID
            Long userId = SecurityUtils.getUserId();
            //优惠券ids
            List<Long> couponIds = coupons.stream().map(a -> a.getCouponId()).collect(Collectors.toList());
            //获取用户领取过的优惠券
            UserHoldCoupon userHoldCouponSearch = new UserHoldCoupon();
            userHoldCouponSearch.setUserId(userId);
            userHoldCouponSearch.getParams().put("couponIds",couponIds);
            List<UserHoldCoupon> userHoldCoupons = userHoldCouponService.selectUserHoldCouponList(userHoldCouponSearch);
            //用户领取过的优惠券Map
            Map<Long, UserHoldCoupon> map = userHoldCoupons.stream().collect(Collectors.toMap(a -> a.getCouponId(), a -> a));
            for (int i = 0; i < coupons.size(); i++) {
                //优惠券信息
                Coupon coupon = coupons.get(i);
                //优惠券ID
                Long couponId = coupon.getCouponId();
                //如果已领取过
                if (map.get(couponId) != null){
                    coupon.getParams().put("receiveStatus",1);
                }else {
                    coupon.getParams().put("receiveStatus",0);
                }
            }
        }catch (Exception e){
            //如果还未登录，全部展示未领取
            coupons.stream().forEach(a->a.getParams().put("receiveStatus",0));
        }
    }

    /**
     * 新增优惠券
     * 
     * @param coupon 优惠券
     * @return 结果
     */
    @Override
    public int insertCoupon(Coupon coupon)
    {
        coupon.setCreateTime(DateUtils.getNowDate());
        return couponMapper.insertCoupon(coupon);
    }

    /**
     * 修改优惠券
     * 
     * @param coupon 优惠券
     * @return 结果
     */
    @Override
    public int updateCoupon(Coupon coupon)
    {
        coupon.setUpdateTime(DateUtils.getNowDate());
        return couponMapper.updateCoupon(coupon);
    }

    /**
     * 批量删除优惠券
     * 
     * @param couponIds 需要删除的优惠券主键
     * @return 结果
     */
    @Override
    public int deleteCouponByCouponIds(Long[] couponIds)
    {
        return couponMapper.deleteCouponByCouponIds(couponIds);
    }

    /**
     * 删除优惠券信息
     * 
     * @param couponId 优惠券主键
     * @return 结果
     */
    @Override
    public int deleteCouponByCouponId(Long couponId)
    {
        return couponMapper.deleteCouponByCouponId(couponId);
    }
}
