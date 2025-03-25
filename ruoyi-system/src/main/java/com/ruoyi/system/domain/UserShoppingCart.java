package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 用户购物车对象 user_shopping_cart
 * 
 * @author ruoyi
 * @date 2024-12-26
 */
public class UserShoppingCart extends SellingGoodsInfo
{
    private static final long serialVersionUID = 1L;

    /** 购物车ID */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 在售商品ID */
    @Excel(name = "在售商品ID")
    private Long sellingGoodsInfoId;

    /** 数量 */
    @Excel(name = "数量")
    private Long quantity;

    /** 商品SKU ID */
    @Excel(name = "商品SKU ID")
    private Long skuId;

    /** 商品SKU图片 */
    @Excel(name = "商品SKU图片")
    private String skuImgs;

    /** 商品SKU对应属性 */
    @Excel(name = "商品SKU对应属性")
    private List<SkuAttrValue> skuAttrValues;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setSellingGoodsInfoId(Long sellingGoodsInfoId) 
    {
        this.sellingGoodsInfoId = sellingGoodsInfoId;
    }

    public Long getSellingGoodsInfoId() 
    {
        return sellingGoodsInfoId;
    }
    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
    }
    public void setSkuId(Long skuId) 
    {
        this.skuId = skuId;
    }

    public Long getSkuId() 
    {
        return skuId;
    }

    public String getSkuImgs() {
        return skuImgs;
    }

    public void setSkuImgs(String skuImgs) {
        this.skuImgs = skuImgs;
    }

    public List<SkuAttrValue> getSkuAttrValues() {
        return skuAttrValues;
    }

    public void setSkuAttrValues(List<SkuAttrValue> skuAttrValues) {
        this.skuAttrValues = skuAttrValues;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("sellingGoodsInfoId", getSellingGoodsInfoId())
            .append("quantity", getQuantity())
            .append("skuId", getSkuId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
