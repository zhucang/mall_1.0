package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 属性值对象 attr_value
 * 
 * @author ruoyi
 * @date 2024-12-20
 */
public class AttrValue extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 属性值ID */
    private Long id;

    /** 属性值名称 */
    @Excel(name = "属性值名称")
    private String attrValueName;

    /** 属性值名称多语言 */
    @Excel(name = "属性值名称多语言")
    private LangMgr attrValueNameLang;

    /** 属性图片 */
    @Excel(name = "属性图片")
    private String attrImg;

    /** 属性图片展示标志 0：不展示 1：展示 */
    @Excel(name = "属性图片展示标志 0：不展示 1：展示")
    private Integer attrImgVisibleFlag;

    /** 商品编码 */
    @Excel(name = "商品编码")
    private String goodsCode;

    /** 属性类型ID */
    @Excel(name = "属性类型ID")
    private String attrTypeId;

    /** 状态 0：禁用 1：启用 */
    @Excel(name = "状态 0：禁用 1：启用")
    private Integer status;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setAttrValueName(String attrValueName) 
    {
        this.attrValueName = attrValueName;
    }

    public String getAttrValueName() 
    {
        return attrValueName;
    }

    public LangMgr getAttrValueNameLang() {
        return attrValueNameLang;
    }

    public void setAttrValueNameLang(LangMgr attrValueNameLang) {
        this.attrValueNameLang = attrValueNameLang;
    }

    public void setAttrImg(String attrImg)
    {
        this.attrImg = attrImg;
    }

    public String getAttrImg() 
    {
        return attrImg;
    }
    public void setAttrImgVisibleFlag(Integer attrImgVisibleFlag) 
    {
        this.attrImgVisibleFlag = attrImgVisibleFlag;
    }

    public Integer getAttrImgVisibleFlag() 
    {
        return attrImgVisibleFlag;
    }
    public void setGoodsCode(String goodsCode) 
    {
        this.goodsCode = goodsCode;
    }

    public String getGoodsCode() 
    {
        return goodsCode;
    }
    public void setAttrTypeId(String attrTypeId) 
    {
        this.attrTypeId = attrTypeId;
    }

    public String getAttrTypeId() 
    {
        return attrTypeId;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("attrValueName", getAttrValueName())
            .append("attrImg", getAttrImg())
            .append("attrImgVisibleFlag", getAttrImgVisibleFlag())
            .append("goodsCode", getGoodsCode())
            .append("attrTypeId", getAttrTypeId())
            .append("status", getStatus())
            .toString();
    }
}
