package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 店铺信息对象 shop_info
 * 
 * @author ruoyi
 * @date 2024-12-21
 */
public class ShopInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String shopName;

    /** 店铺图片 */
    @Excel(name = "店铺图片")
    private String shopImg;

    /** 商户ID */
    @Excel(name = "商户ID")
    private Long sellerId;

    /** 商户账号 */
    @Excel(name = "商户账号")
    private String sellerAccount;

    /** 商品数量 */
    @Excel(name = "商品数量")
    private Integer goodsNum;

    /** 粉丝数量 */
    @Excel(name = "粉丝数量")
    private Integer fansNum;

    /** 销售量 */
    @Excel(name = "销售量")
    private Integer soldNum;

    /** 推荐标志 0：未推荐 1：已推荐 */
    @Excel(name = "推荐标志 0：未推荐 1：已推荐")
    private Integer recommendedFlag;

    /** 店铺状态（0：停用 1：正常） */
    @Excel(name = "店铺状态", readConverterExp = "0=：停用,1=：正常")
    private Integer status;

    /** 删除标志（0：正常 1：删除） */
    private Integer delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setShopName(String shopName) 
    {
        this.shopName = shopName;
    }

    public String getShopName() 
    {
        return shopName;
    }
    public void setShopImg(String shopImg) 
    {
        this.shopImg = shopImg;
    }

    public String getShopImg() 
    {
        return shopImg;
    }
    public void setSellerId(Long sellerId) 
    {
        this.sellerId = sellerId;
    }

    public Long getSellerId() 
    {
        return sellerId;
    }

    public String getSellerAccount() {
        return sellerAccount;
    }

    public void setSellerAccount(String sellerAccount) {
        this.sellerAccount = sellerAccount;
    }

    public void setGoodsNum(Integer goodsNum)
    {
        this.goodsNum = goodsNum;
    }

    public Integer getGoodsNum() 
    {
        return goodsNum;
    }
    public void setFansNum(Integer fansNum) 
    {
        this.fansNum = fansNum;
    }

    public Integer getFansNum() 
    {
        return fansNum;
    }
    public void setSoldNum(Integer soldNum) 
    {
        this.soldNum = soldNum;
    }

    public Integer getSoldNum() 
    {
        return soldNum;
    }

    public Integer getRecommendedFlag() {
        return recommendedFlag;
    }

    public void setRecommendedFlag(Integer recommendedFlag) {
        this.recommendedFlag = recommendedFlag;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
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
            .append("shopName", getShopName())
            .append("shopImg", getShopImg())
            .append("sellerId", getSellerId())
            .append("goodsNum", getGoodsNum())
            .append("fansNum", getFansNum())
            .append("soldNum", getSoldNum())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
