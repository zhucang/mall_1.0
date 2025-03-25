package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户商品收藏对象 user_favorite_goods
 * 
 * @author ruoyi
 * @date 2024-12-24
 */
public class UserFavoriteGoods extends SellingGoodsInfo
{
    private static final long serialVersionUID = 1L;

    /** 商品收藏ID */
    private Long id;

    /** 在售商品ID */
    @Excel(name = "在售商品ID")
    private Long sellingGoodsInfoId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSellingGoodsInfoId(Long sellingGoodsInfoId) 
    {
        this.sellingGoodsInfoId = sellingGoodsInfoId;
    }

    public Long getSellingGoodsInfoId() 
    {
        return sellingGoodsInfoId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sellingGoodsInfoId", getSellingGoodsInfoId())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
