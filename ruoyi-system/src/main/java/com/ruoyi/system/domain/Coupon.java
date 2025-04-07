package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 优惠券对象 coupon
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public class Coupon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 优惠券ID */
    private Long couponId;

    /** 门槛金额 */
    @Excel(name = "门槛金额")
    private BigDecimal requireAmount;

    /** 优惠券金额 */
    @Excel(name = "优惠券金额")
    private BigDecimal couponAmount;

    /** 有效期开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "有效期开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date validityStartTime;

    /** 有效期结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "有效期结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date validityEndTime;

    /** 优惠券状态 0：正常 1：违规 2：过期 */
    @Excel(name = "优惠券状态 0：正常 1：违规 2：过期")
    private Integer couponStatus;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 所属商户ID */
    @Excel(name = "所属商户ID")
    private Long sellerId;

    /** 所属商家账号 */
    @Excel(name = "所属商家账号")
    private String sellerAccount;

    public void setCouponId(Long couponId) 
    {
        this.couponId = couponId;
    }

    public Long getCouponId() 
    {
        return couponId;
    }
    public void setRequireAmount(BigDecimal requireAmount) 
    {
        this.requireAmount = requireAmount;
    }

    public BigDecimal getRequireAmount() 
    {
        return requireAmount;
    }
    public void setCouponAmount(BigDecimal couponAmount) 
    {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getCouponAmount() 
    {
        return couponAmount;
    }
    public void setValidityStartTime(Date validityStartTime) 
    {
        this.validityStartTime = validityStartTime;
    }

    public Date getValidityStartTime() 
    {
        return validityStartTime;
    }
    public void setValidityEndTime(Date validityEndTime) 
    {
        this.validityEndTime = validityEndTime;
    }

    public Date getValidityEndTime() 
    {
        return validityEndTime;
    }
    public void setCouponStatus(Integer couponStatus) 
    {
        this.couponStatus = couponStatus;
    }

    public Integer getCouponStatus() 
    {
        return couponStatus;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setSellerId(Long sellerId) 
    {
        this.sellerId = sellerId;
    }

    public Long getSellerId() 
    {
        return sellerId;
    }

    public String getSellerAccount() {
        return sellerAccount;
    }

    public void setSellerAccount(String sellerAccount) {
        this.sellerAccount = sellerAccount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("couponId", getCouponId())
            .append("requireAmount", getRequireAmount())
            .append("couponAmount", getCouponAmount())
            .append("validityStartTime", getValidityStartTime())
            .append("validityEndTime", getValidityEndTime())
            .append("couponStatus", getCouponStatus())
            .append("sort", getSort())
            .append("sellerId", getSellerId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
