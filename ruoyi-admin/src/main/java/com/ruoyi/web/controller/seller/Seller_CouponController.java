package com.ruoyi.web.controller.seller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Coupon;
import com.ruoyi.system.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 优惠券Controller
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
@RestController
@RequestMapping("/seller/coupon")
public class Seller_CouponController extends BaseController
{
    @Autowired
    private ICouponService couponService;

    /**
     * 查询优惠券列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Coupon coupon)
    {
        startPage();
        coupon.setSellerId(SecurityUtils.getUserId());
        List<Coupon> list = couponService.selectCouponList(coupon);
        return getDataTable(list);
    }

    /**
     * 获取优惠券详细信息
     */
    @GetMapping(value = "/{couponId}")
    public AjaxResult getInfo(@PathVariable("couponId") Long couponId)
    {
        return success(couponService.selectCouponByCouponId(couponId));
    }

    /**
     * 新增优惠券
     */
    @Log(title = "优惠券", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Coupon coupon)
    {
        //优惠券金额
        BigDecimal couponAmount = coupon.getCouponAmount();
        if (couponAmount == null){
            throw new ServiceException("请输入优惠券金额");
        }
        if (couponAmount.compareTo(BigDecimal.ZERO) <= 0){
            throw new ServiceException("门槛金额必须");
        }
        //门槛基恩
        BigDecimal requireAmount = coupon.getRequireAmount();
        if (requireAmount == null){
            throw new ServiceException("请输入门槛金额");
        }
        if (requireAmount.compareTo(couponAmount) <= 0){
            throw new ServiceException("门槛金额必须大于优惠券金额");
        }
        //有效期开始时间
        Date validityStartTime = coupon.getValidityStartTime();
        if (validityStartTime == null){
            throw new ServiceException("请输入有效期开始时间");
        }
        //有效期结束时间
        Date validityEndTime = coupon.getValidityEndTime();
        if (validityEndTime == null){
            throw new ServiceException("请输入有效期结束时间");
        }
        if (validityStartTime.after(validityEndTime)){
            throw new ServiceException("请正确设置有效期时间");
        }
        coupon.setSellerId(SecurityUtils.getUserId());
        return toAjax(couponService.insertCoupon(coupon));
    }

    /**
     * 删除优惠券
     */
    @Log(title = "优惠券", businessType = BusinessType.DELETE)
	@DeleteMapping("/{couponIds}")
    public AjaxResult remove(@PathVariable Long[] couponIds)
    {
        return toAjax(couponService.deleteCouponByCouponIds(couponIds));
    }
}
