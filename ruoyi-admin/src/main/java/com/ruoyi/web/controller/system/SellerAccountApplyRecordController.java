package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SellerAccountApplyRecord;
import com.ruoyi.system.service.ISellerAccountApplyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商户入驻申请记录Controller
 * 
 * @author ruoyi
 * @date 2025-01-03
 */
@RestController
@RequestMapping("/system/sellerAccountApplyRecord")
public class SellerAccountApplyRecordController extends BaseController
{
    @Autowired
    private ISellerAccountApplyRecordService sellerAccountApplyRecordService;

    /**
     * 查询商户入驻申请记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:sellerAccountApplyRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(SellerAccountApplyRecord sellerAccountApplyRecord)
    {
        startPage();
        startOrderBy("id desc");
        List<SellerAccountApplyRecord> list = sellerAccountApplyRecordService.selectSellerAccountApplyRecordList(sellerAccountApplyRecord);
        return getDataTable(list);
    }

    /**
     * 导出商户入驻申请记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:sellerAccountApplyRecord:export')")
    @Log(title = "商户入驻申请记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SellerAccountApplyRecord sellerAccountApplyRecord)
    {
        List<SellerAccountApplyRecord> list = sellerAccountApplyRecordService.selectSellerAccountApplyRecordList(sellerAccountApplyRecord);
        ExcelUtil<SellerAccountApplyRecord> util = new ExcelUtil<SellerAccountApplyRecord>(SellerAccountApplyRecord.class);
        util.exportExcel(response, list, "商户入驻申请记录数据");
    }

    /**
     * 获取商户入驻申请记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:sellerAccountApplyRecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sellerAccountApplyRecordService.selectSellerAccountApplyRecordById(id));
    }

//    /**
//     * 新增商户入驻申请记录
//     */
//    @PreAuthorize("@ss.hasPermi('system:sellerAccountApplyRecord:add')")
//    @Log(title = "商户入驻申请记录", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody SellerAccountApplyRecord sellerAccountApplyRecord)
//    {
//        return toAjax(sellerAccountApplyRecordService.insertSellerAccountApplyRecord(sellerAccountApplyRecord));
//    }
//
//    /**
//     * 修改商户入驻申请记录
//     */
//    @PreAuthorize("@ss.hasPermi('system:sellerAccountApplyRecord:edit')")
//    @Log(title = "商户入驻申请记录", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody SellerAccountApplyRecord sellerAccountApplyRecord)
//    {
//        return toAjax(sellerAccountApplyRecordService.updateSellerAccountApplyRecord(sellerAccountApplyRecord));
//    }
//
//    /**
//     * 删除商户入驻申请记录
//     */
//    @PreAuthorize("@ss.hasPermi('system:sellerAccountApplyRecord:remove')")
//    @Log(title = "商户入驻申请记录", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Long[] ids)
//    {
//        return toAjax(sellerAccountApplyRecordService.deleteSellerAccountApplyRecordByIds(ids));
//    }

    /**
     * 商户入驻申请审核
     */
    @PreAuthorize("@ss.hasPermi('system:sellerAccountApplyRecord:reviewSellerAccountApply')")
    @PostMapping(value = "/reviewSellerAccountApply")
    public AjaxResult reviewSellerAccountApply(@RequestBody SellerAccountApplyRecord sellerAccountApplyRecord)
    {
        return success(sellerAccountApplyRecordService.reviewSellerAccountApply(sellerAccountApplyRecord));
    }

}
