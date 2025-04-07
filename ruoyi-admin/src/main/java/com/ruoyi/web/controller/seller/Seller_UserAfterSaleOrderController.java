package com.ruoyi.web.controller.seller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.UserAfterSaleOrder;
import com.ruoyi.system.service.IUserAfterSaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商城售后订单Controller
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
@RestController
@RequestMapping("/seller/userAfterSaleOrder")
public class Seller_UserAfterSaleOrderController extends BaseController
{
    @Autowired
    private IUserAfterSaleOrderService userAfterSaleOrderService;

    /**
     * 查询商城售后订单列表
     */
    @GetMapping("/list")
    public TableDataInfo list(UserAfterSaleOrder userAfterSaleOrder)
    {
        startPage();
        userAfterSaleOrder.setSellerId(SecurityUtils.getUserId());
        List<UserAfterSaleOrder> list = userAfterSaleOrderService.selectUserAfterSaleOrderList(userAfterSaleOrder);
        return getDataTable(list);
    }
}
