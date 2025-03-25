package com.ruoyi.web.controller.api;

import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.HintConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.LangException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.UserFavoriteGoods;
import com.ruoyi.system.service.IUserFavoriteGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户商品收藏Controller
 * 
 * @author ruoyi
 * @date 2024-12-24
 */
@RestController
@RequestMapping("/api/userFavoriteGoods")
public class ApiUserFavoriteGoodsController extends BaseController
{
    @Autowired
    private IUserFavoriteGoodsService userFavoriteGoodsService;

    /**
     * 查询用户商品收藏列表
     */
    @GetMapping("/list")
    public TableDataInfo list(UserFavoriteGoods userFavoriteGoods)
    {
        userFavoriteGoods.setUserId(SecurityUtils.getUserId());
        startPage();
        List<UserFavoriteGoods> list = userFavoriteGoodsService.selectUserFavoriteGoodsList(userFavoriteGoods);
        return getDataTable(list);
    }

    /**
     * 新增用户商品收藏
     */
//    @Log(title = "用户添加商品收藏", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/userAddFavoriteGoods")
    public AjaxResult add(@RequestBody UserFavoriteGoods userFavoriteGoods) {
        if (userFavoriteGoods.getSellingGoodsInfoId() == null){
            throw new LangException(HintConstants.SYSTEM_BUSY,"请选择需要操作的商品");
        }
        return AjaxResult.success(userFavoriteGoodsService.insertUserFavoriteGoods(userFavoriteGoods));
    }

    /**
     * 删除用户商品收藏
     */
//    @Log(title = "用户取消商品收藏", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@PostMapping("/userRemoveFavoriteGoods")
    public AjaxResult remove(Long id)
    {
        if (id == null){
            throw new LangException(HintConstants.SYSTEM_BUSY,"请选择需要操作的商品");
        }
        return AjaxResult.success(userFavoriteGoodsService.deleteUserFavoriteGoodsById(id));
    }
}
