package com.ruoyi.web.controller.api;

import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.HintConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.LangException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.UserFavoriteShop;
import com.ruoyi.system.service.IUserFavoriteShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户店铺收藏Controller
 * 
 * @author ruoyi
 * @date 2024-12-24
 */
@RestController
@RequestMapping("/api/userFavoriteShop")
public class ApiUserFavoriteShopController extends BaseController
{
    @Autowired
    private IUserFavoriteShopService userFavoriteShopService;

    /**
     * 查询用户店铺收藏列表
     */
    @GetMapping("/list")
    public TableDataInfo list(UserFavoriteShop userFavoriteShop)
    {
        userFavoriteShop.setUserId(SecurityUtils.getUserId());
        startPage();
        List<UserFavoriteShop> list = userFavoriteShopService.selectUserFavoriteShopList(userFavoriteShop);
        return getDataTable(list);
    }

    /**
     * 新增用户店铺收藏
     */
//    @Log(title = "用户添加店铺收藏", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/userAddFavoriteShop")
    public AjaxResult add(@RequestBody UserFavoriteShop userFavoriteShop)
    {
        if (userFavoriteShop.getShopInfoId() == null){
            throw new LangException(HintConstants.SYSTEM_BUSY,"请选择需要操作的店铺");
        }
        return AjaxResult.success(userFavoriteShopService.insertUserFavoriteShop(userFavoriteShop));
    }

    /**
     * 删除用户店铺收藏
     */
//    @Log(title = "用户取消店铺收藏", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@PostMapping("/userRemoveFavoriteShop")
    public AjaxResult remove(Long id)
    {
        if (id == null){
            throw new LangException(HintConstants.SYSTEM_BUSY,"请选择需要操作的店铺");
        }
        return AjaxResult.success(userFavoriteShopService.deleteUserFavoriteShopById(id));
    }
}
