package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商品SKU和属性值关联对象 sku_attr_value
 * 
 * @author ruoyi
 * @date 2024-12-26
 */
public class SkuAttrValue extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品SKU ID */
    private Long skuId;

    /** 属性类型 */
    private Long attrTypeId;

    /** 属性名称 */
    @Excel(name = "属性类型名称")
    private String attrTypeName;

    /** 属性名称多语言 */
    @Excel(name = "属性类型名称多语言")
    private LangMgr attrTypeNameLang;

    /** 属性值ID */
    private Long attrValueId;

    /** 属性值名称 */
    @Excel(name = "属性值名称")
    private String attrValueName;

    /** 属性值名称多语言 */
    @Excel(name = "属性值名称多语言")
    private LangMgr attrValueNameLang;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getAttrTypeId() {
        return attrTypeId;
    }

    public void setAttrTypeId(Long attrTypeId) {
        this.attrTypeId = attrTypeId;
    }

    public String getAttrTypeName() {
        return attrTypeName;
    }

    public void setAttrTypeName(String attrTypeName) {
        this.attrTypeName = attrTypeName;
    }

    public LangMgr getAttrTypeNameLang() {
        return attrTypeNameLang;
    }

    public void setAttrTypeNameLang(LangMgr attrTypeNameLang) {
        this.attrTypeNameLang = attrTypeNameLang;
    }

    public Long getAttrValueId() {
        return attrValueId;
    }

    public void setAttrValueId(Long attrValueId) {
        this.attrValueId = attrValueId;
    }

    public String getAttrValueName() {
        return attrValueName;
    }

    public void setAttrValueName(String attrValueName) {
        this.attrValueName = attrValueName;
    }

    public LangMgr getAttrValueNameLang() {
        return attrValueNameLang;
    }

    public void setAttrValueNameLang(LangMgr attrValueNameLang) {
        this.attrValueNameLang = attrValueNameLang;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("skuId", getSkuId())
            .append("attrValueId", getAttrValueId())
            .toString();
    }
}
