package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.HintConstants;
import com.ruoyi.common.core.domain.entity.UserInfo;
import com.ruoyi.common.exception.LangException;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.CodeUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.system.domain.SellerAccountApplyRecord;
import com.ruoyi.system.domain.ShopInfo;
import com.ruoyi.system.mapper.SellerAccountApplyRecordMapper;
import com.ruoyi.system.mapper.UserInfoMapper;
import com.ruoyi.system.service.ISellerAccountApplyRecordService;
import com.ruoyi.system.service.IShopInfoService;
import com.ruoyi.system.utils.cache.CacheUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商户入驻申请记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-01-03
 */
@Service
public class SellerAccountApplyRecordServiceImpl implements ISellerAccountApplyRecordService 
{
    @Resource
    private SellerAccountApplyRecordMapper sellerAccountApplyRecordMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private IShopInfoService shopInfoService;

    /**
     * 查询商户入驻申请记录
     * 
     * @param id 商户入驻申请记录主键
     * @return 商户入驻申请记录
     */
    @Override
    public SellerAccountApplyRecord selectSellerAccountApplyRecordById(Long id)
    {
        return sellerAccountApplyRecordMapper.selectSellerAccountApplyRecordById(id);
    }

    /**
     * 查询商户入驻申请记录列表
     * 
     * @param sellerAccountApplyRecord 商户入驻申请记录
     * @return 商户入驻申请记录
     */
    @Override
    public List<SellerAccountApplyRecord> selectSellerAccountApplyRecordList(SellerAccountApplyRecord sellerAccountApplyRecord)
    {
        return sellerAccountApplyRecordMapper.selectSellerAccountApplyRecordList(sellerAccountApplyRecord);
    }

    /**
     * 新增商户入驻申请记录
     * 
     * @param sellerAccountApplyRecord 商户入驻申请记录
     * @return 结果
     */
    @Override
    public int insertSellerAccountApplyRecord(SellerAccountApplyRecord sellerAccountApplyRecord)
    {
        sellerAccountApplyRecord.setCreateTime(DateUtils.getNowDate());
        return sellerAccountApplyRecordMapper.insertSellerAccountApplyRecord(sellerAccountApplyRecord);
    }

    /**
     * 修改商户入驻申请记录
     * 
     * @param sellerAccountApplyRecord 商户入驻申请记录
     * @return 结果
     */
    @Override
    public int updateSellerAccountApplyRecord(SellerAccountApplyRecord sellerAccountApplyRecord)
    {
        sellerAccountApplyRecord.setUpdateTime(DateUtils.getNowDate());
        return sellerAccountApplyRecordMapper.updateSellerAccountApplyRecord(sellerAccountApplyRecord);
    }

    /**
     * 批量删除商户入驻申请记录
     * 
     * @param ids 需要删除的商户入驻申请记录主键
     * @return 结果
     */
    @Override
    public int deleteSellerAccountApplyRecordByIds(Long[] ids)
    {
        return sellerAccountApplyRecordMapper.deleteSellerAccountApplyRecordByIds(ids);
    }

    /**
     * 删除商户入驻申请记录信息
     * 
     * @param id 商户入驻申请记录主键
     * @return 结果
     */
    @Override
    public int deleteSellerAccountApplyRecordById(Long id)
    {
        return sellerAccountApplyRecordMapper.deleteSellerAccountApplyRecordById(id);
    }

    /**
     * 商户入驻申请审核
     *
     * @param sellerAccountApplyRecord 商户入驻申请记录
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int reviewSellerAccountApply(SellerAccountApplyRecord sellerAccountApplyRecord){
        //申请记录ID
        Long id = sellerAccountApplyRecord.getId();
        if (id == null){
            throw new ServiceException("请选择需要审核的申请记录");
        }
        //审核状态
        Integer status = sellerAccountApplyRecord.getStatus();
        //申请记录信息
        sellerAccountApplyRecord = sellerAccountApplyRecordMapper.selectSellerAccountApplyRecordById(id);
        //检验审核状态
        if (!sellerAccountApplyRecord.getStatus().equals(0)){
            throw new ServiceException("此申请记录已经审核过");
        }
        //如果审核通过
        if (status.equals(1)){
            sellerAccountApplyRecord.setStatus(1);
            //添加商户账号
            //商户邮箱
            String email = sellerAccountApplyRecord.getEmail();
            UserInfo userInfo = new UserInfo();
            userInfo.setUserAccount(email);
            userInfo.setPhone(sellerAccountApplyRecord.getPhone());
            userInfo.setEmail(email);
            userInfo.setNickName(email);
            userInfo.setRealName(sellerAccountApplyRecord.getRealName());
            userInfo.setUserPwd(SecurityUtils.encryptPassword(sellerAccountApplyRecord.getUserPwd()));
            //生成邀请码
            String inviteCode;
            while (true){
                inviteCode = "2"+ CodeUtils.generateInviteCode();
                UserInfo byInviteCode = userInfoMapper.selectUserInfoByInviteCode(inviteCode);
                if (byInviteCode == null){
                    break;
                }
            }
            userInfo.setInviteCode(inviteCode);
            userInfo.setAccountType(0);
            userInfo.setAvatar(sellerAccountApplyRecord.getShopImg());
            userInfo.setRegTime(new Date());
            userInfo.setRegIp(sellerAccountApplyRecord.getApplyIp());
            userInfo.setRegAddress(sellerAccountApplyRecord.getApplyAddress());
            userInfo.setIdCard(sellerAccountApplyRecord.getIdCard());
            userInfo.setImg1Key(sellerAccountApplyRecord.getImg1Key());
            userInfo.setImg2Key(sellerAccountApplyRecord.getImg2Key());
            userInfo.setImg3Key(sellerAccountApplyRecord.getImg3Key());
            userInfo.setAuthStatusSenior(2);
            userInfo.setAuthMethod(0);
            userInfo.setVipLevel(CacheUtils.getOtherValueByKey("new_user_default_vip_level",Integer.class));
            userInfo.setSellerFlag(1);
            int insertUserInfo = userInfoMapper.insertUserInfo(userInfo);
            if (insertUserInfo == 0){
                throw new ServiceException("新增商户信息异常");
            }
            //新增关联店铺
            ShopInfo shopInfo = new ShopInfo();
            shopInfo.setShopName(sellerAccountApplyRecord.getShopName());
            shopInfo.setShopImg(sellerAccountApplyRecord.getShopImg());
            shopInfo.setSellerId(userInfo.getId());
            shopInfo.setCreateTime(new Date());
            int insertShopInfo = shopInfoService.insertShopInfo(shopInfo);
            if (insertShopInfo == 0){
                throw new ServiceException("新增商户信息异常");
            }
        }else if (status.equals(2)){
            //如果审核驳回
            sellerAccountApplyRecord.setStatus(2);
        }else {
            throw new ServiceException("请选择正确的审核状态");
        }
        sellerAccountApplyRecord.setUpdateBy(SecurityUtils.getUsername());
        sellerAccountApplyRecord.setUpdateTime(new Date());
        int updateSellerAccountApplyRecord = sellerAccountApplyRecordMapper.updateSellerAccountApplyRecord(sellerAccountApplyRecord);
        if (updateSellerAccountApplyRecord == 0){
            throw new ServiceException("更新申请记录信息异常");
        }
        return 1;
    }


    /**
     * 发起商户入驻申请
     *
     * @param sellerAccountApplyRecord 商户入驻申请记录
     * @return 结果
     */
    @Override
    public int InitiateApply(SellerAccountApplyRecord sellerAccountApplyRecord){
        //邮箱地址
        String email = sellerAccountApplyRecord.getEmail();
        if (StringUtils.isEmpty(email)){
            throw new LangException(HintConstants.PARAM_NULL,"请输入邮箱地址");
        }else {
            //验证邮箱格式
            if (!email.contains("@") || !email.contains(".")){
                throw new LangException("hint_42","请输入正确的邮箱");
            }
            UserInfo userInfo = userInfoMapper.userLogin(email);
            if (userInfo != null) {
                throw new LangException("hint_47","此邮箱已绑定账号");
            }
        }
        if (StringUtils.isEmpty(sellerAccountApplyRecord.getUserPwd())){
            throw new LangException(HintConstants.PARAM_NULL,"请输入登录密码");
        }
        if (StringUtils.isEmpty(sellerAccountApplyRecord.getShopName())){
            throw new LangException(HintConstants.PARAM_NULL,"请输入店铺名称");
        }
        if (StringUtils.isEmpty(sellerAccountApplyRecord.getShopImg())){
            throw new LangException(HintConstants.PARAM_NULL,"请上传店铺图片");
        }
        //手机号
        String phone = sellerAccountApplyRecord.getPhone();
        if (StringUtils.isNotEmpty(phone)){
            //验证手机号格式
            String[] split = phone.split("-");
            if (split.length != 2 || !StringUtils.isNumeric(split[0]) || !StringUtils.isNumeric(split[1])){
                throw new LangException("hint_41","请输入正确的手机号码");
            }
            //账号最小位数限制
            Integer userPhoneMinLimit = CacheUtils.getOtherValueByKey("user_phone_min_limit", Integer.class);
            if (userPhoneMinLimit != null && split[1].length() < userPhoneMinLimit){
                List<Object> params = new ArrayList<>();
                params.add(userPhoneMinLimit);
                throw new LangException("hint_46",params,"手机号长度最小为"+userPhoneMinLimit);
            }
            UserInfo userInfo = userInfoMapper.userLogin(phone);
            if (userInfo != null) {
                throw new LangException("hint_47","此手机号已绑定账号");
            }
        }
        String ipAddr = IpUtils.getIpAddr();
        sellerAccountApplyRecord.setApplyIp(ipAddr);
        sellerAccountApplyRecord.setApplyAddress(IpUtils.getAddressByIp(ipAddr));
        sellerAccountApplyRecord.setCreateTime(new Date());
        return sellerAccountApplyRecordMapper.insertSellerAccountApplyRecord(sellerAccountApplyRecord);
    }
}
