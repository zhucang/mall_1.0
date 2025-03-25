package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SystemGoodsInfo;
import com.ruoyi.system.service.ISystemGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 系统商品信息Controller
 * 
 * @author ruoyi
 * @date 2024-12-03
 */
@RestController
@RequestMapping("/system/systemGoodsInfo")
public class SystemGoodsInfoController extends BaseController
{
    @Autowired
    private ISystemGoodsInfoService systemGoodsInfoService;

    /**
     * 查询系统商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:systemGoodsInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(SystemGoodsInfo systemGoodsInfo)
    {
        startPage();
        startOrderBy("a.id desc");
        List<SystemGoodsInfo> list = systemGoodsInfoService.selectSystemGoodsInfoList(systemGoodsInfo);
        return getDataTable(list);
    }

    /**
     * 导出系统商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:systemGoodsInfo:export')")
    @Log(title = "系统商品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SystemGoodsInfo systemGoodsInfo)
    {
        List<SystemGoodsInfo> list = systemGoodsInfoService.selectSystemGoodsInfoList(systemGoodsInfo);
        ExcelUtil<SystemGoodsInfo> util = new ExcelUtil<SystemGoodsInfo>(SystemGoodsInfo.class);
        util.exportExcel(response, list, "系统商品信息数据");
    }

    /**
     * 获取系统商品信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:systemGoodsInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(systemGoodsInfoService.selectSystemGoodsInfoById(id));
    }

    /**
     * 新增系统商品信息
     */
    @PreAuthorize("@ss.hasPermi('system:systemGoodsInfo:add')")
    @Log(title = "系统商品信息", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody SystemGoodsInfo systemGoodsInfo)
    {
        return toAjax(systemGoodsInfoService.insertSystemGoodsInfo(systemGoodsInfo));
    }

    /**
     * 修改系统商品信息
     */
    @PreAuthorize("@ss.hasPermi('system:systemGoodsInfo:edit')")
    @Log(title = "系统商品信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody SystemGoodsInfo systemGoodsInfo)
    {
        return toAjax(systemGoodsInfoService.updateSystemGoodsInfo(systemGoodsInfo));
    }

    /**
     * 修改商品名称多语言配置
     */
    @PreAuthorize("@ss.hasPermi('system:systemGoodsInfo:edit')")
    @Log(title = "修改商品名称多语言配置", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping(value = "/updateGoodsNameLang")
    public AjaxResult updateGoodsNameLang(@RequestBody SystemGoodsInfo systemGoodsInfo)
    {
        if (systemGoodsInfo.getId() == null){
            throw new ServiceException("请选择需要修改的选项");
        }
        return toAjax(systemGoodsInfoService.updateGoodsNameLang(systemGoodsInfo.getId(),systemGoodsInfo.getGoodsNameLang()));
    }

    /**
     * 修改商品详情多语言配置
     */
    @PreAuthorize("@ss.hasPermi('system:systemGoodsInfo:edit')")
    @Log(title = "修改商品详情多语言配置", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping(value = "/updateGoodsDescLang")
    public AjaxResult updateGoodsDescLang(@RequestBody SystemGoodsInfo systemGoodsInfo)
    {
        if (systemGoodsInfo.getId() == null){
            throw new ServiceException("请选择需要修改的选项");
        }
        return toAjax(systemGoodsInfoService.updateGoodsDescLang(systemGoodsInfo.getId(),systemGoodsInfo.getGoodsDescLang()));
    }

    /**
     * 删除系统商品信息
     */
    @PreAuthorize("@ss.hasPermi('system:systemGoodsInfo:remove')")
    @Log(title = "系统商品信息", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(systemGoodsInfoService.deleteSystemGoodsInfoByIds(ids));
    }
}
