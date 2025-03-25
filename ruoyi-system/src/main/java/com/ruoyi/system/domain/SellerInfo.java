package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商户信息对象 seller_info
 * 
 * @author ruoyi
 * @date 2024-12-31
 */
public class SellerInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商户ID */
    private Long id;

    /** 商户账号 */
    @Excel(name = "商户账号")
    private String sellerAccount;

    /** 登陆密码 */
    @Excel(name = "登陆密码")
    private String loginPassword;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSellerAccount(String sellerAccount) 
    {
        this.sellerAccount = sellerAccount;
    }

    public String getSellerAccount() 
    {
        return sellerAccount;
    }
    public void setLoginPassword(String loginPassword) 
    {
        this.loginPassword = loginPassword;
    }

    public String getLoginPassword() 
    {
        return loginPassword;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sellerAccount", getSellerAccount())
            .append("loginPassword", getLoginPassword())
            .toString();
    }
}
