package com.ruoyi.web.controller.api;

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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.GoodsEvaluation;
import com.ruoyi.system.service.IGoodsEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品评价Controller
 * 
 * @author ruoyi
 * @date 2025-03-25
 */
@RestController
@RequestMapping("/api/goodsEvaluation")
public class ApiGoodsEvaluationController extends BaseController
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
        List<GoodsEvaluation> list = goodsEvaluationService.selectGoodsEvaluationList(goodsEvaluation);
        return getDataTable(list);
    }

    /**
     * 用户评价
     */
    @Log(title = "用户评价", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("/userAddGoodsEvaluation")
    public AjaxResult userAddGoodsEvaluation(@RequestBody GoodsEvaluation goodsEvaluation)
    {
        goodsEvaluation.setUserId(SecurityUtils.getUserId());
        if (StringUtils.isEmpty(goodsEvaluation.getEvaluateContent())){
            throw new LangException(HintConstants.PARAM_NULL,"请输入评价内容");
        }
        Integer score1 = goodsEvaluation.getScore1();
        if (score1 == null){
            throw new LangException(HintConstants.PARAM_NULL,"请选择描述相符评分");
        }else {
            if (score1 < 0 || score1 > 5){
                throw new LangException(HintConstants.SYSTEM_BUSY,"请正确选择描述相符评分");
            }
        }
        Integer score2 = goodsEvaluation.getScore2();
        if (score2 == null){
            throw new LangException(HintConstants.PARAM_NULL,"请选择物流服务评分");
        }else {
            if (score2 < 0 || score2 > 5){
                throw new LangException(HintConstants.SYSTEM_BUSY,"请正确选择物流服务评分");
            }
        }
        Integer score3 = goodsEvaluation.getScore3();
        if (score3 == null){
            throw new LangException(HintConstants.PARAM_NULL,"请选择服务态度评分");
        }else {
            if (score3 < 0 || score3 > 5){
                throw new LangException(HintConstants.SYSTEM_BUSY,"请正确选择服务态度评分");
            }
        }
        return toAjax(goodsEvaluationService.insertGoodsEvaluation(goodsEvaluation));
    }
}
