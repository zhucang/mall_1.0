package com.ruoyi.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 系统商品信息对象 selling_goods_info2
 * 
 * @author ruoyi
 * @date 2024-12-06
 */
public class SellingGoodsInfo2 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 商品编码 */
    @Excel(name = "商品编码")
    private String goodsCode;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String goodsImg;

    /** 商品描述 */
    @Excel(name = "商品描述")
    private String goodsDesc;

    /** 在售价格 */
    @Excel(name = "在售价格")
    private BigDecimal sellingPrice;

    /** 系统价格 */
    @Excel(name = "系统价格")
    private BigDecimal systemPrice;

    /** 分类ID */
    @Excel(name = "分类ID")
    private String categoryId;

    /** 运费金额 */
    @Excel(name = "运费金额")
    private BigDecimal freightAmount;

    /** 商家ID */
    @Excel(name = "商家ID")
    private String sellerId;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 删除标志 0：正常 1：删除 */
    private Integer delFlag;

    /** 商品名称-英文 */
    @Excel(name = "商品名称-英文")
    private String goodsNameEn;

    /** 商品名称-繁体 */
    @Excel(name = "商品名称-繁体")
    private String goodsNameTc;

    /** 商品名称-德国 */
    @Excel(name = "商品名称-德国")
    private String goodsNameDe;

    /** 商品名称-西班牙 */
    @Excel(name = "商品名称-西班牙")
    private String goodsNameEs;

    /** 商品名称-法国 */
    @Excel(name = "商品名称-法国")
    private String goodsNameFr;

    /** 商品名称-印度尼西亚 */
    @Excel(name = "商品名称-印度尼西亚")
    private String goodsNameIdn;

    /** 商品名称-日本 */
    @Excel(name = "商品名称-日本")
    private String goodsNameJp;

    /** 商品名称-韩国 */
    @Excel(name = "商品名称-韩国")
    private String goodsNameKo;

    /** 商品名称-马来西亚 */
    @Excel(name = "商品名称-马来西亚")
    private String goodsNameMy;

    /** 商品名称-泰国 */
    @Excel(name = "商品名称-泰国")
    private String goodsNameTh;

    /** 商品名称-越南 */
    @Excel(name = "商品名称-越南")
    private String goodsNameVi;

    /** 商品名称-葡萄牙 */
    @Excel(name = "商品名称-葡萄牙")
    private String goodsNamePt;

    /** 商品名称-俄语 */
    @Excel(name = "商品名称-俄语")
    private String goodsNameRus;

    /** 商品名称-白俄罗斯 */
    @Excel(name = "商品名称-白俄罗斯")
    private String goodsNameBlr;

    /** 商品名称-印度 */
    @Excel(name = "商品名称-印度")
    private String goodsNameIda;

    /** 商品名称-沙特阿拉伯 */
    @Excel(name = "商品名称-沙特阿拉伯")
    private String goodsNameSa;

    /** 商品名称-阿拉伯 */
    @Excel(name = "商品名称-阿拉伯")
    private String goodsNameAr;

    /** 商品名称-意大利 */
    @Excel(name = "商品名称-意大利")
    private String goodsNameIt;

    /** 商品名称-土耳其 */
    @Excel(name = "商品名称-土耳其")
    private String goodsNameTr;

    /** 商品描述-英文 */
    @Excel(name = "商品描述-英文")
    private String goodsDescEn;

    /** 商品描述-繁体 */
    @Excel(name = "商品描述-繁体")
    private String goodsDescTc;

    /** 商品描述-德国 */
    @Excel(name = "商品描述-德国")
    private String goodsDescDe;

    /** 商品描述-西班牙 */
    @Excel(name = "商品描述-西班牙")
    private String goodsDescEs;

    /** 商品描述-法国 */
    @Excel(name = "商品描述-法国")
    private String goodsDescFr;

    /** 商品描述-印度尼西亚 */
    @Excel(name = "商品描述-印度尼西亚")
    private String goodsDescIdn;

    /** 商品描述-日本 */
    @Excel(name = "商品描述-日本")
    private String goodsDescJp;

    /** 商品描述-韩国 */
    @Excel(name = "商品描述-韩国")
    private String goodsDescKo;

    /** 商品描述-马来西亚 */
    @Excel(name = "商品描述-马来西亚")
    private String goodsDescMy;

    /** 商品描述-泰国 */
    @Excel(name = "商品描述-泰国")
    private String goodsDescTh;

    /** 商品描述-越南 */
    @Excel(name = "商品描述-越南")
    private String goodsDescVi;

    /** 商品描述-葡萄牙 */
    @Excel(name = "商品描述-葡萄牙")
    private String goodsDescPt;

    /** 商品描述-俄语 */
    @Excel(name = "商品描述-俄语")
    private String goodsDescRus;

    /** 商品描述-白俄罗斯 */
    @Excel(name = "商品描述-白俄罗斯")
    private String goodsDescBlr;

    /** 商品描述-印度 */
    @Excel(name = "商品描述-印度")
    private String goodsDescIda;

    /** 商品描述-沙特阿拉伯 */
    @Excel(name = "商品描述-沙特阿拉伯")
    private String goodsDescSa;

    /** 商品描述-阿拉伯 */
    @Excel(name = "商品描述-阿拉伯")
    private String goodsDescAr;

    /** 商品描述-意大利 */
    @Excel(name = "商品描述-意大利")
    private String goodsDescIt;

    /** 商品描述-土耳其 */
    @Excel(name = "商品描述-土耳其")
    private String goodsDescTr;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
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
    public void setSellingPrice(BigDecimal sellingPrice) 
    {
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal getSellingPrice() 
    {
        return sellingPrice;
    }
    public void setSystemPrice(BigDecimal systemPrice) 
    {
        this.systemPrice = systemPrice;
    }

    public BigDecimal getSystemPrice() 
    {
        return systemPrice;
    }
    public void setCategoryId(String categoryId) 
    {
        this.categoryId = categoryId;
    }

    public String getCategoryId() 
    {
        return categoryId;
    }
    public void setFreightAmount(BigDecimal freightAmount) 
    {
        this.freightAmount = freightAmount;
    }

    public BigDecimal getFreightAmount() 
    {
        return freightAmount;
    }
    public void setSellerId(String sellerId) 
    {
        this.sellerId = sellerId;
    }

    public String getSellerId() 
    {
        return sellerId;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setDelFlag(Integer delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag() 
    {
        return delFlag;
    }
    public void setGoodsNameEn(String goodsNameEn) 
    {
        this.goodsNameEn = goodsNameEn;
    }

    public String getGoodsNameEn() 
    {
        return goodsNameEn;
    }
    public void setGoodsNameTc(String goodsNameTc) 
    {
        this.goodsNameTc = goodsNameTc;
    }

    public String getGoodsNameTc() 
    {
        return goodsNameTc;
    }
    public void setGoodsNameDe(String goodsNameDe) 
    {
        this.goodsNameDe = goodsNameDe;
    }

    public String getGoodsNameDe() 
    {
        return goodsNameDe;
    }
    public void setGoodsNameEs(String goodsNameEs) 
    {
        this.goodsNameEs = goodsNameEs;
    }

    public String getGoodsNameEs() 
    {
        return goodsNameEs;
    }
    public void setGoodsNameFr(String goodsNameFr) 
    {
        this.goodsNameFr = goodsNameFr;
    }

    public String getGoodsNameFr() 
    {
        return goodsNameFr;
    }
    public void setGoodsNameIdn(String goodsNameIdn) 
    {
        this.goodsNameIdn = goodsNameIdn;
    }

    public String getGoodsNameIdn() 
    {
        return goodsNameIdn;
    }
    public void setGoodsNameJp(String goodsNameJp) 
    {
        this.goodsNameJp = goodsNameJp;
    }

    public String getGoodsNameJp() 
    {
        return goodsNameJp;
    }
    public void setGoodsNameKo(String goodsNameKo) 
    {
        this.goodsNameKo = goodsNameKo;
    }

    public String getGoodsNameKo() 
    {
        return goodsNameKo;
    }
    public void setGoodsNameMy(String goodsNameMy) 
    {
        this.goodsNameMy = goodsNameMy;
    }

    public String getGoodsNameMy() 
    {
        return goodsNameMy;
    }
    public void setGoodsNameTh(String goodsNameTh) 
    {
        this.goodsNameTh = goodsNameTh;
    }

    public String getGoodsNameTh() 
    {
        return goodsNameTh;
    }
    public void setGoodsNameVi(String goodsNameVi) 
    {
        this.goodsNameVi = goodsNameVi;
    }

    public String getGoodsNameVi() 
    {
        return goodsNameVi;
    }
    public void setGoodsNamePt(String goodsNamePt) 
    {
        this.goodsNamePt = goodsNamePt;
    }

    public String getGoodsNamePt() 
    {
        return goodsNamePt;
    }
    public void setGoodsNameRus(String goodsNameRus) 
    {
        this.goodsNameRus = goodsNameRus;
    }

    public String getGoodsNameRus() 
    {
        return goodsNameRus;
    }
    public void setGoodsNameBlr(String goodsNameBlr) 
    {
        this.goodsNameBlr = goodsNameBlr;
    }

    public String getGoodsNameBlr() 
    {
        return goodsNameBlr;
    }
    public void setGoodsNameIda(String goodsNameIda) 
    {
        this.goodsNameIda = goodsNameIda;
    }

    public String getGoodsNameIda() 
    {
        return goodsNameIda;
    }
    public void setGoodsNameSa(String goodsNameSa) 
    {
        this.goodsNameSa = goodsNameSa;
    }

    public String getGoodsNameSa() 
    {
        return goodsNameSa;
    }
    public void setGoodsNameAr(String goodsNameAr) 
    {
        this.goodsNameAr = goodsNameAr;
    }

    public String getGoodsNameAr() 
    {
        return goodsNameAr;
    }
    public void setGoodsNameIt(String goodsNameIt) 
    {
        this.goodsNameIt = goodsNameIt;
    }

    public String getGoodsNameIt() 
    {
        return goodsNameIt;
    }
    public void setGoodsNameTr(String goodsNameTr) 
    {
        this.goodsNameTr = goodsNameTr;
    }

    public String getGoodsNameTr() 
    {
        return goodsNameTr;
    }
    public void setGoodsDescEn(String goodsDescEn) 
    {
        this.goodsDescEn = goodsDescEn;
    }

    public String getGoodsDescEn() 
    {
        return goodsDescEn;
    }
    public void setGoodsDescTc(String goodsDescTc) 
    {
        this.goodsDescTc = goodsDescTc;
    }

    public String getGoodsDescTc() 
    {
        return goodsDescTc;
    }
    public void setGoodsDescDe(String goodsDescDe) 
    {
        this.goodsDescDe = goodsDescDe;
    }

    public String getGoodsDescDe() 
    {
        return goodsDescDe;
    }
    public void setGoodsDescEs(String goodsDescEs) 
    {
        this.goodsDescEs = goodsDescEs;
    }

    public String getGoodsDescEs() 
    {
        return goodsDescEs;
    }
    public void setGoodsDescFr(String goodsDescFr) 
    {
        this.goodsDescFr = goodsDescFr;
    }

    public String getGoodsDescFr() 
    {
        return goodsDescFr;
    }
    public void setGoodsDescIdn(String goodsDescIdn) 
    {
        this.goodsDescIdn = goodsDescIdn;
    }

    public String getGoodsDescIdn() 
    {
        return goodsDescIdn;
    }
    public void setGoodsDescJp(String goodsDescJp) 
    {
        this.goodsDescJp = goodsDescJp;
    }

    public String getGoodsDescJp() 
    {
        return goodsDescJp;
    }
    public void setGoodsDescKo(String goodsDescKo) 
    {
        this.goodsDescKo = goodsDescKo;
    }

    public String getGoodsDescKo() 
    {
        return goodsDescKo;
    }
    public void setGoodsDescMy(String goodsDescMy) 
    {
        this.goodsDescMy = goodsDescMy;
    }

    public String getGoodsDescMy() 
    {
        return goodsDescMy;
    }
    public void setGoodsDescTh(String goodsDescTh) 
    {
        this.goodsDescTh = goodsDescTh;
    }

    public String getGoodsDescTh() 
    {
        return goodsDescTh;
    }
    public void setGoodsDescVi(String goodsDescVi) 
    {
        this.goodsDescVi = goodsDescVi;
    }

    public String getGoodsDescVi() 
    {
        return goodsDescVi;
    }
    public void setGoodsDescPt(String goodsDescPt) 
    {
        this.goodsDescPt = goodsDescPt;
    }

    public String getGoodsDescPt() 
    {
        return goodsDescPt;
    }
    public void setGoodsDescRus(String goodsDescRus) 
    {
        this.goodsDescRus = goodsDescRus;
    }

    public String getGoodsDescRus() 
    {
        return goodsDescRus;
    }
    public void setGoodsDescBlr(String goodsDescBlr) 
    {
        this.goodsDescBlr = goodsDescBlr;
    }

    public String getGoodsDescBlr() 
    {
        return goodsDescBlr;
    }
    public void setGoodsDescIda(String goodsDescIda) 
    {
        this.goodsDescIda = goodsDescIda;
    }

    public String getGoodsDescIda() 
    {
        return goodsDescIda;
    }
    public void setGoodsDescSa(String goodsDescSa) 
    {
        this.goodsDescSa = goodsDescSa;
    }

    public String getGoodsDescSa() 
    {
        return goodsDescSa;
    }
    public void setGoodsDescAr(String goodsDescAr) 
    {
        this.goodsDescAr = goodsDescAr;
    }

    public String getGoodsDescAr() 
    {
        return goodsDescAr;
    }
    public void setGoodsDescIt(String goodsDescIt) 
    {
        this.goodsDescIt = goodsDescIt;
    }

    public String getGoodsDescIt() 
    {
        return goodsDescIt;
    }
    public void setGoodsDescTr(String goodsDescTr) 
    {
        this.goodsDescTr = goodsDescTr;
    }

    public String getGoodsDescTr() 
    {
        return goodsDescTr;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("goodsCode", getGoodsCode())
            .append("goodsName", getGoodsName())
            .append("goodsImg", getGoodsImg())
            .append("goodsDesc", getGoodsDesc())
            .append("sellingPrice", getSellingPrice())
            .append("systemPrice", getSystemPrice())
            .append("categoryId", getCategoryId())
            .append("freightAmount", getFreightAmount())
            .append("sellerId", getSellerId())
            .append("sort", getSort())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("goodsNameEn", getGoodsNameEn())
            .append("goodsNameTc", getGoodsNameTc())
            .append("goodsNameDe", getGoodsNameDe())
            .append("goodsNameEs", getGoodsNameEs())
            .append("goodsNameFr", getGoodsNameFr())
            .append("goodsNameIdn", getGoodsNameIdn())
            .append("goodsNameJp", getGoodsNameJp())
            .append("goodsNameKo", getGoodsNameKo())
            .append("goodsNameMy", getGoodsNameMy())
            .append("goodsNameTh", getGoodsNameTh())
            .append("goodsNameVi", getGoodsNameVi())
            .append("goodsNamePt", getGoodsNamePt())
            .append("goodsNameRus", getGoodsNameRus())
            .append("goodsNameBlr", getGoodsNameBlr())
            .append("goodsNameIda", getGoodsNameIda())
            .append("goodsNameSa", getGoodsNameSa())
            .append("goodsNameAr", getGoodsNameAr())
            .append("goodsNameIt", getGoodsNameIt())
            .append("goodsNameTr", getGoodsNameTr())
            .append("goodsDescEn", getGoodsDescEn())
            .append("goodsDescTc", getGoodsDescTc())
            .append("goodsDescDe", getGoodsDescDe())
            .append("goodsDescEs", getGoodsDescEs())
            .append("goodsDescFr", getGoodsDescFr())
            .append("goodsDescIdn", getGoodsDescIdn())
            .append("goodsDescJp", getGoodsDescJp())
            .append("goodsDescKo", getGoodsDescKo())
            .append("goodsDescMy", getGoodsDescMy())
            .append("goodsDescTh", getGoodsDescTh())
            .append("goodsDescVi", getGoodsDescVi())
            .append("goodsDescPt", getGoodsDescPt())
            .append("goodsDescRus", getGoodsDescRus())
            .append("goodsDescBlr", getGoodsDescBlr())
            .append("goodsDescIda", getGoodsDescIda())
            .append("goodsDescSa", getGoodsDescSa())
            .append("goodsDescAr", getGoodsDescAr())
            .append("goodsDescIt", getGoodsDescIt())
            .append("goodsDescTr", getGoodsDescTr())
            .toString();
    }
}
