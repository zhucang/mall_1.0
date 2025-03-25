package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.AttrType;
import com.ruoyi.system.service.IAttrTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 属性类型Controller
 * 
 * @author ruoyi
 * @date 2024-12-20
 */
@RestController
@RequestMapping("/system/attrType")
public class AttrTypeController extends BaseController
{
    @Autowired
    private IAttrTypeService attrTypeService;

    /**
     * 查询属性类型列表
     */
    @PreAuthorize("@ss.hasPermi('system:attrType:list')")
    @GetMapping("/list")
    public TableDataInfo list(AttrType attrType)
    {
        startPage();
        List<AttrType> list = attrTypeService.selectAttrTypeList(attrType);
        return getDataTable(list);
    }

    /**
     * 导出属性类型列表
     */
    @PreAuthorize("@ss.hasPermi('system:attrType:export')")
    @Log(title = "属性类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AttrType attrType)
    {
        List<AttrType> list = attrTypeService.selectAttrTypeList(attrType);
        ExcelUtil<AttrType> util = new ExcelUtil<AttrType>(AttrType.class);
        util.exportExcel(response, list, "属性类型数据");
    }

    /**
     * 获取属性类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:attrType:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(attrTypeService.selectAttrTypeById(id));
    }

    /**
     * 新增属性类型
     */
    @PreAuthorize("@ss.hasPermi('system:attrType:add')")
    @Log(title = "属性类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AttrType attrType)
    {
        return toAjax(attrTypeService.insertAttrType(attrType));
    }

    /**
     * 修改属性类型
     */
    @PreAuthorize("@ss.hasPermi('system:attrType:edit')")
    @Log(title = "属性类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AttrType attrType)
    {
        return toAjax(attrTypeService.updateAttrType(attrType));
    }

    /**
     * 修改属性类型名称多语言
     */
    @PreAuthorize("@ss.hasPermi('system:attrType:edit')")
    @Log(title = "修改属性类型名称多语言", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping(value = "/updateAttrTypeNameLang")
    public AjaxResult updateAttrTypeNameLang(@RequestBody AttrType attrType)
    {
        if (attrType.getId() == null){
            throw new ServiceException("请选择需要修改的选项");
        }
        return toAjax(attrTypeService.updateAttrTypeNameLang(attrType.getId(),attrType.getAttrTypeNameLang()));
    }

    /**
     * 删除属性类型
     */
    @PreAuthorize("@ss.hasPermi('system:attrType:remove')")
    @Log(title = "属性类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(attrTypeService.deleteAttrTypeByIds(ids));
    }
}
