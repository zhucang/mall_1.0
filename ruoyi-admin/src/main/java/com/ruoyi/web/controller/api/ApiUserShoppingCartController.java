package com.ruoyi.web.controller.api;

import cn.hutool.json.JSONObject;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.HintConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.LangException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.UserShoppingCart;
import com.ruoyi.system.service.IUserShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户购物车Controller
 * 
 * @author ruoyi
 * @date 2024-12-26
 */
@RestController
@RequestMapping("/api/userShoppingCart")
public class ApiUserShoppingCartController extends BaseController
{
    @Autowired
    private IUserShoppingCartService userShoppingCartService;

    /**
     * 查询用户购物车列表
     */
    @GetMapping("/list")
    public TableDataInfo list(UserShoppingCart userShoppingCart)
    {
        userShoppingCart.setUserId(SecurityUtils.getUserId());
        startPage();
        startOrderBy("update_time desc");
        List<UserShoppingCart> list = userShoppingCartService.selectUserShoppingCartList(userShoppingCart);
        //先根据店铺ID分组
        Map<Long, List<UserShoppingCart>> map = list.stream().collect(Collectors.groupingBy(a -> a.getShopInfoId()));
        //结果（需按时间排序）
        List<Object> result = new ArrayList<>();
        //分组
        for (int i = 0; i < list.size(); i++) {
            //购物车商品信息
            UserShoppingCart vo = list.get(i);
            //店铺ID
            Long shopInfoId = vo.getShopInfoId();
            //店铺名称
            String shopName = vo.getShopName();
            //对应店铺的购物车数据
            List<UserShoppingCart> userShoppingCarts = map.get(shopInfoId);
            //如果取到数据
            if (userShoppingCarts != null){
                JSONObject object = new JSONObject();
                object.put("shopInfoId",shopInfoId);
                object.put("shopName",shopName);
                object.put("userShoppingCarts",userShoppingCarts);
                result.add(object);
                map.remove(shopInfoId);
            }
        }
        return getDataTable(result);
    }


    /**
     * 用户添加购物车商品
     */
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult add(@RequestBody UserShoppingCart userShoppingCart)
    {
        userShoppingCart.setUserId(SecurityUtils.getUserId());
        if (userShoppingCart.getSellingGoodsInfoId() == null){
            throw new LangException(HintConstants.PARAM_NULL,"请选择商品");
        }
        if (userShoppingCart.getQuantity() == null){
            throw new LangException(HintConstants.PARAM_NULL,"请输入数量");
        }
        return toAjax(userShoppingCartService.insertUserShoppingCart(userShoppingCart));
    }

    /**
     * 用户编辑购物车商品
     */
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody UserShoppingCart userShoppingCart)
    {
        if (userShoppingCart.getId() == null){
            throw new LangException(HintConstants.PARAM_NULL,"请选择需要编辑的选项");
        }
        if (userShoppingCart.getQuantity() == null){
            throw new LangException(HintConstants.PARAM_NULL,"请输入数量");
        }
        return toAjax(userShoppingCartService.updateUserShoppingCart(userShoppingCart));
    }

    /**
     * 用户删除购物车商品
     */
    @PostMapping("/remove")
    public AjaxResult remove(@RequestBody Long[] ids)
    {
        return toAjax(userShoppingCartService.deleteUserShoppingCartByIds(ids));
    }
}
