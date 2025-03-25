package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品分类信息对象 category
 * 
 * @author ruoyi
 * @date 2024-12-03
 */
public class Category extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String categoryName;

    /** 分类名称多语言 */
    @Excel(name = "分类名称多语言")
    private LangMgr categoryNameLang;

    /** 分类图标 */
    @Excel(name = "分类图标")
    private String categoryImg;

    /** 分类等级 */
    @Excel(name = "分类等级")
    private Integer categoryLevel;

    /** 父分类ID */
    @Excel(name = "父分类ID")
    private Long parentId;

    /** 显示标志 0：隐藏 1：显示 */
    @Excel(name = "显示标志 0：隐藏 1：显示")
    private Integer visibleFlag;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

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
    public void setCategoryName(String categoryName) 
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName() 
    {
        return categoryName;
    }

    public LangMgr getCategoryNameLang() {
        if (categoryNameLang == null){
            categoryNameLang = new LangMgr();
        }
        return categoryNameLang;
    }

    public void setCategoryNameLang(LangMgr categoryNameLang) {
        this.categoryNameLang = categoryNameLang;
    }

    public void setCategoryImg(String categoryImg)
    {
        this.categoryImg = categoryImg;
    }

    public String getCategoryImg() 
    {
        return categoryImg;
    }
    public void setCategoryLevel(Integer categoryLevel) 
    {
        this.categoryLevel = categoryLevel;
    }

    public Integer getCategoryLevel() 
    {
        return categoryLevel;
    }
    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public Integer getVisibleFlag() {
        return visibleFlag;
    }

    public void setVisibleFlag(Integer visibleFlag) {
        this.visibleFlag = visibleFlag;
    }

    public void setSort(Integer sort)
    {
        this.sort = sort;
    }

    public Integer getSort() 
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("categoryName", getCategoryName())
            .append("categoryImg", getCategoryImg())
            .append("categoryLevel", getCategoryLevel())
            .append("parentId", getParentId())
            .append("sort", getSort())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
