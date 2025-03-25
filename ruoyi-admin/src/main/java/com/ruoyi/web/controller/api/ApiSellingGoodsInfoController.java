package com.ruoyi.web.controller.api;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.constant.HintConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.LangException;
import com.ruoyi.system.domain.SellingGoodsInfo;
import com.ruoyi.system.service.ISellingGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 在售商品信息Controller
 * 
 * @author ruoyi
 * @date 2024-12-20
 */
@RestController
@RequestMapping("/api/sellingGoodsInfo")
public class ApiSellingGoodsInfoController extends BaseController
{
    @Autowired
    private ISellingGoodsInfoService sellingGoodsInfoService;

    /**
     * 查询在售商品信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SellingGoodsInfo sellingGoodsInfo,String isAsc)
    {
        startPage();
        //分页信息
        Page<Object> page = PageHelper.getLocalPage();
        sellingGoodsInfo.getParams().put("startRow",page.getStartRow());
        sellingGoodsInfo.getParams().put("endRow",page.getEndRow());
        sellingGoodsInfo.getParams().put("orderBy", Arrays.stream(page.getOrderBy().split(",")).map(a -> a.replace("asc","").replace("desc","") + " " + isAsc).collect(Collectors.joining(",")));
        PageHelper.clearPage();
        sellingGoodsInfo.setPageNum(1);
        List<SellingGoodsInfo> list = sellingGoodsInfoService.selectSellingGoodsInfoList(sellingGoodsInfo);
        return getDataTable(list);
    }

    /**
     * 获取在售商品信息详细信息
     */
    @GetMapping(value = "/getInfo")
    public AjaxResult getInfo(Long id)
    {
        SellingGoodsInfo sellingGoodsInfo = sellingGoodsInfoService.selectSellingGoodsInfoById(id);
        if (sellingGoodsInfo != null){
            sellingGoodsInfoService.fillOtherInfo(sellingGoodsInfo);
        }
        return success(sellingGoodsInfo);
    }

    /**
     * 推荐商品
     */
    @GetMapping("/recommendedGoods")
    public TableDataInfo recommendedGoods(SellingGoodsInfo sellingGoodsInfo)
    {
        sellingGoodsInfo.setRecommendedFlag(1);
        sellingGoodsInfo.getParams().put("randNum",12);
        List<SellingGoodsInfo> list = sellingGoodsInfoService.selectSellingGoodsInfoList(sellingGoodsInfo);
        return getDataTable(list);
    }

    /**
     * 热销产品
     */
    @GetMapping("/hotGoods")
    public TableDataInfo hotGoods(SellingGoodsInfo sellingGoodsInfo)
    {
        sellingGoodsInfo.getParams().put("hotGoodsNum",10);
        List<SellingGoodsInfo> list = sellingGoodsInfoService.selectSellingGoodsInfoList(sellingGoodsInfo);
        return getDataTable(list);
    }

    /**
     * 猜你喜欢
     */
    @GetMapping("/maybeLike")
    public TableDataInfo maybeLike(SellingGoodsInfo sellingGoodsInfo)
    {
        sellingGoodsInfo.getParams().put("maybeLike",10);
        List<SellingGoodsInfo> list = sellingGoodsInfoService.selectSellingGoodsInfoList(sellingGoodsInfo);
        return getDataTable(list);
    }

    /**
     * 商户添加商品到店铺
     */
    @PostMapping("/sellerAddGoodsToShop")
    public AjaxResult sellerAddGoodsToShop(@RequestBody List<Long> systemGoodsIds)
    {
        if (systemGoodsIds == null || systemGoodsIds.size() == 0){
            throw new LangException(HintConstants.PARAM_NULL,"请选择需要上架店铺的商品");
        }
       return toAjax(sellingGoodsInfoService.sellerAddGoodsToShop(systemGoodsIds));
    }

    /**
     * 商户移除店铺商品
     */
    @PostMapping("/sellerRemoveGoodsFromShop")
    public AjaxResult sellerRemoveGoodsFromShop(@RequestBody List<Long> ids)
    {
        if (ids == null || ids.size() == 0){
            throw new LangException(HintConstants.PARAM_NULL,"请选择需要下架店铺的商品");
        }
        return toAjax(sellingGoodsInfoService.sellerRemoveGoodsFromShop(ids));
    }
}
