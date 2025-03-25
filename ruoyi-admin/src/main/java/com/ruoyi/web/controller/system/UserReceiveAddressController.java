package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.UserReceiveAddress;
import com.ruoyi.system.service.IUserReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户收货地址Controller
 * 
 * @author ruoyi
 * @date 2024-12-27
 */
@RestController
@RequestMapping("/system/userReceiveAddress")
public class UserReceiveAddressController extends BaseController
{
    @Autowired
    private IUserReceiveAddressService userReceiveAddressService;

    /**
     * 查询用户收货地址列表
     */
    @PreAuthorize("@ss.hasPermi('system:userReceiveAddress:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserReceiveAddress userReceiveAddress)
    {
        startPage();
        List<UserReceiveAddress> list = userReceiveAddressService.selectUserReceiveAddressList(userReceiveAddress);
        return getDataTable(list);
    }

    /**
     * 导出用户收货地址列表
     */
    @PreAuthorize("@ss.hasPermi('system:userReceiveAddress:export')")
    @Log(title = "用户收货地址", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserReceiveAddress userReceiveAddress)
    {
        List<UserReceiveAddress> list = userReceiveAddressService.selectUserReceiveAddressList(userReceiveAddress);
        ExcelUtil<UserReceiveAddress> util = new ExcelUtil<UserReceiveAddress>(UserReceiveAddress.class);
        util.exportExcel(response, list, "用户收货地址数据");
    }

    /**
     * 获取用户收货地址详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:userReceiveAddress:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(userReceiveAddressService.selectUserReceiveAddressById(id));
    }

    /**
     * 新增用户收货地址
     */
    @PreAuthorize("@ss.hasPermi('system:userReceiveAddress:add')")
    @Log(title = "用户收货地址", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserReceiveAddress userReceiveAddress)
    {
        return toAjax(userReceiveAddressService.insertUserReceiveAddress(userReceiveAddress));
    }

    /**
     * 修改用户收货地址
     */
    @PreAuthorize("@ss.hasPermi('system:userReceiveAddress:edit')")
    @Log(title = "用户收货地址", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserReceiveAddress userReceiveAddress)
    {
        return toAjax(userReceiveAddressService.updateUserReceiveAddress(userReceiveAddress));
    }

    /**
     * 删除用户收货地址
     */
    @PreAuthorize("@ss.hasPermi('system:userReceiveAddress:remove')")
    @Log(title = "用户收货地址", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userReceiveAddressService.deleteUserReceiveAddressByIds(ids));
    }
}
