package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.constant.HintConstants;
import com.ruoyi.common.exception.LangException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.UserShoppingOrder;
import com.ruoyi.system.domain.UserShoppingOrderDetail;
import com.ruoyi.system.service.IUserShoppingOrderDetailService;
import com.ruoyi.system.service.IUserShoppingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.GoodsEvaluationMapper;
import com.ruoyi.system.domain.GoodsEvaluation;
import com.ruoyi.system.service.IGoodsEvaluationService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 商品评价Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-25
 */
@Service
public class GoodsEvaluationServiceImpl implements IGoodsEvaluationService 
{
    @Resource
    private GoodsEvaluationMapper goodsEvaluationMapper;

    @Autowired
    private IUserShoppingOrderService userShoppingOrderService;

    @Autowired
    private IUserShoppingOrderDetailService userShoppingOrderDetailService;

    /**
     * 查询商品评价
     * 
     * @param id 商品评价主键
     * @return 商品评价
     */
    @Override
    public GoodsEvaluation selectGoodsEvaluationById(Long id)
    {
        return goodsEvaluationMapper.selectGoodsEvaluationById(id);
    }

    /**
     * 查询商品评价列表
     * 
     * @param goodsEvaluation 商品评价
     * @return 商品评价
     */
    @Override
    public List<GoodsEvaluation> selectGoodsEvaluationList(GoodsEvaluation goodsEvaluation)
    {
        return goodsEvaluationMapper.selectGoodsEvaluationList(goodsEvaluation);
    }

    /**
     * 新增商品评价
     * 
     * @param goodsEvaluation 商品评价
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertGoodsEvaluation(GoodsEvaluation goodsEvaluation)
    {
        //用户购物订单ID
        Long userShoppingOrderDetailId = goodsEvaluation.getUserShoppingOrderDetailId();
        if (userShoppingOrderDetailId == null){
            throw new LangException(HintConstants.PARAM_NULL,"请选择需要评论的订单");
        }
        //用户购物订单明细信息
        UserShoppingOrderDetail userShoppingOrderDetail = userShoppingOrderDetailService.selectUserShoppingOrderDetailById(userShoppingOrderDetailId);
        if (userShoppingOrderDetail == null){
            throw new LangException(HintConstants.SYSTEM_BUSY,"获取用户购物订单明细信息异常");
        }
        //用户购物订单信息
        UserShoppingOrder userShoppingOrder = userShoppingOrderService.selectUserShoppingOrderById(userShoppingOrderDetail.getUserShoppingOrderId());
        if (!userShoppingOrder.getOrderStatus().equals(5)){
            throw new LangException(HintConstants.SYSTEM_BUSY,"订单非待评论状态");
        }
        //更新订单状态为已完成
        userShoppingOrder.setOrderStatus(6);
        int updateUserShoppingOrder = userShoppingOrderService.updateUserShoppingOrder(userShoppingOrder);
        if (updateUserShoppingOrder == 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"更新用户购物订单信息异常");
        }
        //更新订单明细状态为已完成
        userShoppingOrderDetail.setOrderStatus(6);
        int updateUserShoppingOrderDetail = userShoppingOrderDetailService.updateUserShoppingOrderDetail(userShoppingOrderDetail);
        if (updateUserShoppingOrderDetail == 0){
            throw new LangException(HintConstants.SYSTEM_BUSY,"更新用户购物订单明细信息异常");
        }
        goodsEvaluation.setCreateTime(DateUtils.getNowDate());
        goodsEvaluation.setEvaluateStatus(1);
        return goodsEvaluationMapper.insertGoodsEvaluation(goodsEvaluation);
    }

    /**
     * 修改商品评价
     * 
     * @param goodsEvaluation 商品评价
     * @return 结果
     */
    @Override
    public int updateGoodsEvaluation(GoodsEvaluation goodsEvaluation)
    {
        goodsEvaluation.setUpdateTime(DateUtils.getNowDate());
        return goodsEvaluationMapper.updateGoodsEvaluation(goodsEvaluation);
    }

    /**
     * 批量删除商品评价
     * 
     * @param ids 需要删除的商品评价主键
     * @return 结果
     */
    @Override
    public int deleteGoodsEvaluationByIds(Long[] ids)
    {
        return goodsEvaluationMapper.deleteGoodsEvaluationByIds(ids);
    }

    /**
     * 删除商品评价信息
     * 
     * @param id 商品评价主键
     * @return 结果
     */
    @Override
    public int deleteGoodsEvaluationById(Long id)
    {
        return goodsEvaluationMapper.deleteGoodsEvaluationById(id);
    }
}
