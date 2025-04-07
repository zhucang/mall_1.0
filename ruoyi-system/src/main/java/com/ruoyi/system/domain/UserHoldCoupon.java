package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户持有优惠券对象 user_hold_coupon
 * 
 * @author ruoyi
 * @date 2025-03-28
 */
public class UserHoldCoupon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户持有优惠券ID */
    private Long userHoldCouponId;

    /** 优惠券ID */
    @Excel(name = "优惠券ID")
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

    /** 所属商家ID */
    @Excel(name = "所属商家ID")
    private Long sellerId;

    /** 所属商家账号 */
    @Excel(name = "所属商家账号")
    private String sellerAccount;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 使用状态 0：未使用 1：已使用 */
    @Excel(name = "使用状态 0：未使用 1：已使用")
    private Integer useStatus;

    public void setUserHoldCouponId(Long userHoldCouponId) 
    {
        this.userHoldCouponId = userHoldCouponId;
    }

    public Long getUserHoldCouponId() 
    {
        return userHoldCouponId;
    }
    public void setCouponId(Long couponId) 
    {
        this.couponId = couponId;
    }

    public Long getCouponId() 
    {
        return couponId;
    }

    public BigDecimal getRequireAmount() {
        return requireAmount;
    }

    public void setRequireAmount(BigDecimal requireAmount) {
        this.requireAmount = requireAmount;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public Date getValidityStartTime() {
        return validityStartTime;
    }

    public void setValidityStartTime(Date validityStartTime) {
        this.validityStartTime = validityStartTime;
    }

    public Date getValidityEndTime() {
        return validityEndTime;
    }

    public void setValidityEndTime(Date validityEndTime) {
        this.validityEndTime = validityEndTime;
    }

    public Integer getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(Integer couponStatus) {
        this.couponStatus = couponStatus;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerAccount() {
        return sellerAccount;
    }

    public void setSellerAccount(String sellerAccount) {
        this.sellerAccount = sellerAccount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUseStatus(Integer useStatus)
    {
        this.useStatus = useStatus;
    }

    public Integer getUseStatus() 
    {
        return useStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userHoldCouponId", getUserHoldCouponId())
            .append("couponId", getCouponId())
            .append("useStatus", getUseStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
