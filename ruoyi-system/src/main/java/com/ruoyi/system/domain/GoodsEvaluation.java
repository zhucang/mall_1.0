package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.entity.UserInfoDetailVo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 商品评价对象 goods_evaluation
 * 
 * @author ruoyi
 * @date 2025-03-25
 */
public class GoodsEvaluation extends UserInfoDetailVo
{
    private static final long serialVersionUID = 1L;

    /** 商品评论ID */
    private Long id;

    /** 用户购物订单明细ID */
    @Excel(name = "用户购物订单明细ID")
    private Long userShoppingOrderDetailId;

    /** 所属商户ID */
    @Excel(name = "所属商户ID")
    private Long sellerId;

    /** 所属商户账号 */
    @Excel(name = "所属商户账号")
    private String sellerAccount;

    /** 在售商品信息ID */
    @Excel(name = "在售商品信息ID")
    private Long sellingGoodsInfoId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品名称多语言 */
    @Excel(name = "商品名称多语言")
    private LangMgr goodsNameLang;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String goodsImg;

    /** 商品SKU对应属性 */
    @Excel(name = "商品SKU对应属性")
    private List<SkuAttrValue> skuAttrValues;

    /** 评价内容 */
    @Excel(name = "评价内容")
    private String evaluateContent;

    /** 评价图片视频（使用逗号隔开） */
    @Excel(name = "评价图片视频")
    private String evaluateImg;

    /** 描述相符评分 */
    @Excel(name = "描述相符评分")
    private Integer score1;

    /** 物流服务评分 */
    @Excel(name = "物流服务评分")
    private Integer score2;

    /** 服务态度评分 */
    @Excel(name = "服务态度评分")
    private Integer score3;

    /** 评价状态 0：审核中 1：正常 2：违规 */
    @Excel(name = "评价状态 0：审核中 1：正常 2：违规")
    private Integer evaluateStatus;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public Long getUserShoppingOrderDetailId() {
        return userShoppingOrderDetailId;
    }

    public void setUserShoppingOrderDetailId(Long userShoppingOrderDetailId) {
        this.userShoppingOrderDetailId = userShoppingOrderDetailId;
    }
    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerAccount() {
        return sellerAccount;
    }

    public void setSellerAccount(String sellerAccount) {
        this.sellerAccount = sellerAccount;
    }

    public Long getSellingGoodsInfoId() {
        return sellingGoodsInfoId;
    }

    public void setSellingGoodsInfoId(Long sellingGoodsInfoId) {
        this.sellingGoodsInfoId = sellingGoodsInfoId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public LangMgr getGoodsNameLang() {
        return goodsNameLang;
    }

    public void setGoodsNameLang(LangMgr goodsNameLang) {
        this.goodsNameLang = goodsNameLang;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public List<SkuAttrValue> getSkuAttrValues() {
        return skuAttrValues;
    }

    public void setSkuAttrValues(List<SkuAttrValue> skuAttrValues) {
        this.skuAttrValues = skuAttrValues;
    }

    public void setEvaluateContent(String evaluateContent)
    {
        this.evaluateContent = evaluateContent;
    }

    public String getEvaluateContent() 
    {
        return evaluateContent;
    }
    public void setEvaluateImg(String evaluateImg) 
    {
        this.evaluateImg = evaluateImg;
    }

    public String getEvaluateImg() 
    {
        return evaluateImg;
    }
    public void setScore1(Integer score1) 
    {
        this.score1 = score1;
    }

    public Integer getScore1() 
    {
        return score1;
    }
    public void setScore2(Integer score2) 
    {
        this.score2 = score2;
    }

    public Integer getScore2() 
    {
        return score2;
    }
    public void setScore3(Integer score3) 
    {
        this.score3 = score3;
    }

    public Integer getScore3() 
    {
        return score3;
    }
    public void setEvaluateStatus(Integer evaluateStatus) 
    {
        this.evaluateStatus = evaluateStatus;
    }

    public Integer getEvaluateStatus() 
    {
        return evaluateStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("evaluateContent", getEvaluateContent())
            .append("evaluateImg", getEvaluateImg())
            .append("score1", getScore1())
            .append("score2", getScore2())
            .append("score3", getScore3())
            .append("evaluateStatus", getEvaluateStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
