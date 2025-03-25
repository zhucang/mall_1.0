package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户收货地址对象 user_receive_address
 * 
 * @author ruoyi
 * @date 2024-12-27
 */
public class UserReceiveAddress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户收货地址ID */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 收货人名称 */
    @Excel(name = "收货人名称")
    private String receiverName;

    /** 收货人电话 */
    @Excel(name = "收货人电话")
    private String receiverPhone;

    /** 国家名称 */
    @Excel(name = "国家名称")
    private String countryName;

    /** 收货省份 */
    @Excel(name = "收货省份")
    private String receiverProvince;

    /** 收货城市 */
    @Excel(name = "收货城市")
    private String receiverCity;

    /** 收货详细地址 */
    @Excel(name = "收货详细地址")
    private String receiverAddress;

    /** 是否默认 0：否 1:是 */
    @Excel(name = "是否默认 0：否 1:是")
    private Integer defaultFlag;


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
    public void setReceiverName(String receiverName) 
    {
        this.receiverName = receiverName;
    }

    public String getReceiverName() 
    {
        return receiverName;
    }
    public void setReceiverPhone(String receiverPhone) 
    {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverPhone() 
    {
        return receiverPhone;
    }
    public void setReceiverProvince(String receiverProvince) 
    {
        this.receiverProvince = receiverProvince;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    public String getReceiverProvince()
    {
        return receiverProvince;
    }
    public void setReceiverCity(String receiverCity) 
    {
        this.receiverCity = receiverCity;
    }

    public String getReceiverCity() 
    {
        return receiverCity;
    }
    public void setReceiverAddress(String receiverAddress) 
    {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverAddress() 
    {
        return receiverAddress;
    }
    public void setDefaultFlag(Integer defaultFlag) 
    {
        this.defaultFlag = defaultFlag;
    }

    public Integer getDefaultFlag() 
    {
        return defaultFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("receiverName", getReceiverName())
            .append("receiverPhone", getReceiverPhone())
            .append("receiverProvince", getReceiverProvince())
            .append("receiverCity", getReceiverCity())
            .append("receiverAddress", getReceiverAddress())
            .append("defaultFlag", getDefaultFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
