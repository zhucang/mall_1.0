package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.ShopInfo;
import com.ruoyi.system.service.IShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 店铺信息Controller
 * 
 * @author ruoyi
 * @date 2024-12-21
 */
@RestController
@RequestMapping("/system/shopInfo")
public class ShopInfoController extends BaseController
{
    @Autowired
    private IShopInfoService shopInfoService;

    /**
     * 查询店铺信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShopInfo shopInfo)
    {
        startPage();
        startOrderBy("id desc");
        List<ShopInfo> list = shopInfoService.selectShopInfoList(shopInfo);
        return getDataTable(list);
    }

    /**
     * 导出店铺信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfo:export')")
    @Log(title = "店铺信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopInfo shopInfo)
    {
        List<ShopInfo> list = shopInfoService.selectShopInfoList(shopInfo);
        ExcelUtil<ShopInfo> util = new ExcelUtil<ShopInfo>(ShopInfo.class);
        util.exportExcel(response, list, "店铺信息数据");
    }

    /**
     * 获取店铺信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(shopInfoService.selectShopInfoById(id));
    }

    /**
     * 新增店铺信息
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfo:add')")
    @Log(title = "店铺信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopInfo shopInfo)
    {
        return toAjax(shopInfoService.insertShopInfo(shopInfo));
    }

    /**
     * 修改店铺信息
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfo:edit')")
    @Log(title = "店铺信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopInfo shopInfo)
    {
        return toAjax(shopInfoService.updateShopInfo(shopInfo));
    }

    /**
     * 删除店铺信息
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfo:remove')")
    @Log(title = "店铺信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(shopInfoService.deleteShopInfoByIds(ids));
    }
}
