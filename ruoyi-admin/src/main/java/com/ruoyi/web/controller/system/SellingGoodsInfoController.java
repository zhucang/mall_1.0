package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SellingGoodsInfo;
import com.ruoyi.system.service.ISellingGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 在售商品信息Controller
 * 
 * @author ruoyi
 * @date 2024-12-20
 */
@RestController
@RequestMapping("/system/sellingGoodsInfo")
public class SellingGoodsInfoController extends BaseController
{
    @Autowired
    private ISellingGoodsInfoService sellingGoodsInfoService;

    /**
     * 查询在售商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:sellingGoodsInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(SellingGoodsInfo sellingGoodsInfo)
    {
        startPage();
        List<SellingGoodsInfo> list = sellingGoodsInfoService.selectSellingGoodsInfoList(sellingGoodsInfo);
        return getDataTable(list);
    }

    /**
     * 导出在售商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:sellingGoodsInfo:export')")
    @Log(title = "在售商品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SellingGoodsInfo sellingGoodsInfo)
    {
        List<SellingGoodsInfo> list = sellingGoodsInfoService.selectSellingGoodsInfoList(sellingGoodsInfo);
        ExcelUtil<SellingGoodsInfo> util = new ExcelUtil<SellingGoodsInfo>(SellingGoodsInfo.class);
        util.exportExcel(response, list, "在售商品信息数据");
    }

    /**
     * 获取在售商品信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:sellingGoodsInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sellingGoodsInfoService.selectSellingGoodsInfoById(id));
    }

    /**
     * 新增在售商品信息
     */
    @PreAuthorize("@ss.hasPermi('system:sellingGoodsInfo:add')")
    @Log(title = "在售商品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SellingGoodsInfo sellingGoodsInfo)
    {
        return toAjax(sellingGoodsInfoService.insertSellingGoodsInfo(sellingGoodsInfo));
    }

    /**
     * 修改在售商品信息
     */
    @PreAuthorize("@ss.hasPermi('system:sellingGoodsInfo:edit')")
    @Log(title = "在售商品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SellingGoodsInfo sellingGoodsInfo)
    {
        return toAjax(sellingGoodsInfoService.updateSellingGoodsInfo(sellingGoodsInfo));
    }

    /**
     * 删除在售商品信息
     */
    @PreAuthorize("@ss.hasPermi('system:sellingGoodsInfo:remove')")
    @Log(title = "在售商品信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sellingGoodsInfoService.deleteSellingGoodsInfoByIds(ids));
    }
}
