package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.GoodsEvaluation;

/**
 * 商品评价Service接口
 * 
 * @author ruoyi
 * @date 2025-03-25
 */
public interface IGoodsEvaluationService 
{
    /**
     * 查询商品评价
     * 
     * @param id 商品评价主键
     * @return 商品评价
     */
    public GoodsEvaluation selectGoodsEvaluationById(Long id);

    /**
     * 查询商品评价列表
     * 
     * @param goodsEvaluation 商品评价
     * @return 商品评价集合
     */
    public List<GoodsEvaluation> selectGoodsEvaluationList(GoodsEvaluation goodsEvaluation);

    /**
     * 新增商品评价
     * 
     * @param goodsEvaluation 商品评价
     * @return 结果
     */
    public int insertGoodsEvaluation(GoodsEvaluation goodsEvaluation);

    /**
     * 修改商品评价
     * 
     * @param goodsEvaluation 商品评价
     * @return 结果
     */
    public int updateGoodsEvaluation(GoodsEvaluation goodsEvaluation);

    /**
     * 批量删除商品评价
     * 
     * @param ids 需要删除的商品评价主键集合
     * @return 结果
     */
    public int deleteGoodsEvaluationByIds(Long[] ids);

    /**
     * 删除商品评价信息
     * 
     * @param id 商品评价主键
     * @return 结果
     */
    public int deleteGoodsEvaluationById(Long id);
}
