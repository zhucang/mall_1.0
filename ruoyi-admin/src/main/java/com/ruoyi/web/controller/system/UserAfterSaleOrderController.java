package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.system.domain.UserShoppingOrder;
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
import com.ruoyi.system.domain.UserAfterSaleOrder;
import com.ruoyi.system.service.IUserAfterSaleOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商城售后订单Controller
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
@RestController
@RequestMapping("/system/userAfterSaleOrder")
public class UserAfterSaleOrderController extends BaseController
{
    @Autowired
    private IUserAfterSaleOrderService userAfterSaleOrderService;

    /**
     * 查询商城售后订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:userAfterSaleOrder:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserAfterSaleOrder userAfterSaleOrder)
    {
        startPage();
        List<UserAfterSaleOrder> list = userAfterSaleOrderService.selectUserAfterSaleOrderList(userAfterSaleOrder);
        return getDataTable(list);
    }

    /**
     * 导出商城售后订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:userAfterSaleOrder:export')")
    @Log(title = "商城售后订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserAfterSaleOrder userAfterSaleOrder)
    {
        List<UserAfterSaleOrder> list = userAfterSaleOrderService.selectUserAfterSaleOrderList(userAfterSaleOrder);
        ExcelUtil<UserAfterSaleOrder> util = new ExcelUtil<UserAfterSaleOrder>(UserAfterSaleOrder.class);
        util.exportExcel(response, list, "商城售后订单数据");
    }

    /**
     * 获取商城售后订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:userAfterSaleOrder:query')")
    @GetMapping(value = "/{userAfterSaleOrderId}")
    public AjaxResult getInfo(@PathVariable("userAfterSaleOrderId") Long userAfterSaleOrderId)
    {
        return success(userAfterSaleOrderService.selectUserAfterSaleOrderByUserAfterSaleOrderId(userAfterSaleOrderId));
    }

    /**
     * 新增商城售后订单
     */
    @PreAuthorize("@ss.hasPermi('system:userAfterSaleOrder:add')")
    @Log(title = "商城售后订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserAfterSaleOrder userAfterSaleOrder)
    {
        return toAjax(userAfterSaleOrderService.insertUserAfterSaleOrder(userAfterSaleOrder));
    }

    /**
     * 修改商城售后订单
     */
    @PreAuthorize("@ss.hasPermi('system:userAfterSaleOrder:edit')")
    @Log(title = "商城售后订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserAfterSaleOrder userAfterSaleOrder)
    {
        return toAjax(userAfterSaleOrderService.updateUserAfterSaleOrder(userAfterSaleOrder));
    }

    /**
     * 删除商城售后订单
     */
    @PreAuthorize("@ss.hasPermi('system:userAfterSaleOrder:remove')")
    @Log(title = "商城售后订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userAfterSaleOrderIds}")
    public AjaxResult remove(@PathVariable Long[] userAfterSaleOrderIds)
    {
        return toAjax(userAfterSaleOrderService.deleteUserAfterSaleOrderByUserAfterSaleOrderIds(userAfterSaleOrderIds));
    }

    /**
     * 售后订单审核
     */
    @PreAuthorize("@ss.hasPermi('system:userAfterSaleOrder:refundReview')")
    @Log(title = "售后订单审核", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("/userAfterSaleOrderReview")
    public AjaxResult userAfterSaleOrderReview(@RequestBody UserAfterSaleOrder userAfterSaleOrder)
    {
        return toAjax(userAfterSaleOrderService.userAfterSaleOrderReview(userAfterSaleOrder));
    }
}
