package com.ruoyi.web.controller.api;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.HintConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.LangException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.UserAfterSaleOrder;
import com.ruoyi.system.service.IUserAfterSaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 商城售后订单Controller
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
@RestController
@RequestMapping("/api/userAfterSaleOrder")
public class ApiUserAfterSaleOrderController extends BaseController
{
    @Autowired
    private IUserAfterSaleOrderService userAfterSaleOrderService;

    /**
     * 用户申请售后
     */
    @Log(title = "用户申请售后", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("/userApplyAfterSale")
    public AjaxResult userApplyAfterSale(@RequestBody UserAfterSaleOrder userAfterSaleOrder)
    {
        if (userAfterSaleOrder.getUserShoppingOrderDetailId() == null){
            throw new LangException(HintConstants.PARAM_NULL,"请选择需要退款的订单");
        }
        if (userAfterSaleOrder.getAfterSaleType() == null){
            throw new LangException(HintConstants.PARAM_NULL,"请选择售后种类");
        }
        if (!userAfterSaleOrder.getAfterSaleType().equals(1)){
            //        if (StringUtils.isEmpty(userAfterSaleOrder.getAfterSaleReason())){
//            throw new LangException(HintConstants.PARAM_NULL,"请输入售后原因");
//        }
            if (StringUtils.isEmpty(userAfterSaleOrder.getUserInstructions())){
                throw new LangException(HintConstants.PARAM_NULL,"请输入售后说明");
            }
        }
        return toAjax(userAfterSaleOrderService.userApplyAfterSale(userAfterSaleOrder));
    }

    /**
     * 用户申请售后
     */
    @Log(title = "用户申请售后", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("/userApplyRefund")
    public AjaxResult userApplyRefund(@RequestBody UserAfterSaleOrder userAfterSaleOrder)
    {
        if (userAfterSaleOrder.getUserShoppingOrderId() == null){
            throw new LangException(HintConstants.PARAM_NULL,"请选择需要退款的订单");
        }
        userAfterSaleOrder.setUserShoppingOrderDetailId(userAfterSaleOrder.getUserShoppingOrderId());
        if (userAfterSaleOrder.getAfterSaleType() == null){
            throw new LangException(HintConstants.PARAM_NULL,"请选择售后种类");
        }
        if (!userAfterSaleOrder.getAfterSaleType().equals(1)){
            //        if (StringUtils.isEmpty(userAfterSaleOrder.getAfterSaleReason())){
//            throw new LangException(HintConstants.PARAM_NULL,"请输入售后原因");
//        }
            if (StringUtils.isEmpty(userAfterSaleOrder.getUserInstructions())){
                throw new LangException(HintConstants.PARAM_NULL,"请输入售后说明");
            }
        }
        return toAjax(userAfterSaleOrderService.userApplyAfterSale(userAfterSaleOrder));
    }
}
