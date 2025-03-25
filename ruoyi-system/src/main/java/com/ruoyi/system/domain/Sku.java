package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 商品sku对象 sku
 * 
 * @author ruoyi
 * @date 2024-12-24
 */
public class Sku extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** SKU ID */
    private Long id;

    /** 商品ID */
    @Excel(name = "系统商品ID")
    private Long systemGoodsInfoId;

    /** 属性值ID */
    @Excel(name = "属性值ID")
    private String attrValueId;

    /** 商品价格 */
    @Excel(name = "商品价格")
    private BigDecimal goodsPrice;

    /** 系统价格 */
    @Excel(name = "系统价格")
    private BigDecimal systemPrice;

    /** sku图片 */
    @Excel(name = "sku图片")
    private String skuImgs;

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
    public void setAttrValueId(String attrValueId)
    {
        this.attrValueId = attrValueId;
    }

    public String getAttrValueId()
    {
        return attrValueId;
    }
    public void setGoodsPrice(BigDecimal goodsPrice) 
    {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getGoodsPrice() 
    {
        return goodsPrice;
    }
    public void setSystemPrice(BigDecimal systemPrice) 
    {
        this.systemPrice = systemPrice;
    }

    public BigDecimal getSystemPrice() 
    {
        return systemPrice;
    }
    public void setSkuImgs(String skuImgs) 
    {
        this.skuImgs = skuImgs;
    }

    public String getSkuImgs() 
    {
        return skuImgs;
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
            .append("attrValueId", getAttrValueId())
            .append("goodsPrice", getGoodsPrice())
            .append("systemPrice", getSystemPrice())
            .append("skuImgs", getSkuImgs())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
