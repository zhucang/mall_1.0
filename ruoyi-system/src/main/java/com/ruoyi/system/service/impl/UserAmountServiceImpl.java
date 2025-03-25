package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.UserAmount;
import com.ruoyi.common.utils.PageUtils;
import com.ruoyi.system.domain.PlatformCurrency;
import com.ruoyi.system.mapper.PlatformCurrencyMapper;
import com.ruoyi.system.mapper.UserAmountMapper;
import com.ruoyi.system.service.IUserAmountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户各币种余额Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-28
 */
@Service
public class UserAmountServiceImpl implements IUserAmountService 
{
    @Resource
    private UserAmountMapper userAmountMapper;

    @Resource
    private PlatformCurrencyMapper platformCurrencyMapper;

    /**
     * 查询用户各币种余额
     * 
     * @param id 用户各币种余额主键
     * @return 用户各币种余额
     */
    @Override
    public UserAmount selectUserAmountById(Long id)
    {
        return userAmountMapper.selectUserAmountById(id);
    }

    /**
     * 查询用户各币种余额列表
     * 
     * @param userAmount 用户各币种余额
     * @return 用户各币种余额
     */
    @Override
    public List<UserAmount> selectUserAmountList(UserAmount userAmount)
    {
        return userAmountMapper.selectUserAmountList(userAmount);
    }

    /**
     * 查询用户各货币余额信息列表
     *
     * @param userId 用户id
     * @return 用户各货币余额信息集合
     */
    @Override
    public List<UserAmount> selectUserAmountListByUserId(Long userId,List<PlatformCurrency> platformCurrencies){
        if (platformCurrencies == null){
            //平台币种
            PlatformCurrency platformCurrency = new PlatformCurrency();
            platformCurrency.setStatus(0);
            PageUtils.orderBy("sort is null,sort");
            platformCurrencies = platformCurrencyMapper.selectPlatformCurrencyList(platformCurrency);
            PageUtils.clearPage();
        }
        //用户钱包余额
        UserAmount userAmount = new UserAmount();
        userAmount.setUserId(userId);
        //用户现有的钱包余额信息
        List<UserAmount> userAmounts = userAmountMapper.selectUserAmountList(userAmount);
        //map
        Map<Long, UserAmount> userAmountMap = userAmounts.stream().collect(Collectors.toMap(a->a.getCurrencyId(), a -> a));
        //结果（只展示启用的钱包信息）
        List<UserAmount> result = new ArrayList<>();
        for (int i = 0; i < platformCurrencies.size(); i++) {
            //币种id
            Long currencyId = platformCurrencies.get(i).getId();
            //该币种钱包信息
            UserAmount userAmountVo = userAmountMap.get(currencyId);
            //如果该用户没有该币种钱包信息
            if (userAmountVo == null){
                userAmountVo = new UserAmount();
                userAmountVo.setUserId(userId);
                userAmountVo.setCurrencyId(currencyId);
                userAmountVo.setAmount(BigDecimal.ZERO);
                userAmountVo.setFrozenAmount(BigDecimal.ZERO);
            }
            userAmountVo.setCurrencyName(platformCurrencies.get(i).getCurrencyName());
            result.add(userAmountVo);
        }
        return result;
    }

    /**
     * 新增用户各币种余额
     * 
     * @param userAmount 用户各币种余额
     * @return 结果
     */
    @Override
    public int insertUserAmount(UserAmount userAmount)
    {
        return userAmountMapper.insertUserAmount(userAmount);
    }

    /**
     * 修改用户各币种余额
     * 
     * @param userAmount 用户各币种余额
     * @return 结果
     */
    @Override
    public int updateUserAmount(UserAmount userAmount)
    {
        //还没有此账户余额记录
        if (userAmount.getId() == null){
            return userAmountMapper.insertUserAmount(userAmount);
        }else {
            return userAmountMapper.updateUserAmount(userAmount);
        }
    }

    /**
     * 批量删除用户各币种余额
     * 
     * @param ids 需要删除的用户各币种余额主键
     * @return 结果
     */
    @Override
    public int deleteUserAmountByIds(Long[] ids)
    {
        return userAmountMapper.deleteUserAmountByIds(ids);
    }

    /**
     * 删除用户各币种余额信息
     * 
     * @param id 用户各币种余额主键
     * @return 结果
     */
    @Override
    public int deleteUserAmountById(Long id)
    {
        return userAmountMapper.deleteUserAmountById(id);
    }

    /**
     * 获取用户某币种余额
     * @param userId 用户id
     * @param currencyId 货币id
     */
    @Override
    public UserAmount getUserAmount(Long userId, Long currencyId) {
        //用户该币种余额信息
        UserAmount userAmount = userAmountMapper.getUserAmount(userId, currencyId);
        if (userAmount == null) {
            userAmount = new UserAmount();
            userAmount.setUserId(userId);
            userAmount.setCurrencyId(currencyId);
            userAmount.setAmount(BigDecimal.ZERO);
            userAmount.setFrozenAmount(BigDecimal.ZERO);
        }
        return userAmount;
    }
}
