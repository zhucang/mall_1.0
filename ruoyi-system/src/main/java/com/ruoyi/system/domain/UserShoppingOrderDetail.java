package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户购物订单明细对象 user_shopping_order_detail
 * 
 * @author ruoyi
 * @date 2024-12-24
 */
public class UserShoppingOrderDetail extends SellingGoodsInfo
{
    private static final long serialVersionUID = 1L;

    /** 用户购物订单明细ID */
    private Long id;

    /** 用户购物订单ID */
    @Excel(name = "用户购物订单ID")
    private Long userShoppingOrderId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 在售商品信息ID */
    @Excel(name = "在售商品信息ID")
    private Long sellingGoodsInfoId;

    /** 商品SKU ID */
    @Excel(name = "商品SKU ID")
    private Long skuId;

    /** 商品SKU图片 */
    @Excel(name = "商品SKU图片")
    private String skuImgs;

    /** 购买数量 */
    @Excel(name = "购买数量")
    private Integer buyNum;

    /** 商品单价 */
    @Excel(name = "商品单价")
    private BigDecimal goodsSinglePrice;

    /** 订单金额 */
    @Excel(name = "订单金额")
    private BigDecimal orderPrice;

    /** 商品系统单价 */
    @Excel(name = "商品系统单价")
    private BigDecimal goodsSystemSinglePrice;

    /** 订单系统总金额 */
    @Excel(name = "订单系统总金额")
    private BigDecimal orderSystemPrice;

    /** 运费金额 */
    @Excel(name = "运费金额")
    private BigDecimal freightAmount;

    /** SKU对应属性 */
    @Excel(name = "SKU对应属性")
    private List<SkuAttrValue> skuAttrValues;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserShoppingOrderId(Long userShoppingOrderId) 
    {
        this.userShoppingOrderId = userShoppingOrderId;
    }

    public Long getUserShoppingOrderId() 
    {
        return userShoppingOrderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setSellingGoodsInfoId(Long sellingGoodsInfoId)
    {
        this.sellingGoodsInfoId = sellingGoodsInfoId;
    }

    public Long getSellingGoodsInfoId() 
    {
        return sellingGoodsInfoId;
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

    public void setBuyNum(Integer buyNum)
    {
        this.buyNum = buyNum;
    }

    public Integer getBuyNum() 
    {
        return buyNum;
    }
    public void setGoodsSinglePrice(BigDecimal goodsSinglePrice) 
    {
        this.goodsSinglePrice = goodsSinglePrice;
    }

    public BigDecimal getGoodsSinglePrice() 
    {
        return goodsSinglePrice;
    }
    public void setOrderPrice(BigDecimal orderPrice) 
    {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getOrderPrice() 
    {
        return orderPrice;
    }

    @Override
    public BigDecimal getFreightAmount() {
        return freightAmount;
    }

    public BigDecimal getGoodsSystemSinglePrice() {
        return goodsSystemSinglePrice;
    }

    public void setGoodsSystemSinglePrice(BigDecimal goodsSystemSinglePrice) {
        this.goodsSystemSinglePrice = goodsSystemSinglePrice;
    }

    public BigDecimal getOrderSystemPrice() {
        return orderSystemPrice;
    }

    public void setOrderSystemPrice(BigDecimal orderSystemPrice) {
        this.orderSystemPrice = orderSystemPrice;
    }

    @Override
    public void setFreightAmount(BigDecimal freightAmount) {
        this.freightAmount = freightAmount;
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
            .append("userShoppingOrderId", getUserShoppingOrderId())
            .append("sellingGoodsInfoId", getSellingGoodsInfoId())
            .append("skuId", getSkuId())
            .append("buyNum", getBuyNum())
            .append("goodsSinglePrice", getGoodsSinglePrice())
            .append("orderPrice", getOrderPrice())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
