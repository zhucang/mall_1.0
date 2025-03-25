package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.UserShoppingCart;
import com.ruoyi.system.service.IUserShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户购物车Controller
 * 
 * @author ruoyi
 * @date 2024-12-26
 */
@RestController
@RequestMapping("/system/userShoppingCart")
public class UserShoppingCartController extends BaseController
{
    @Autowired
    private IUserShoppingCartService userShoppingCartService;

    /**
     * 查询用户购物车列表
     */
    @PreAuthorize("@ss.hasPermi('system:userShoppingCart:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserShoppingCart userShoppingCart)
    {
        startPage();
        List<UserShoppingCart> list = userShoppingCartService.selectUserShoppingCartList(userShoppingCart);
        return getDataTable(list);
    }

    /**
     * 导出用户购物车列表
     */
    @PreAuthorize("@ss.hasPermi('system:userShoppingCart:export')")
    @Log(title = "用户购物车", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserShoppingCart userShoppingCart)
    {
        List<UserShoppingCart> list = userShoppingCartService.selectUserShoppingCartList(userShoppingCart);
        ExcelUtil<UserShoppingCart> util = new ExcelUtil<UserShoppingCart>(UserShoppingCart.class);
        util.exportExcel(response, list, "用户购物车数据");
    }

    /**
     * 获取用户购物车详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:userShoppingCart:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(userShoppingCartService.selectUserShoppingCartById(id));
    }

    /**
     * 新增用户购物车
     */
    @PreAuthorize("@ss.hasPermi('system:userShoppingCart:add')")
    @Log(title = "用户购物车", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserShoppingCart userShoppingCart)
    {
        return toAjax(userShoppingCartService.insertUserShoppingCart(userShoppingCart));
    }

    /**
     * 修改用户购物车
     */
    @PreAuthorize("@ss.hasPermi('system:userShoppingCart:edit')")
    @Log(title = "用户购物车", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserShoppingCart userShoppingCart)
    {
        return toAjax(userShoppingCartService.updateUserShoppingCart(userShoppingCart));
    }

    /**
     * 删除用户购物车
     */
    @PreAuthorize("@ss.hasPermi('system:userShoppingCart:remove')")
    @Log(title = "用户购物车", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userShoppingCartService.deleteUserShoppingCartByIds(ids));
    }
}
