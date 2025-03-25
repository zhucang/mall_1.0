package com.ruoyi.web.controller.api;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SellerAccountApplyRecord;
import com.ruoyi.system.service.ISellerAccountApplyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商户入驻申请记录Controller
 * 
 * @author ruoyi
 * @date 2025-01-03
 */
@RestController
@RequestMapping("/api/sellerAccountApplyRecord")
public class ApiSellerAccountApplyRecordController extends BaseController
{
    @Autowired
    private ISellerAccountApplyRecordService sellerAccountApplyRecordService;

    /**
     * 查询商户入驻申请记录列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SellerAccountApplyRecord sellerAccountApplyRecord)
    {
        startPage();
        List<SellerAccountApplyRecord> list = sellerAccountApplyRecordService.selectSellerAccountApplyRecordList(sellerAccountApplyRecord);
        return getDataTable(list);
    }

    /**
     * 发起商户入驻申请
     */
    @Log(title = "发起商户入驻申请", businessType = BusinessType.INSERT)
    @PostMapping("/InitiateApply")
    public AjaxResult InitiateApply(@RequestBody SellerAccountApplyRecord sellerAccountApplyRecord)
    {
        return toAjax(sellerAccountApplyRecordService.InitiateApply(sellerAccountApplyRecord));
    }
}
