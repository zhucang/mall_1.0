package com.ruoyi.web.controller.api;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Coupon;
import com.ruoyi.system.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 优惠券Controller
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
@RestController
@RequestMapping("/api/coupon")
public class ApiCouponController extends BaseController
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
        coupon.setCouponStatus(0);
        coupon.getParams().put("valid",0);
        List<Coupon> list = couponService.selectCouponList(coupon);
        couponService.fillOtherInfo(list);
        return getDataTable(list);
    }
}
