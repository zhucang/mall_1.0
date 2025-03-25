package com.ruoyi.web.controller.api;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.ShopInfo;
import com.ruoyi.system.service.IShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 店铺信息Controller
 * 
 * @author ruoyi
 * @date 2024-12-21
 */
@RestController
@RequestMapping("/api/shopInfo")
public class ApiShopInfoController extends BaseController
{
    @Autowired
    private IShopInfoService shopInfoService;

    /**
     * 查询店铺信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ShopInfo shopInfo)
    {
//        shopInfo.setSellerId(SecurityUtils.getUserId());
        startPage();
        List<ShopInfo> list = shopInfoService.selectShopInfoList(shopInfo);
        return getDataTable(list);
    }

    /**
     * 获取店铺信息详细信息
     */
    @GetMapping(value = "/getInfo")
    public AjaxResult getInfo(Long id)
    {
        ShopInfo shopInfo = shopInfoService.selectShopInfoById(id);
        if (shopInfo != null){
            shopInfoService.fillOtherInfo(shopInfo);
        }
        return success(shopInfo);
    }

    /**
     * 推荐店铺
     */
    @GetMapping("/recommendedShops")
    public TableDataInfo recommendedShops(ShopInfo shopInfo)
    {
        shopInfo.getParams().put("randNum",5);
        List<ShopInfo> list = shopInfoService.selectShopInfoList(shopInfo);
        return getDataTable(list);
    }
}
