package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.List;

/**
 * 系统商品信息对象 system_goods_info
 * 
 * @author ruoyi
 * @date 2024-12-03
 */
public class SystemGoodsInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 商品编码 */
    @Excel(name = "商品编码")
    private String goodsCode;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品名称多语言 */
    @Excel(name = "商品名称多语言")
    private LangMgr goodsNameLang;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String goodsImg;

    /** 商品描述 */
    @Excel(name = "商品描述")
    private String goodsDesc;

    /** 商品描述多语言 */
    @Excel(name = "商品描述多语言")
    private LangMgr goodsDescLang;

    /** 系统价格 */
    @Excel(name = "系统价格")
    private BigDecimal systemPrice;

    /** 分类ID */
    @Excel(name = "分类ID")
    private Long categoryId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String categoryName;

    /** 运费金额 */
    @Excel(name = "运费金额")
    private BigDecimal freightAmount;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    /** 删除标志 0：正常 1：删除 */
    private Integer delFlag;

    /** 商品属性 */
    private List<AttrType> attrTypes;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setGoodsCode(String goodsCode) 
    {
        this.goodsCode = goodsCode;
    }

    public String getGoodsCode() 
    {
        return goodsCode;
    }
    public void setGoodsName(String goodsName) 
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName() 
    {
        return goodsName;
    }

    public LangMgr getGoodsNameLang() {
        if (goodsNameLang == null){
            goodsNameLang = new LangMgr();
        }
        return goodsNameLang;
    }

    public void setGoodsNameLang(LangMgr goodsNameLang) {
        this.goodsNameLang = goodsNameLang;
    }

    public void setGoodsImg(String goodsImg)
    {
        this.goodsImg = goodsImg;
    }

    public String getGoodsImg() 
    {
        return goodsImg;
    }
    public void setGoodsDesc(String goodsDesc) 
    {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsDesc() 
    {
        return goodsDesc;
    }

    public LangMgr getGoodsDescLang() {
        if (goodsDescLang == null){
            goodsDescLang = new LangMgr();
        }
        return goodsDescLang;
    }

    public void setGoodsDescLang(LangMgr goodsDescLang) {
        this.goodsDescLang = goodsDescLang;
    }

    public void setSystemPrice(BigDecimal systemPrice)
    {
        this.systemPrice = systemPrice;
    }

    public BigDecimal getSystemPrice() 
    {
        return systemPrice;
    }
    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId()
    {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setFreightAmount(BigDecimal freightAmount)
    {
        this.freightAmount = freightAmount;
    }

    public BigDecimal getFreightAmount() 
    {
        return freightAmount;
    }
    public void setSort(Integer sort) 
    {
        this.sort = sort;
    }

    public Integer getSort() 
    {
        return sort;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public List<AttrType> getAttrTypes() {
        return attrTypes;
    }

    public void setAttrTypes(List<AttrType> attrTypes) {
        this.attrTypes = attrTypes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("goodsCode", getGoodsCode())
            .append("goodsName", getGoodsName())
            .append("goodsImg", getGoodsImg())
            .append("goodsDesc", getGoodsDesc())
            .append("systemPrice", getSystemPrice())
            .append("categoryId", getCategoryId())
            .append("freightAmount", getFreightAmount())
            .append("sort", getSort())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
