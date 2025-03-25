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
import com.ruoyi.system.domain.Sku;
import com.ruoyi.system.service.ISkuService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品skuController
 * 
 * @author ruoyi
 * @date 2024-12-24
 */
@RestController
@RequestMapping("/system/sku")
public class SkuController extends BaseController
{
    @Autowired
    private ISkuService skuService;

    /**
     * 查询商品sku列表
     */
    @PreAuthorize("@ss.hasPermi('system:sku:list')")
    @GetMapping("/list")
    public TableDataInfo list(Sku sku)
    {
        startPage();
        List<Sku> list = skuService.selectSkuList(sku);
        return getDataTable(list);
    }

    /**
     * 导出商品sku列表
     */
    @PreAuthorize("@ss.hasPermi('system:sku:export')")
    @Log(title = "商品sku", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Sku sku)
    {
        List<Sku> list = skuService.selectSkuList(sku);
        ExcelUtil<Sku> util = new ExcelUtil<Sku>(Sku.class);
        util.exportExcel(response, list, "商品sku数据");
    }

    /**
     * 获取商品sku详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:sku:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(skuService.selectSkuById(id));
    }

    /**
     * 新增商品sku
     */
    @PreAuthorize("@ss.hasPermi('system:sku:add')")
    @Log(title = "商品sku", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Sku sku)
    {
        return toAjax(skuService.insertSku(sku));
    }

    /**
     * 修改商品sku
     */
    @PreAuthorize("@ss.hasPermi('system:sku:edit')")
    @Log(title = "商品sku", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Sku sku)
    {
        return toAjax(skuService.updateSku(sku));
    }

    /**
     * 删除商品sku
     */
    @PreAuthorize("@ss.hasPermi('system:sku:remove')")
    @Log(title = "商品sku", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(skuService.deleteSkuByIds(ids));
    }
}
