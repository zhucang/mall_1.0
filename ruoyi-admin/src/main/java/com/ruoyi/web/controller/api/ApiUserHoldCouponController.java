package com.ruoyi.web.controller.api;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.UserHoldCoupon;
import com.ruoyi.system.service.IUserHoldCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户持有优惠券Controller
 * 
 * @author ruoyi
 * @date 2025-03-28
 */
@RestController
@RequestMapping("/api/userHoldCoupon")
public class ApiUserHoldCouponController extends BaseController
{
    @Autowired
    private IUserHoldCouponService userHoldCouponService;

    /**
     * 查询用户持有优惠券列表
     */
    @GetMapping("/list")
    public TableDataInfo list(UserHoldCoupon userHoldCoupon)
    {
        startPage();
        userHoldCoupon.setUserId(SecurityUtils.getUserId());
        userHoldCoupon.getParams().put("valid",0);
        List<UserHoldCoupon> list = userHoldCouponService.selectUserHoldCouponList(userHoldCoupon);
        return getDataTable(list);
    }

    /**
     * 用户领取优惠券
     */
    @Log(title = "用户领取优惠券", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/usersReceiveCoupons")
    public AjaxResult usersReceiveCoupons(@RequestBody UserHoldCoupon userHoldCoupon)
    {
        return toAjax(userHoldCouponService.usersReceiveCoupons(userHoldCoupon));
    }
}
