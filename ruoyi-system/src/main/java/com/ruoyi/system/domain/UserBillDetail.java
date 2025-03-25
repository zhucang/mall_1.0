package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.entity.UserInfoDetailVo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户流水记录对象 user_bill_detail
 * 
 * @author ruoyi
 * @date 2023-10-29
 */
public class UserBillDetail extends UserInfoDetailVo
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 流水金额 */
    @Excel(name = "流水金额")
    private BigDecimal orderAmount;

    /** 流水标题 */
    @Excel(name = "流水标题")
    private String deType;

    /** 流水内容 */
    @Excel(name = "流水内容")
    private String deSummary;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderTime;

    /** 变更前总资金 */
    @Excel(name = "变更前总资金")
    private BigDecimal amountBefore;

    /** 变更后总资金 */
    @Excel(name = "变更后总资金")
    private BigDecimal amountAfter;

    /** 相关订单id */
    @Excel(name = "相关订单id")
    private Long relateOrderId;

    /** 相关信息 */
    @Excel(name = "相关信息")
    private String relateInfo;

    /** 订单类型 */
    @Excel(name = "订单类型： 0：充值 1：提现 2：上分 3:下分 10：下级返佣 11：资金互转扣除 12：资金互转收入 13：用户取消出金返还 14：提现驳回资金返还 16：资金兑换手续费扣除 17：理财产品购买扣除 18：理财产品日收益结算 19：理财产品本金退回 20：理财产品提前赎回违约金 40：彩金赠送（系统充值） 41：彩金赠送（福利彩金） 42：彩金回收 53：冻结资金 54：解冻资金 55：充值赠送彩金 56：注册赠送彩金  61:理财产品期满收益结算 62：理财订单驳回本金返还 63：贷款收入 64：贷款还款 65：贷款每日利息扣除 66:提现手续费扣除 72：首次充值赠送彩金 73：用户商城购物下单 74：用户购物订单退款 75：商户提交订单扣除 76：订单完成商户提交本金返还 77：订单完成商户利润收入")
    private Integer orderClass;

    /** 货币名称 */
    @Excel(name = "货币名称")
    private Long currencyId;

    /** 币种名称 */
    private String currencyName;

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setOrderAmount(BigDecimal orderAmount) 
    {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getOrderAmount() 
    {
        return orderAmount;
    }
    public void setDeType(String deType) 
    {
        this.deType = deType;
    }

    public String getDeType() 
    {
        return deType;
    }
    public void setDeSummary(String deSummary) 
    {
        this.deSummary = deSummary;
    }

    public String getDeSummary() 
    {
        return deSummary;
    }
    public void setOrderTime(Date orderTime) 
    {
        this.orderTime = orderTime;
    }

    public Date getOrderTime() 
    {
        return orderTime;
    }
    public void setAmountBefore(BigDecimal amountBefore) 
    {
        this.amountBefore = amountBefore;
    }

    public BigDecimal getAmountBefore() 
    {
        return amountBefore;
    }
    public void setAmountAfter(BigDecimal amountAfter) 
    {
        this.amountAfter = amountAfter;
    }

    public BigDecimal getAmountAfter() 
    {
        return amountAfter;
    }
    public void setRelateOrderId(Long relateOrderId) 
    {
        this.relateOrderId = relateOrderId;
    }

    public Long getRelateOrderId() 
    {
        return relateOrderId;
    }

    public String getRelateInfo() {
        return relateInfo;
    }

    public void setRelateInfo(String relateInfo) {
        this.relateInfo = relateInfo;
    }

    public Integer getOrderClass() {
        return orderClass;
    }

    public void setOrderClass(Integer orderClass) {
        this.orderClass = orderClass;
    }

    public void setCurrencyId(Long currencyId)
    {
        this.currencyId = currencyId;
    }

    public Long getCurrencyId() 
    {
        return currencyId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("orderAmount", getOrderAmount())
            .append("deType", getDeType())
            .append("deSummary", getDeSummary())
            .append("orderTime", getOrderTime())
            .append("amountBefore", getAmountBefore())
            .append("amountAfter", getAmountAfter())
            .append("relateOrderId", getRelateOrderId())
            .append("orderClass", getOrderClass())
            .append("currencyId", getCurrencyId())
            .append("remark", getRemark())
            .toString();
    }
}
