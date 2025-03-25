package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Category;
import com.ruoyi.system.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品分类信息Controller
 * 
 * @author ruoyi
 * @date 2024-12-03
 */
@RestController
@RequestMapping("/system/category")
public class CategoryController extends BaseController
{
    @Autowired
    private ICategoryService categoryService;

    /**
     * 查询商品分类信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(Category category)
    {
        startPage();
        startOrderBy("sort is null,sort");
        List<Category> list = categoryService.selectCategoryList(category);
        return getDataTable(list);
    }

    /**
     * 查询商品分类信息列表
     */
    @GetMapping("/listNoPreAuthorize")
    public TableDataInfo listNoPreAuthorize(Category category)
    {
        startPage();
        startOrderBy("sort is null,sort");
        List<Category> list = categoryService.selectCategoryList(category);
        return getDataTable(list);
    }

    /**
     * 导出商品分类信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:export')")
    @Log(title = "商品分类信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Category category)
    {
        List<Category> list = categoryService.selectCategoryList(category);
        ExcelUtil<Category> util = new ExcelUtil<Category>(Category.class);
        util.exportExcel(response, list, "商品分类信息数据");
    }

    /**
     * 获取商品分类信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(categoryService.selectCategoryById(id));
    }

    /**
     * 新增商品分类信息
     */
    @PreAuthorize("@ss.hasPermi('system:category:add')")
    @Log(title = "商品分类信息", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody Category category)
    {
        return toAjax(categoryService.insertCategory(category));
    }

    /**
     * 修改商品分类信息
     */
    @PreAuthorize("@ss.hasPermi('system:category:edit')")
    @Log(title = "商品分类信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody Category category)
    {
        return toAjax(categoryService.updateCategory(category));
    }

    /**
     * 修改商品分类名称多语言配置
     */
    @PreAuthorize("@ss.hasPermi('system:category:edit')")
    @Log(title = "修改商品分类名称多语言", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping(value = "/updateCategoryNameLang")
    public AjaxResult updateCategoryNameLang(@RequestBody Category category)
    {
        if (category.getId() == null){
            throw new ServiceException("请选择需要修改的选项");
        }
        return toAjax(categoryService.updateCategoryNameLang(category.getId(),category.getCategoryNameLang()));
    }

    /**
     * 删除商品分类信息
     */
    @PreAuthorize("@ss.hasPermi('system:category:remove')")
    @Log(title = "商品分类信息", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(categoryService.deleteCategoryByIds(ids));
    }
}
