package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 属性类型对象 attr_type
 * 
 * @author ruoyi
 * @date 2024-12-20
 */
public class AttrType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 属性ID */
    private Long id;

    /** 属性名称 */
    @Excel(name = "属性类型名称")
    private String attrTypeName;

    /** 属性名称多语言 */
    @Excel(name = "属性类型名称多语言")
    private LangMgr attrTypeNameLang;

    /** 系统商品ID */
    @Excel(name = "系统商品ID")
    private Long systemGoodsInfoId;

    /** 商品编码 */
    @Excel(name = "商品编码")
    private String goodsCode;

    /** 状态 0：禁用 1：启用 */
    @Excel(name = "状态 0：禁用 1：启用")
    private Integer status;

    /** 删除标志 0：正常 1：删除 */
    private Integer delFlag;

    /** 属性值 */
    @Excel(name = "属性值")
    private List<AttrValue> attrValues;


    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setAttrTypeName(String attrTypeName) 
    {
        this.attrTypeName = attrTypeName;
    }

    public String getAttrTypeName() 
    {
        return attrTypeName;
    }

    public LangMgr getAttrTypeNameLang() {
        return attrTypeNameLang;
    }

    public void setAttrTypeNameLang(LangMgr attrTypeNameLang) {
        this.attrTypeNameLang = attrTypeNameLang;
    }

    public void setSystemGoodsInfoId(Long systemGoodsInfoId)
    {
        this.systemGoodsInfoId = systemGoodsInfoId;
    }

    public Long getSystemGoodsInfoId() 
    {
        return systemGoodsInfoId;
    }
    public void setGoodsCode(String goodsCode) 
    {
        this.goodsCode = goodsCode;
    }

    public String getGoodsCode() 
    {
        return goodsCode;
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

    public List<AttrValue> getAttrValues() {
        return attrValues;
    }

    public void setAttrValues(List<AttrValue> attrValues) {
        this.attrValues = attrValues;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("attrTypeName", getAttrTypeName())
            .append("systemGoodsInfoId", getSystemGoodsInfoId())
            .append("goodsCode", getGoodsCode())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
