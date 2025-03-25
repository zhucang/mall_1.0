package com.ruoyi.web.controller.api;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.HintConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.LangException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.UserReceiveAddress;
import com.ruoyi.system.service.IUserReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户收货地址Controller
 * 
 * @author ruoyi
 * @date 2024-12-27
 */
@RestController
@RequestMapping("/api/userReceiveAddress")
public class ApiUserReceiveAddressController extends BaseController
{
    @Autowired
    private IUserReceiveAddressService userReceiveAddressService;

    /**
     * 查询用户收货地址列表
     */
    @GetMapping("/list")
    public TableDataInfo list(UserReceiveAddress userReceiveAddress)
    {
        userReceiveAddress.setUserId(SecurityUtils.getUserId());
        startPage();
        startOrderBy("default_flag desc, id desc");
        List<UserReceiveAddress> list = userReceiveAddressService.selectUserReceiveAddressList(userReceiveAddress);
        return getDataTable(list);
    }

    /**
     * 用户添加收货地址
     */
    @Log(title = "用户添加收货地址", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult add(@RequestBody UserReceiveAddress userReceiveAddress)
    {
        userReceiveAddress.setUserId(SecurityUtils.getUserId());
        return toAjax(userReceiveAddressService.userAddReceiveAddress(userReceiveAddress));
    }

    /**
     * 用户编辑收货地址
     */
    @Log(title = "用户编辑收货地址", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody UserReceiveAddress userReceiveAddress)
    {
        if (userReceiveAddress.getId() == null){
            throw new LangException(HintConstants.PARAM_NULL,"请选择需要编辑的选项");
        }
        return toAjax(userReceiveAddressService.userEditReceiveAddress(userReceiveAddress));
    }

    /**
     * 用户删除收货地址
     */
    @Log(title = "用户收货地址", businessType = BusinessType.DELETE)
    @RepeatSubmit
    @PostMapping("/remove")
    public AjaxResult remove(@RequestBody List<Long> ids)
    {
        return toAjax(userReceiveAddressService.userRemoveReceiveAddress(ids));
    }
}
