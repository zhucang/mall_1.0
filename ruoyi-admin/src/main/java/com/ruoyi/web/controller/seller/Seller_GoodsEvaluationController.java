package com.ruoyi.web.controller.seller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.HintConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.LangException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.GoodsEvaluation;
import com.ruoyi.system.service.IGoodsEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品评价Controller
 * 
 * @author ruoyi
 * @date 2025-03-25
 */
@RestController
@RequestMapping("/seller/goodsEvaluation")
public class Seller_GoodsEvaluationController extends BaseController
{
    @Autowired
    private IGoodsEvaluationService goodsEvaluationService;

    /**
     * 查询商品评价列表
     */
    @GetMapping("/list")
    public TableDataInfo list(GoodsEvaluation goodsEvaluation)
    {
        startPage();
        goodsEvaluation.setEvaluateStatus(1);
        goodsEvaluation.setSellerId(SecurityUtils.getUserId());
        List<GoodsEvaluation> list = goodsEvaluationService.selectGoodsEvaluationList(goodsEvaluation);
        return getDataTable(list);
    }
}
