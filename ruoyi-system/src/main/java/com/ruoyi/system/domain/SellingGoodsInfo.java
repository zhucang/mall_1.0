package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 在售商品信息对象 selling_goods_info
 * 
 * @author ruoyi
 * @date 2024-12-20
 */
public class SellingGoodsInfo extends SystemGoodsInfo
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 系统商品ID */
    @Excel(name = "系统商品ID")
    private Long systemGoodsInfoId;

    /** 在售价格 */
    @Excel(name = "在售价格")
    private BigDecimal sellingPrice;

    /** 销量 */
    @Excel(name = "销量")
    private Integer soldNum;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    /** 店铺ID */
    @Excel(name = "店铺ID")
    private Long shopInfoId;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String shopName;

    /** 商家ID */
    @Excel(name = "商家ID")
    private Long sellerId;

    /** 推荐标志 0：未推荐 1：已推荐 */
    @Excel(name = "推荐标志 0：未推荐 1：已推荐")
    private Integer recommendedFlag;

    /** 删除标志 0：正常 1：删除 */
    private Integer delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSystemGoodsInfoId(Long systemGoodsInfoId) 
    {
        this.systemGoodsInfoId = systemGoodsInfoId;
    }

    public Long getSystemGoodsInfoId() 
    {
        return systemGoodsInfoId;
    }
    public void setSellingPrice(BigDecimal sellingPrice) 
    {
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal getSellingPrice() 
    {
        return sellingPrice;
    }

    public Integer getSoldNum() {
        return soldNum;
    }

    public void setSoldNum(Integer soldNum) {
        this.soldNum = soldNum;
    }

    @Override
    public Integer getSort() {
        return sort;
    }

    @Override
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setShopInfoId(Long shopInfoId)
    {
        this.shopInfoId = shopInfoId;
    }

    public Long getShopInfoId() 
    {
        return shopInfoId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setSellerId(Long sellerId)
    {
        this.sellerId = sellerId;
    }

    public Long getSellerId()
    {
        return sellerId;
    }

    public Integer getRecommendedFlag() {
        return recommendedFlag;
    }

    public void setRecommendedFlag(Integer recommendedFlag) {
        this.recommendedFlag = recommendedFlag;
    }

    public void setDelFlag(Integer delFlag)
    {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("systemGoodsInfoId", getSystemGoodsInfoId())
            .append("sellingPrice", getSellingPrice())
            .append("shopInfoId", getShopInfoId())
            .append("sellerId", getSellerId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
