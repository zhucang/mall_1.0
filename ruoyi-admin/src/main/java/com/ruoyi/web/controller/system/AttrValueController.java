package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.AttrValue;
import com.ruoyi.system.service.IAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 属性值Controller
 * 
 * @author ruoyi
 * @date 2024-12-20
 */
@RestController
@RequestMapping("/system/attrValue")
public class AttrValueController extends BaseController
{
    @Autowired
    private IAttrValueService attrValueService;

    /**
     * 查询属性值列表
     */
    @PreAuthorize("@ss.hasPermi('system:attrValue:list')")
    @GetMapping("/list")
    public TableDataInfo list(AttrValue attrValue)
    {
        startPage();
        List<AttrValue> list = attrValueService.selectAttrValueList(attrValue);
        return getDataTable(list);
    }

    /**
     * 导出属性值列表
     */
    @PreAuthorize("@ss.hasPermi('system:attrValue:export')")
    @Log(title = "属性值", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AttrValue attrValue)
    {
        List<AttrValue> list = attrValueService.selectAttrValueList(attrValue);
        ExcelUtil<AttrValue> util = new ExcelUtil<AttrValue>(AttrValue.class);
        util.exportExcel(response, list, "属性值数据");
    }

    /**
     * 获取属性值详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:attrValue:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(attrValueService.selectAttrValueById(id));
    }

    /**
     * 新增属性值
     */
    @PreAuthorize("@ss.hasPermi('system:attrValue:add')")
    @Log(title = "属性值", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AttrValue attrValue)
    {
        return toAjax(attrValueService.insertAttrValue(attrValue));
    }

    /**
     * 修改属性值
     */
    @PreAuthorize("@ss.hasPermi('system:attrValue:edit')")
    @Log(title = "属性值", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AttrValue attrValue)
    {
        return toAjax(attrValueService.updateAttrValue(attrValue));
    }

    /**
     * 修改属性值名称多语言
     */
    @PreAuthorize("@ss.hasPermi('system:attrValue:edit')")
    @Log(title = "修改属性值名称多语言", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping(value = "/updateAttrValueNameLang")
    public AjaxResult updateAttrValueNameLang(@RequestBody AttrValue attrValue)
    {
        if (attrValue.getId() == null){
            throw new ServiceException("请选择需要修改的选项");
        }
        return toAjax(attrValueService.updateAttrValueNameLang(attrValue.getId(),attrValue.getAttrValueNameLang()));
    }

    /**
     * 删除属性值
     */
    @PreAuthorize("@ss.hasPermi('system:attrValue:remove')")
    @Log(title = "属性值", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(attrValueService.deleteAttrValueByIds(ids));
    }
}
