package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.UserShoppingOrder;
import com.ruoyi.system.service.IUserShoppingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户购物订单Controller
 * 
 * @author ruoyi
 * @date 2024-12-24
 */
@RestController
@RequestMapping("/system/userShoppingOrder")
public class UserShoppingOrderController extends BaseController
{
    @Autowired
    private IUserShoppingOrderService userShoppingOrderService;

    /**
     * 查询用户购物订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:userShoppingOrder:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserShoppingOrder userShoppingOrder)
    {
        startPage();
        startOrderBy("id desc");
        List<UserShoppingOrder> list = userShoppingOrderService.selectUserShoppingOrderList(userShoppingOrder);
        return getDataTable(list);
    }

    /**
     * 导出用户购物订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:userShoppingOrder:export')")
    @Log(title = "用户购物订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserShoppingOrder userShoppingOrder)
    {
        List<UserShoppingOrder> list = userShoppingOrderService.selectUserShoppingOrderList(userShoppingOrder);
        ExcelUtil<UserShoppingOrder> util = new ExcelUtil<UserShoppingOrder>(UserShoppingOrder.class);
        util.exportExcel(response, list, "用户购物订单数据");
    }

    /**
     * 获取用户购物订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:userShoppingOrder:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(userShoppingOrderService.selectUserShoppingOrderById(id));
    }

    /**
     * 新增用户购物订单
     */
    @PreAuthorize("@ss.hasPermi('system:userShoppingOrder:add')")
    @Log(title = "用户购物订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserShoppingOrder userShoppingOrder)
    {
        return toAjax(userShoppingOrderService.insertUserShoppingOrder(userShoppingOrder));
    }

    /**
     * 修改用户购物订单
     */
    @PreAuthorize("@ss.hasPermi('system:userShoppingOrder:edit')")
    @Log(title = "用户购物订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserShoppingOrder userShoppingOrder)
    {
        return toAjax(userShoppingOrderService.updateUserShoppingOrder(userShoppingOrder));
    }

    /**
     * 删除用户购物订单
     */
    @PreAuthorize("@ss.hasPermi('system:userShoppingOrder:remove')")
    @Log(title = "用户购物订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userShoppingOrderService.deleteUserShoppingOrderByIds(ids));
    }


    /**
     * 购物订单平台发货
     */
    @PreAuthorize("@ss.hasPermi('system:userShoppingOrder:shipUserShoppingOrder')")
    @Log(title = "购物订单平台发货", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @GetMapping("/shipUserShoppingOrder")
    public AjaxResult shipUserShoppingOrder(UserShoppingOrder userShoppingOrder)
    {
        return toAjax(userShoppingOrderService.shipUserShoppingOrder(userShoppingOrder));
    }

    /**
     * 退款审核
     */
    @PreAuthorize("@ss.hasPermi('system:userShoppingOrder:refundReview')")
    @Log(title = "退款审核", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("/refundReview")
    public AjaxResult refundReview(@RequestBody UserShoppingOrder userShoppingOrder)
    {
        if (userShoppingOrder.getId() == null){
            throw new ServiceException("请选择需要审核的订单");
        }
        return toAjax(userShoppingOrderService.refundReview(userShoppingOrder));
    }
}
