package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.UserInfo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.logDict.*;
import com.ruoyi.common.service.TokenService;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.UserAuthRecord;
import com.ruoyi.system.service.*;
import com.ruoyi.system.utils.cache.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户信息Controller
 * 
 * @author ruoyi
 * @date 2023-10-28
 */
@RestController
@RequestMapping("/system/userInfo")
public class UserInfoController extends BaseController
{
    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private IUserRechargeService userRechargeService;

    @Autowired
    private IUserPointChangeRecordService userPointChangeRecordService;

    @Autowired
    private IUserWinningsChangeRecordService userWinningsChangeRecordService;

    @Autowired
    private IUserWithdrawService userWithdrawService;

    @Autowired
    private ISwitchSetService switchSetService;

    /**
     * 查询用户信息列表
     */
    @PreAuthorize("@ss.hasAnyPermi('system:userInfo:list,system:tourists:list,system:sellerInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserInfo userInfo)
    {
        startPage();
        startOrderBy("id desc");
        List<UserInfo> list = userInfoService.selectUserInfoList(userInfo);
        userInfoService.fillOtherInfo(list);
        return getDataTable(list);
    }

    /**
     * 查询用户信息列表(无权限)
     */
    @GetMapping("/listNoPreAuthorize")
    public TableDataInfo listNoPreAuthorize(UserInfo userInfo)
    {
        startPage();
        startOrderBy("id desc");
        Object userIds = userInfo.getParams().get("userIds");
        if (userIds != null){
            try{
                userInfo.getParams().put("userIds", Arrays.asList(String.valueOf(userIds).split(",")));
            }catch (Exception e){

            }
        }
        List<UserInfo> list = userInfoService.selectUserInfoList(userInfo);
        return getDataTable(list);
    }

    /**
     * 获取用户信息详细信息
     */
    @PreAuthorize("@ss.hasAnyPermi('system:userInfo:query,system:tourists:query,system:sellerInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(userInfoService.selectUserInfoById(id));
    }

    /**
     * 新增用户信息
     */
    @PreAuthorize("@ss.hasAnyPermi('system:userInfo:add,system:tourists:add,system:sellerInfo:add')")
    @Log(title = "用户信息", relateAppUserId = "id", businessType = BusinessType.INSERT,dict = UserInfoLogDict.class,
            saveParamNames = {"id","userAccount","phone","email","nickName","userPwd","withPwd","agentId","agentName","accountType","isLock","status","needOrderAmount","remark"})
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody UserInfo userInfo)
    {
        if (StringUtils.isEmpty(userInfo.getUserAccount())){
            throw new ServiceException("请输入用户登陆账号");
        }else {
            //去除头尾空格
            userInfo.setUserAccount(userInfo.getUserAccount().trim());
        }
        if (StringUtils.isEmpty(userInfo.getNickName())){
            throw new ServiceException("请输入用户昵称");
        }
//        if (StringUtils.isEmpty(userInfo.getPhone()) && StringUtils.isEmpty(userInfo.getEmail())){
//            return AjaxResult.error("邮箱和手机号至少填写一种");
//        }
        //用户登录密码
        String userPwd = userInfo.getUserPwd();
        if (StringUtils.isEmpty(userPwd) || userPwd.length() < 6){
            throw new ServiceException("请输入至少6位数的登录密码");
        }
        //用户提现密码
        String withPwd = userInfo.getWithPwd();
        if (StringUtils.isNotEmpty(withPwd) && withPwd.length() < 6){
            throw new ServiceException("请输入至少6位数的提现密码");
        }
        if (userInfo.getAccountType() == null){
            throw new ServiceException("请选择账号类型");
        }
        if (!userInfo.getAccountType().equals(0) && !userInfo.getAccountType().equals(1)){
            throw new ServiceException("账号类型错误");
        }
        return toAjax(userInfoService.insertUserInfo(userInfo));
    }

    /**
     * 修改用户信息
     */
    @PreAuthorize("@ss.hasAnyPermi('system:userInfo:edit,system:tourists:edit,system:sellerInfo:edit')")
    @Log(title = "用户信息", relateAppUserId = "id", businessType = BusinessType.UPDATE,dict = UserInfoLogDict.class,
            saveParamNames = {"id","userAccount","phone","email","nickName","userPwd","withPwd","agentId","agentName","accountType","isLock","status","needOrderAmount","remark"})
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody UserInfo userInfo)
    {
        if (userInfo.getId() == null){
            throw new ServiceException("请选择需要修改的选项");
        }
        //用户登录密码
        String userPwd = userInfo.getUserPwd();
        if (StringUtils.isNotEmpty(userPwd) && userPwd.length() < 6){
            throw new ServiceException("请输入至少6位数的登录密码");
        }
        //用户提现密码
        String withPwd = userInfo.getWithPwd();
        if (StringUtils.isNotEmpty(withPwd) && withPwd.length() < 6){
            throw new ServiceException("请输入至少6位数的提现密码");
        }
        return toAjax(userInfoService.updateUserInfo(userInfo));
    }

    /**
     * 删除用户信息
     */
    @PreAuthorize("@ss.hasAnyPermi('system:userInfo:remove,system:tourists:remove,system:sellerInfo:remove')")
    @Log(title = "用户信息", businessType = BusinessType.DELETE,dict = UserInfoLogDict.class,
            saveParamNames = {"id","userNo","userAccount","userInfos"})
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userInfoService.deleteUserInfoByIds(ids));
    }

    /**
     * 修改用户资金（用户资金入款/扣款）
     */
    @PreAuthorize("@ss.hasAnyPermi('system:userInfo:updateAmt,system:tourists:updateAmt,system:sellerInfo:updateAmt')")
    @Log(title = "修改用户资金（上分/下分）" , businessType = BusinessType.UPDATE,dict = UserPointChangeRecordLogDict.class,
            saveParamNames = {"orderCode","orderAmount","orderType","operatorName","currencyId","currencyName","userAmountBefore","userAmountAfter"})
    @RepeatSubmit
    @PostMapping(value = "updateAmt")
    public AjaxResult updateAmt(Long userId, BigDecimal amt, Integer direction, Long currencyId){
        if (userId == null){
            throw new ServiceException("请选择需要修改的选项");
        }
        if (amt == null){
            throw new ServiceException("请输入需要变更的金额");
        }
        if (amt.compareTo(BigDecimal.ZERO) <= 0){
            throw new ServiceException("金额必须大于0");
        }
        if (currencyId == null){
            throw new ServiceException("请选择需要变更金额的货币类型");
        }
        if (direction == null){
            throw new ServiceException("请选择是入款还是扣款");
        }
        if (!direction.equals(0) && !direction.equals(1)){
            throw new ServiceException("操作方向错误");
        }
        return toAjax(userInfoService.updateAmt(userId, amt, direction,currencyId));
    }

    /**
     * 修改用户彩金
     */
    @PreAuthorize("@ss.hasAnyPermi('system:userInfo:updateWinnings,system:tourists:updateWinnings,system:sellerInfo:updateWinnings')")
    @Log(title = "修改用户彩金" , businessType = BusinessType.UPDATE,dict = UserWinningsChangeRecordLogDict.class,
            saveParamNames = {"orderCode","orderAmount","orderType","operatorName","currencyId","currencyName","userAmountBefore","userAmountAfter"})
    @RepeatSubmit
    @PostMapping(value = "updateWinnings")
    public AjaxResult updateWinnings(Long userId, BigDecimal amt, Integer direction, Long currencyId){
        if (userId == null){
            throw new ServiceException("请选择需要修改的选项");
        }
        if (amt == null){
            throw new ServiceException("请输入需要变更的金额");
        }
        if (amt.compareTo(BigDecimal.ZERO) <= 0){
            throw new ServiceException("金额必须大于0");
        }
        if (currencyId == null){
            throw new ServiceException("请选择需要变更金额的货币类型");
        }
        if (direction == null){
            throw new ServiceException("请选择彩金类型");
        }
        if (!direction.equals(0) && !direction.equals(1) && !direction.equals(2)){
            throw new ServiceException("操作方向错误");
        }
        return toAjax(userInfoService.updateWinnings(userId, amt, direction,currencyId));
    }

    /**
     * 修改用户VIP等级
     */
    @PreAuthorize("@ss.hasAnyPermi('system:userInfo:setUserVipLevel,system:tourists:setUserVipLevel,system:sellerInfo:setUserVipLevel')")
    @Log(title = "修改用户VIP等级" , businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping(value = "setUserVipLevel")
    public AjaxResult setUserVipLevel(Long userId, Integer vipLevel) {
        if (userId == null){
            return AjaxResult.error("请选择需要修改的用户");
        }
        if (vipLevel == null){
            return AjaxResult.error("请输入用户vip");
        }
        return userInfoService.setUserVipLevel(userId,vipLevel);
    }

    /**
     * 修改用户信用分
     */
    @PreAuthorize("@ss.hasAnyPermi('system:userInfo:setUserCreditScore,system:tourists:setUserCreditScore,system:sellerInfo:setUserCreditScore')")
    @Log(title = "修改用户信用分" , businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping(value = "setUserCreditScore")
    public AjaxResult setUserCreditScore(Long userId,Integer creditScore) {
        if (userId == null){
            return AjaxResult.error("请选择需要修改的用户");
        }
        if (creditScore == null){
            return AjaxResult.error("请输入信用分");
        }
        return userInfoService.setUserCreditScore(userId,creditScore);
    }

    /**
     * 修改用户可提现状态
     */
    @PreAuthorize("@ss.hasAnyPermi('system:userInfo:setUserIsCanWithdraw,system:tourists:setUserIsCanWithdraw,system:sellerInfo:setUserIsCanWithdraw')")
    @Log(title = "修改用户可提现状态" , businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping(value = "setUserIsCanWithdraw")
    public AjaxResult setUserIsCanWithdraw(Long userId,Integer isCanWithdraw) {
        if (userId == null){
            return AjaxResult.error("请选择需要修改的用户");
        }
        if (isCanWithdraw == null){
            return AjaxResult.error("请选择用户可提现状态");
        }
        return userInfoService.setUserIsCanWithdraw(userId,isCanWithdraw);
    }

    /**
     * 设置用户是否是代理员
     */
    @PreAuthorize("@ss.hasAnyPermi('system:userInfo:setIsAgent,system:tourists:setIsAgent,system:sellerInfo:setIsAgent')")
    @Log(title = "设置用户是否是代理员" , businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping(value = "setIsAgent")
    public AjaxResult setIsAgent(Long userId, Integer isAgent) {
        if (userId == null){
            return AjaxResult.error("请选择需要修改的用户");
        }
        if (isAgent == null){
            return AjaxResult.error("请选择用户是否是代理员");
        }
        return userInfoService.setIsAgent(userId,isAgent);
    }

    /**
     * 冻结、解冻资金
     */
    @PreAuthorize("@ss.hasAnyPermi('system:userInfo:freezeAndUnfreezeFunds,system:tourists:freezeAndUnfreezeFunds,system:sellerInfo:freezeAndUnfreezeFunds')")
    @Log(title = "冻结、解冻资金" , businessType = BusinessType.UPDATE,dict = UserBillDetailLogDict.class,
            saveParamNames = {"currencyId","currencyName","orderAmount","amountBefore","amountAfter","frozenAmountBefore","frozenAmountAfter"})
    @RepeatSubmit
    @PostMapping(value = "freezeAndUnfreezeFunds")
    public AjaxResult freezeAndUnfreezeFunds(Long userId,Long currencyId,BigDecimal amount,Integer operationType) {
        if (userId == null){
            throw new ServiceException("请选择需要修改的用户");
        }
        if (currencyId == null){
            throw new ServiceException("请选择币种");
        }
        if (amount == null){
            throw new ServiceException("请输入操作金额");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new ServiceException("操作金额必须大于0");
        }
        if (operationType == null){
            throw new ServiceException("请选择操作方向");
        }
        if (!operationType.equals(0) && !operationType.equals(1)){
            throw new ServiceException("操作方向错误");
        }
        return toAjax(userInfoService.freezeAndUnfreezeFunds(userId,currencyId,amount,operationType));
    }


    /**
     * 用户实名审核
     */
    @PreAuthorize("@ss.hasAnyPermi('system:userInfo:authApprove,system:tourists:authApprove,system:sellerInfo:authApprove')")
    @Log(title = "用户实名审核" ,businessType = BusinessType.UPDATE,dict = UserAuthRecordLogDict.class,
            saveParamNames = {"userNo","idNumber","realName","img1Key","img2Key","img3Key","authStatus","authMethod","authLevel"})
    @RepeatSubmit
    @PostMapping(value = "authApprove")
    public AjaxResult userAuth(@RequestBody UserAuthRecord userAuthRecord) {
        if (userAuthRecord.getUserId() == null){
            return AjaxResult.error("请选择需要修改的用户");
        }
        if (userAuthRecord.getAuthStatus() == null){
            return AjaxResult.error("请选择实名状态");
        }
        if (!userAuthRecord.getAuthStatus().equals(-1) && !userAuthRecord.getAuthStatus().equals(0) && !userAuthRecord.getAuthStatus().equals(1) && !userAuthRecord.getAuthStatus().equals(2) && !userAuthRecord.getAuthStatus().equals(3)){
            return AjaxResult.error("实名状态错误");
        }
        if (!userAuthRecord.getAuthStatus().equals(-1)){
            if (userAuthRecord.getAuthMethod() == null){
                return AjaxResult.error("请选择认证方式");
            }
            if (userAuthRecord.getAuthLevel() == null){
                return AjaxResult.error("请选择认证等级");
            }
            if (StringUtils.isEmpty(userAuthRecord.getIdNumber())){
                return AjaxResult.error("请输入证件号码");
            }
            //如果是身份证认证
            if (userAuthRecord.getAuthMethod().equals(0) && StringUtils.isNotEmpty(userAuthRecord.getIdNumber())){
                //身份证位数限制开关
                Integer idCardNumberDigitLimit = switchSetService.selectSwitchStatusById(17L);
                if (idCardNumberDigitLimit.equals(0)){
                    //身份证位数
                    Integer digit = CacheUtils.getOtherValueByKey("idCardNumber.digit",Integer.class);
                    if (digit != null && userAuthRecord.getIdNumber().length() != digit){
                        throw new ServiceException("请输入正确的身份证号码");
                    }
                }
            }
            //用户实名认证是否填写真实姓名
            Integer selectSwitchStatusById117 = switchSetService.selectSwitchStatusById(117L);
            if (selectSwitchStatusById117.equals(0)){
                if (StringUtils.isEmpty(userAuthRecord.getRealName())){
                    return AjaxResult.error("请输入真实姓名");
                }else {
                    //去除头尾空格
                    userAuthRecord.setRealName(userAuthRecord.getRealName().trim());
                }
            }
            //初级认证
            if (userAuthRecord.getAuthLevel().equals(0)){

            }else if (userAuthRecord.getAuthLevel().equals(1)){
                //高级认证
                if (StringUtils.isEmpty(userAuthRecord.getImg1Key())){
                    return AjaxResult.error("请上传证件正面图片");
                }
                //用户实名认证是否上传背面身份证
                Integer selectSwitchStatusById118 = switchSetService.selectSwitchStatusById(118L);
                if (selectSwitchStatusById118.equals(0)){
                    if (StringUtils.isEmpty(userAuthRecord.getImg2Key())){
                        return AjaxResult.error("请上传证件反面图片");
                    }
                }
                //用户实名认证是否上传手持身份证
                Integer selectSwitchStatusById63 = switchSetService.selectSwitchStatusById(63L);
                if (selectSwitchStatusById63.equals(0)){
                    if (StringUtils.isEmpty(userAuthRecord.getImg3Key())){
                        return AjaxResult.error("请上传手持身份证图片");
                    }
                }
            }
        }
        return toAjax(userInfoService.authApprove(userAuthRecord));
    }

    /**
     * 踢下线功能
     */
    @PreAuthorize("@ss.hasAnyPermi('system:userInfo:kickOffline,system:tourists:kickOffline,system:sellerInfo:kickOffline')")
    @Log(title = "强制用户下线" ,businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping(value = "/kickOffline")
    public AjaxResult kickOffline(Long userId){
        if (userId == null){
            throw new ServiceException("请选择需要操作的用户");
        }
        //token标识
        String tokenFlag = "app";
        //此用户当前登录的token
        String tokenCurrent = redisCache.getCacheMapValue(tokenFlag, String.valueOf(userId));
        //删除登录信息
        tokenService.delLoginUser(tokenCurrent);
        return AjaxResult.success();
    }

//    /**
////     * 变更上级(变更用户的上级)
////     */
////    @PreAuthorize("@ss.hasAnyPermi('system:userInfo:changeSuperior,system:tourists:changeSuperior,system:sellerInfo:changeSuperior')")
////    @Log(title = "变更上级(变更用户的上级)", businessType = BusinessType.UPDATE
////            ,saveParamNames = {"用户编号","用户账号","上级编号","上级账号"})
////    @RepeatSubmit
////    @PostMapping("/{changeSuperior}")
////    public AjaxResult changeSuperior(Long supUserId,Long userId)
////    {
////        if (supUserId == null){
////            throw new ServiceException("请选择上级");
////        }
////        if (userId == null){
////            throw new ServiceException("请选择需要变更上级的选项");
////        }
////        return toAjax(userInfoService.changeSuperior(supUserId,userId));
////    }

    /**
     * 查看用户各币种充值金额
     */
    @GetMapping(value = "selectUserRechargeAmountAllCurrency")
    public AjaxResult selectUserRechargeAmountAllCurrency(Long userId) {
        if (userId == null){
            return AjaxResult.error("请选择需要查看信息的用户");
        }
        //result
        List<Object> result = new ArrayList<>();
        //用户充值
        Map<String, Object> userRecharge = new HashMap<>();
        userRecharge.put("type","用户充值");
        userRecharge.put("data",userRechargeService.selectUserRechargeAmountAllCurrencyByUserId(userId).stream().map(a->{a.getParams().put("orderAmount",a.getRechargeAmount());return a;}).collect(Collectors.toList()));
        result.add(userRecharge);
        //上分
        Map<String, Object> upPoint = new HashMap<>();
        upPoint.put("type","上分");
        upPoint.put("data",userPointChangeRecordService.selectUserPointChangeAmountAllCurrencyByUserId(userId,0).stream().map(a->{a.getParams().put("orderAmount",a.getOrderAmount());return a;}).collect(Collectors.toList()));
        result.add(upPoint);
        //彩金赠送（系统充值）
        Map<String, Object> winnings1 = new HashMap<>();
        winnings1.put("type","彩金赠送（系统充值)");
        winnings1.put("data",userWinningsChangeRecordService.selectUserWinningsChangeAmountAllCurrencyByUserId(userId,0).stream().map(a->{a.getParams().put("orderAmount",a.getOrderAmount());return a;}).collect(Collectors.toList()));
        result.add(winnings1);
        //彩金赠送（福利彩金）
        Map<String, Object> winnings2 = new HashMap<>();
        winnings2.put("type","彩金赠送（福利彩金)");
        winnings2.put("data",userWinningsChangeRecordService.selectUserWinningsChangeAmountAllCurrencyByUserId(userId,1).stream().map(a->{a.getParams().put("orderAmount",a.getOrderAmount());return a;}).collect(Collectors.toList()));
        result.add(winnings2);
        //充值彩金
        Map<String, Object> winnings3 = new HashMap<>();
        winnings3.put("type","充值彩金");
        winnings3.put("data",userWinningsChangeRecordService.selectUserWinningsChangeAmountAllCurrencyByUserId(userId,3).stream().map(a->{a.getParams().put("orderAmount",a.getOrderAmount());return a;}).collect(Collectors.toList()));
        result.add(winnings3);
        //注册彩金
        Map<String, Object> winnings4 = new HashMap<>();
        winnings4.put("type","注册彩金");
        winnings4.put("data",userWinningsChangeRecordService.selectUserWinningsChangeAmountAllCurrencyByUserId(userId,4).stream().map(a->{a.getParams().put("orderAmount",a.getOrderAmount());return a;}).collect(Collectors.toList()));
        result.add(winnings4);
        return AjaxResult.success(result);
    }

    /**
     * 查看用户各币种提现金额
     */
    @GetMapping(value = "selectUserWithdrawAmountAllCurrency")
    public AjaxResult selectUserWithdrawAmountAllCurrency(Long userId) {
        if (userId == null){
            return AjaxResult.error("请选择需要查看信息的用户");
        }
        //result
        List<Object> result = new ArrayList<>();
        //用户提现
        Map<String, Object> userWithdraw = new HashMap<>();
        userWithdraw.put("type","用户提现");
        userWithdraw.put("data",userWithdrawService.selectUserWithdrawAmountAllCurrency(userId).stream().map(a->{a.getParams().put("orderAmount",a.getWithdrawAmount());return a;}).collect(Collectors.toList()));
        result.add(userWithdraw);
        //下分
        Map<String, Object> downPoint = new HashMap<>();
        downPoint.put("type","下分");
        downPoint.put("data",userPointChangeRecordService.selectUserPointChangeAmountAllCurrencyByUserId(userId,1).stream().map(a->{a.getParams().put("orderAmount",a.getOrderAmount());return a;}).collect(Collectors.toList()));
        result.add(downPoint);
        //回收彩金
        Map<String, Object> winnings = new HashMap<>();
        winnings.put("type","回收彩金");
        winnings.put("data",userWinningsChangeRecordService.selectUserWinningsChangeAmountAllCurrencyByUserId(userId,2).stream().map(a->{a.getParams().put("orderAmount",a.getOrderAmount());return a;}).collect(Collectors.toList()));
        result.add(winnings);
        return AjaxResult.success(result);
    }
}
