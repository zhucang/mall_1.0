package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.UserHoldCoupon;
import com.ruoyi.system.service.IUserHoldCouponService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户持有优惠券Controller
 * 
 * @author ruoyi
 * @date 2025-03-28
 */
@RestController
@RequestMapping("/system/userHoldCoupon")
public class UserHoldCouponController extends BaseController
{
    @Autowired
    private IUserHoldCouponService userHoldCouponService;

    /**
     * 查询用户持有优惠券列表
     */
    @PreAuthorize("@ss.hasPermi('system:userHoldCoupon:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserHoldCoupon userHoldCoupon)
    {
        startPage();
        List<UserHoldCoupon> list = userHoldCouponService.selectUserHoldCouponList(userHoldCoupon);
        return getDataTable(list);
    }

    /**
     * 导出用户持有优惠券列表
     */
    @PreAuthorize("@ss.hasPermi('system:userHoldCoupon:export')")
    @Log(title = "用户持有优惠券", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserHoldCoupon userHoldCoupon)
    {
        List<UserHoldCoupon> list = userHoldCouponService.selectUserHoldCouponList(userHoldCoupon);
        ExcelUtil<UserHoldCoupon> util = new ExcelUtil<UserHoldCoupon>(UserHoldCoupon.class);
        util.exportExcel(response, list, "用户持有优惠券数据");
    }

    /**
     * 获取用户持有优惠券详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:userHoldCoupon:query')")
    @GetMapping(value = "/{userHoldCouponId}")
    public AjaxResult getInfo(@PathVariable("userHoldCouponId") Long userHoldCouponId)
    {
        return success(userHoldCouponService.selectUserHoldCouponByUserHoldCouponId(userHoldCouponId));
    }

    /**
     * 新增用户持有优惠券
     */
    @PreAuthorize("@ss.hasPermi('system:userHoldCoupon:add')")
    @Log(title = "用户持有优惠券", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserHoldCoupon userHoldCoupon)
    {
        return toAjax(userHoldCouponService.insertUserHoldCoupon(userHoldCoupon));
    }

    /**
     * 修改用户持有优惠券
     */
    @PreAuthorize("@ss.hasPermi('system:userHoldCoupon:edit')")
    @Log(title = "用户持有优惠券", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserHoldCoupon userHoldCoupon)
    {
        return toAjax(userHoldCouponService.updateUserHoldCoupon(userHoldCoupon));
    }

    /**
     * 删除用户持有优惠券
     */
    @PreAuthorize("@ss.hasPermi('system:userHoldCoupon:remove')")
    @Log(title = "用户持有优惠券", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userHoldCouponIds}")
    public AjaxResult remove(@PathVariable Long[] userHoldCouponIds)
    {
        return toAjax(userHoldCouponService.deleteUserHoldCouponByUserHoldCouponIds(userHoldCouponIds));
    }
}
