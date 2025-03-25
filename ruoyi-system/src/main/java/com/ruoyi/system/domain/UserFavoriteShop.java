package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户店铺收藏对象 user_favorite_shop
 * 
 * @author ruoyi
 * @date 2024-12-24
 */
public class UserFavoriteShop extends ShopInfo
{
    private static final long serialVersionUID = 1L;

    /** 商品收藏ID */
    private Long id;

    /** 店铺ID */
    @Excel(name = "店铺ID")
    private Long shopInfoId;

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
    public void setShopInfoId(Long shopInfoId) 
    {
        this.shopInfoId = shopInfoId;
    }

    public Long getShopInfoId() 
    {
        return shopInfoId;
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
            .append("shopInfoId", getShopInfoId())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
