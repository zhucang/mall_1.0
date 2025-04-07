package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.core.domain.entity.UserInfoDetailVo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商城售后订单对象 user_after_sale_order
 * 
 * @author ruoyi
 * @date 2025-03-27
 */
public class UserAfterSaleOrder extends UserInfoDetailVo
{
    private static final long serialVersionUID = 1L;

    /** 售后订单ID */
    private Long userAfterSaleOrderId;

    /** 售后订单号 */
    @Excel(name = "售后订单号")
    private String userAfterSaleOrderCode;

    /** 关联用户购物订单ID */
    @Excel(name = "关联用户购物订单ID")
    private Long userShoppingOrderId;

    /** 关联用户购物订单明细ID */
    @Excel(name = "关联用户购物订单明细ID")
    private Long userShoppingOrderDetailId;

    /** 售后金额 */
    @Excel(name = "售后金额")
    private BigDecimal afterSaleAmount;

    /** 商户ID */
    @Excel(name = "商户ID")
    private Long sellerId;

    /** 商户账号 */
    @Excel(name = "商户账号")
    private String sellerAccount;

    /** 售后种类 0：退款退货 1：仅退款 2：换货 */
    @Excel(name = "售后种类 0：退款退货 1：仅退款 2：换货")
    private Integer afterSaleType;

    /** 售后状态 0：待审核 1：售后中 2：已拒绝 3：已退款 4：用户撤销 */
    @Excel(name = "售后状态 0：待审核 1：售后中 2：已拒绝 3：已退款 4：用户撤销")
    private Integer afterSaleStatus;

    /** 售后原因 */
    @Excel(name = "售后原因")
    private String afterSaleReason;

    /** 用户申请售后说明 */
    @Excel(name = "用户申请售后说明")
    private String userInstructions;

    /** 用户售后凭证 */
    @Excel(name = "用户售后凭证")
    private String userAfterSaleImgs;

    /** 拒绝退款原因 */
    @Excel(name = "拒绝退款原因")
    private String rejectRefundReason;

    /** 用户购物订单明细 */
    @Excel(name = "用户购物订单明细")
    private UserShoppingOrderDetail userShoppingOrderDetail;

    public void setUserAfterSaleOrderId(Long userAfterSaleOrderId) 
    {
        this.userAfterSaleOrderId = userAfterSaleOrderId;
    }

    public Long getUserAfterSaleOrderId() 
    {
        return userAfterSaleOrderId;
    }
    public void setUserAfterSaleOrderCode(String userAfterSaleOrderCode) 
    {
        this.userAfterSaleOrderCode = userAfterSaleOrderCode;
    }

    public String getUserAfterSaleOrderCode() 
    {
        return userAfterSaleOrderCode;
    }
    public void setUserShoppingOrderId(Long userShoppingOrderId) 
    {
        this.userShoppingOrderId = userShoppingOrderId;
    }

    public Long getUserShoppingOrderId() 
    {
        return userShoppingOrderId;
    }

    public Long getUserShoppingOrderDetailId() {
        return userShoppingOrderDetailId;
    }

    public void setUserShoppingOrderDetailId(Long userShoppingOrderDetailId) {
        this.userShoppingOrderDetailId = userShoppingOrderDetailId;
    }

    public void setAfterSaleAmount(BigDecimal afterSaleAmount)
    {
        this.afterSaleAmount = afterSaleAmount;
    }

    public BigDecimal getAfterSaleAmount() 
    {
        return afterSaleAmount;
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

    public void setAfterSaleType(Integer afterSaleType)
    {
        this.afterSaleType = afterSaleType;
    }

    public Integer getAfterSaleType() 
    {
        return afterSaleType;
    }
    public void setAfterSaleStatus(Integer afterSaleStatus) 
    {
        this.afterSaleStatus = afterSaleStatus;
    }

    public Integer getAfterSaleStatus() 
    {
        return afterSaleStatus;
    }
    public void setAfterSaleReason(String afterSaleReason) 
    {
        this.afterSaleReason = afterSaleReason;
    }

    public String getAfterSaleReason() 
    {
        return afterSaleReason;
    }
    public void setUserInstructions(String userInstructions) 
    {
        this.userInstructions = userInstructions;
    }

    public String getUserInstructions() 
    {
        return userInstructions;
    }
    public void setUserAfterSaleImgs(String userAfterSaleImgs) 
    {
        this.userAfterSaleImgs = userAfterSaleImgs;
    }

    public String getUserAfterSaleImgs() 
    {
        return userAfterSaleImgs;
    }
    public void setRejectRefundReason(String rejectRefundReason) 
    {
        this.rejectRefundReason = rejectRefundReason;
    }

    public String getRejectRefundReason() 
    {
        return rejectRefundReason;
    }

    public UserShoppingOrderDetail getUserShoppingOrderDetail() {
        return userShoppingOrderDetail;
    }

    public void setUserShoppingOrderDetail(UserShoppingOrderDetail userShoppingOrderDetail) {
        this.userShoppingOrderDetail = userShoppingOrderDetail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userAfterSaleOrderId", getUserAfterSaleOrderId())
            .append("userAfterSaleOrderCode", getUserAfterSaleOrderCode())
            .append("userShoppingOrderId", getUserShoppingOrderId())
            .append("afterSaleAmount", getAfterSaleAmount())
            .append("afterSaleType", getAfterSaleType())
            .append("afterSaleStatus", getAfterSaleStatus())
            .append("afterSaleReason", getAfterSaleReason())
            .append("userInstructions", getUserInstructions())
            .append("userAfterSaleImgs", getUserAfterSaleImgs())
            .append("rejectRefundReason", getRejectRefundReason())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
