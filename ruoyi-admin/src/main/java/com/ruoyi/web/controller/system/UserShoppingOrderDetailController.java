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
import com.ruoyi.system.domain.UserShoppingOrderDetail;
import com.ruoyi.system.service.IUserShoppingOrderDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户购物订单明细Controller
 * 
 * @author ruoyi
 * @date 2024-12-24
 */
@RestController
@RequestMapping("/system/userShoppingOrderDetail")
public class UserShoppingOrderDetailController extends BaseController
{
    @Autowired
    private IUserShoppingOrderDetailService userShoppingOrderDetailService;

    /**
     * 查询用户购物订单明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:userShoppingOrderDetail:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserShoppingOrderDetail userShoppingOrderDetail)
    {
        startPage();
        List<UserShoppingOrderDetail> list = userShoppingOrderDetailService.selectUserShoppingOrderDetailList(userShoppingOrderDetail);
        return getDataTable(list);
    }

    /**
     * 导出用户购物订单明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:userShoppingOrderDetail:export')")
    @Log(title = "用户购物订单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserShoppingOrderDetail userShoppingOrderDetail)
    {
        List<UserShoppingOrderDetail> list = userShoppingOrderDetailService.selectUserShoppingOrderDetailList(userShoppingOrderDetail);
        ExcelUtil<UserShoppingOrderDetail> util = new ExcelUtil<UserShoppingOrderDetail>(UserShoppingOrderDetail.class);
        util.exportExcel(response, list, "用户购物订单明细数据");
    }

    /**
     * 获取用户购物订单明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:userShoppingOrderDetail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(userShoppingOrderDetailService.selectUserShoppingOrderDetailById(id));
    }

    /**
     * 新增用户购物订单明细
     */
    @PreAuthorize("@ss.hasPermi('system:userShoppingOrderDetail:add')")
    @Log(title = "用户购物订单明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserShoppingOrderDetail userShoppingOrderDetail)
    {
        return toAjax(userShoppingOrderDetailService.insertUserShoppingOrderDetail(userShoppingOrderDetail));
    }

    /**
     * 修改用户购物订单明细
     */
    @PreAuthorize("@ss.hasPermi('system:userShoppingOrderDetail:edit')")
    @Log(title = "用户购物订单明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserShoppingOrderDetail userShoppingOrderDetail)
    {
        return toAjax(userShoppingOrderDetailService.updateUserShoppingOrderDetail(userShoppingOrderDetail));
    }

    /**
     * 删除用户购物订单明细
     */
    @PreAuthorize("@ss.hasPermi('system:userShoppingOrderDetail:remove')")
    @Log(title = "用户购物订单明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userShoppingOrderDetailService.deleteUserShoppingOrderDetailByIds(ids));
    }
}
