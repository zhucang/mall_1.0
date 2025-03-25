package com.ruoyi.web.controller.api;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.Category;
import com.ruoyi.system.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品分类信息Controller
 * 
 * @author ruoyi
 * @date 2024-12-03
 */
@RestController
@RequestMapping("/api/category")
public class ApiCategoryController extends BaseController
{
    @Autowired
    private ICategoryService categoryService;

    /**
     * 查询商品分类信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list()
    {
        startOrderBy("sort is null,sort");
        List<Category> list = categoryService.selectAllCategory();
        return getDataTable(list);
    }
}
