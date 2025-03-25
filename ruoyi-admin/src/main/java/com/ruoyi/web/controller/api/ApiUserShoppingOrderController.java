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
import com.ruoyi.system.domain.UserShoppingOrder;
import com.ruoyi.system.service.IUserShoppingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户购物订单Controller
 * 
 * @author ruoyi
 * @date 2024-12-24
 */
@RestController
@RequestMapping("/api/userShoppingOrder")
public class ApiUserShoppingOrderController extends BaseController
{
    @Autowired
    private IUserShoppingOrderService userShoppingOrderService;

    /**
     * 查询用户购物订单列表
     */
    @GetMapping("/list")
    public TableDataInfo list(UserShoppingOrder userShoppingOrder)
    {
        if (userShoppingOrder.getParams().get("sellerFlag") != null){
            userShoppingOrder.setSellerId(SecurityUtils.getUserId());
        }else {
            userShoppingOrder.setUserId(SecurityUtils.getUserId());
        }
        startPage();
        startOrderBy("id desc");
        List<UserShoppingOrder> list = userShoppingOrderService.selectUserShoppingOrderList(userShoppingOrder);
        userShoppingOrderService.fillOtherInfo(list);
        return getDataTable(list);
    }

    /**
     * 用户提交商城订单
     */
    @Log(title = "用户提交商城订单", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/userAddShoppingOrders")
    public AjaxResult userAddShoppingOrders(@RequestBody List<UserShoppingOrder> userShoppingOrders)
    {
        return toAjax(userShoppingOrderService.userAddShoppingOrders(userShoppingOrders)).put("userShoppingOrders",userShoppingOrders);
    }

    /**
     * 用户支付商城订单
     */
    @Log(title = "用户支付商城订单", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("/userPayShoppingOrders")
    public AjaxResult userPayShoppingOrders(@RequestBody List<UserShoppingOrder> userShoppingOrders)
    {
        if (userShoppingOrders.stream().filter(a->a.getId() == null).count() > 0){
            throw new LangException(HintConstants.PARAM_NULL,"请选择需要支付的订单");
        }
        return toAjax(userShoppingOrderService.userPayShoppingOrders(userShoppingOrders));
    }

    /**
     * 用户取消商订单
     */
    @Log(title = "用户取消商订单", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("/userCancelShoppingOrders")
    public AjaxResult userCancelShoppingOrders(@RequestBody UserShoppingOrder userShoppingOrder)
    {
        if (userShoppingOrder.getId() == null){
            throw new LangException(HintConstants.PARAM_NULL,"请选择需要操作的订单");
        }
        return toAjax(userShoppingOrderService.userCancelShoppingOrders(userShoppingOrder));
    }

    /**
     * 用户确认收货
     */
    @Log(title = "用户确认收货", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("/userConfirmReceipt")
    public AjaxResult userConfirmReceipt(@RequestBody UserShoppingOrder userShoppingOrder)
    {
        if (userShoppingOrder.getId() == null){
            throw new LangException(HintConstants.PARAM_NULL,"请选择需要确认收货的订单");
        }
        return toAjax(userShoppingOrderService.userConfirmReceipt(userShoppingOrder));
    }

    /**
     * 商户提交订单到平台
     */
    @Log(title = "商户提交订单到平台", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("/sellerSubmitOrderToPlatform")
    public AjaxResult sellerSubmitOrderToPlatform(@RequestBody List<UserShoppingOrder> userShoppingOrders)
    {
        return toAjax(userShoppingOrderService.sellerSubmitOrderToPlatform(userShoppingOrders));
    }

    /**
     * 用户申请退款
     */
    @Log(title = "用户申请退款", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("/userApplyRefund")
    public AjaxResult userApplyRefund(@RequestBody UserShoppingOrder userShoppingOrder)
    {
        if (userShoppingOrder.getId() == null){
            throw new LangException(HintConstants.PARAM_NULL,"请选择需要退款的订单");
        }
        return toAjax(userShoppingOrderService.userApplyRefund(userShoppingOrder));
    }


}
