package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.entity.UserInfoDetailVo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用户购物订单对象 user_shopping_order
 * 
 * @author ruoyi
 * @date 2024-12-24
 */
public class UserShoppingOrder extends UserInfoDetailVo
{
    private static final long serialVersionUID = 1L;

    /** 用户购物订单ID */
    private Long id;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderCode;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 订单金额 */
    @Excel(name = "订单金额")
    private BigDecimal orderPrice;

    /** 订单系统总金额 */
    @Excel(name = "订单系统总金额")
    private BigDecimal orderSystemPrice;

    /** 运费金额 */
    @Excel(name = "运费金额")
    private BigDecimal freightAmount;

    /** 快递单号 */
    private String trackingNumber;

    /** 收货地址 */
    @Excel(name = "收货地址")
    private String shippingAddress;

    /** 订单状态 0：待付款 1：待发货 2：待收货 3：已取消 4：退款/售后 5：待评价 6：交易成功 */
    @Excel(name = "订单状态 0：待付款 1：待发货 2：待收货 3：已取消 4：退款/售后 5：待评价 6：交易成功")
    private Integer orderStatus;

    /** 退款状态 0：未退款 1：申请中 2：退款成功 */
    @Excel(name = "退款状态 0：未退款 1：申请中 2：退款成功 3：退款失败")
    private Integer refundStatus;

    /** 是否提交到平台 0：否 1：是 */
    @Excel(name = "是否提交到平台 0：否 1：是")
    private Integer submitPlatformFlag;

    /** 店铺Id */
    @Excel(name = "店铺Id")
    private Long shopInfoId;

    /** 卖家ID */
    @Excel(name = "卖家ID")
    private Long sellerId;

    /** 订单付款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss HH:mm:ss")
    @Excel(name = "订单付款时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    /** 订单发货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "订单发货时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date shippingTime;

    /** 订单完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "订单完成时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date finishTime;

    /** 用户购物订单明细 */
    @Excel(name = "用户购物订单明细")
    private List<UserShoppingOrderDetail> userShoppingOrderDetails;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderCode(String orderCode) 
    {
        this.orderCode = orderCode;
    }

    public String getOrderCode() 
    {
        return orderCode;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setOrderPrice(BigDecimal orderPrice) 
    {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getOrderPrice() 
    {
        return orderPrice;
    }

    public BigDecimal getOrderSystemPrice() {
        return orderSystemPrice;
    }

    public void setOrderSystemPrice(BigDecimal orderSystemPrice) {
        this.orderSystemPrice = orderSystemPrice;
    }

    public void setFreightAmount(BigDecimal freightAmount)
    {
        this.freightAmount = freightAmount;
    }

    public BigDecimal getFreightAmount() 
    {
        return freightAmount;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public void setShippingAddress(String shippingAddress)
    {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingAddress() 
    {
        return shippingAddress;
    }
    public void setOrderStatus(Integer orderStatus) 
    {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderStatus() 
    {
        return orderStatus;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Integer getSubmitPlatformFlag() {
        return submitPlatformFlag;
    }

    public void setSubmitPlatformFlag(Integer submitPlatformFlag) {
        this.submitPlatformFlag = submitPlatformFlag;
    }

    public Long getShopInfoId() {
        return shopInfoId;
    }

    public void setShopInfoId(Long shopInfoId) {
        this.shopInfoId = shopInfoId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public void setPayTime(Date payTime)
    {
        this.payTime = payTime;
    }

    public Date getPayTime() 
    {
        return payTime;
    }
    public void setShippingTime(Date shippingTime) 
    {
        this.shippingTime = shippingTime;
    }

    public Date getShippingTime() 
    {
        return shippingTime;
    }
    public void setFinishTime(Date finishTime) 
    {
        this.finishTime = finishTime;
    }

    public Date getFinishTime() 
    {
        return finishTime;
    }

    public List<UserShoppingOrderDetail> getUserShoppingOrderDetails() {
        return userShoppingOrderDetails;
    }

    public void setUserShoppingOrderDetails(List<UserShoppingOrderDetail> userShoppingOrderDetails) {
        this.userShoppingOrderDetails = userShoppingOrderDetails;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderCode", getOrderCode())
            .append("userId", getUserId())
            .append("orderPrice", getOrderPrice())
            .append("freightAmount", getFreightAmount())
            .append("shippingAddress", getShippingAddress())
            .append("orderStatus", getOrderStatus())
            .append("payTime", getPayTime())
            .append("shippingTime", getShippingTime())
            .append("finishTime", getFinishTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
