package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商户入驻申请记录对象 seller_account_apply_record
 * 
 * @author ruoyi
 * @date 2025-01-03
 */
public class SellerAccountApplyRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商户入驻申请ID */
    private Long id;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String email;

    /** 登陆密码 */
    @Excel(name = "登陆密码")
    private String userPwd;

    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String realName;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String idCard;

    /** 证件图片（正面） */
    @Excel(name = "证件图片", readConverterExp = "正=面")
    private String img1Key;

    /** 证件图片（背面） */
    @Excel(name = "证件图片", readConverterExp = "背=面")
    private String img2Key;

    /** 证件图片（手持） */
    @Excel(name = "证件图片", readConverterExp = "手=持")
    private String img3Key;

    /** 国家名称 */
    @Excel(name = "国家名称")
    private String countryName;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String shopName;

    /** 店铺图片 */
    @Excel(name = "店铺图片")
    private String shopImg;

    /** 申请ip */
    @Excel(name = "申请ip")
    private String applyIp;

    /** 申请地址 */
    @Excel(name = "申请地址")
    private String applyAddress;

    /** 审核状态 ： 0：待审核 1：已通过 2：已驳回 */
    @Excel(name = "审核状态 ： 0：待审核 1：已通过 2：已驳回")
    private Integer status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setUserPwd(String userPwd) 
    {
        this.userPwd = userPwd;
    }

    public String getUserPwd() 
    {
        return userPwd;
    }
    public void setRealName(String realName) 
    {
        this.realName = realName;
    }

    public String getRealName() 
    {
        return realName;
    }
    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }
    public void setImg1Key(String img1Key) 
    {
        this.img1Key = img1Key;
    }

    public String getImg1Key() 
    {
        return img1Key;
    }
    public void setImg2Key(String img2Key) 
    {
        this.img2Key = img2Key;
    }

    public String getImg2Key() 
    {
        return img2Key;
    }
    public void setImg3Key(String img3Key) 
    {
        this.img3Key = img3Key;
    }

    public String getImg3Key() 
    {
        return img3Key;
    }
    public void setCountryName(String countryName) 
    {
        this.countryName = countryName;
    }

    public String getCountryName() 
    {
        return countryName;
    }
    public void setShopName(String shopName) 
    {
        this.shopName = shopName;
    }

    public String getShopName() 
    {
        return shopName;
    }
    public void setShopImg(String shopImg) 
    {
        this.shopImg = shopImg;
    }

    public String getShopImg() 
    {
        return shopImg;
    }
    public void setApplyIp(String applyIp) 
    {
        this.applyIp = applyIp;
    }

    public String getApplyIp() 
    {
        return applyIp;
    }
    public void setApplyAddress(String applyAddress) 
    {
        this.applyAddress = applyAddress;
    }

    public String getApplyAddress() 
    {
        return applyAddress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("userPwd", getUserPwd())
            .append("realName", getRealName())
            .append("idCard", getIdCard())
            .append("img1Key", getImg1Key())
            .append("img2Key", getImg2Key())
            .append("img3Key", getImg3Key())
            .append("countryName", getCountryName())
            .append("shopName", getShopName())
            .append("shopImg", getShopImg())
            .append("applyIp", getApplyIp())
            .append("applyAddress", getApplyAddress())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
