package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.UserReceiveAddress;
import com.ruoyi.system.mapper.UserReceiveAddressMapper;
import com.ruoyi.system.service.IUserReceiveAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户收货地址Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-12-27
 */
@Service
public class UserReceiveAddressServiceImpl implements IUserReceiveAddressService 
{
    @Resource
    private UserReceiveAddressMapper userReceiveAddressMapper;

    /**
     * 查询用户收货地址
     * 
     * @param id 用户收货地址主键
     * @return 用户收货地址
     */
    @Override
    public UserReceiveAddress selectUserReceiveAddressById(Long id)
    {
        return userReceiveAddressMapper.selectUserReceiveAddressById(id);
    }

    /**
     * 查询用户收货地址列表
     * 
     * @param userReceiveAddress 用户收货地址
     * @return 用户收货地址
     */
    @Override
    public List<UserReceiveAddress> selectUserReceiveAddressList(UserReceiveAddress userReceiveAddress)
    {
        return userReceiveAddressMapper.selectUserReceiveAddressList(userReceiveAddress);
    }

    /**
     * 新增用户收货地址
     * 
     * @param userReceiveAddress 用户收货地址
     * @return 结果
     */
    @Override
    public int insertUserReceiveAddress(UserReceiveAddress userReceiveAddress)
    {
        userReceiveAddress.setCreateTime(DateUtils.getNowDate());
        return userReceiveAddressMapper.insertUserReceiveAddress(userReceiveAddress);
    }

    /**
     * 修改用户收货地址
     * 
     * @param userReceiveAddress 用户收货地址
     * @return 结果
     */
    @Override
    public int updateUserReceiveAddress(UserReceiveAddress userReceiveAddress)
    {
        userReceiveAddress.setUpdateTime(DateUtils.getNowDate());
        return userReceiveAddressMapper.updateUserReceiveAddress(userReceiveAddress);
    }

    /**
     * 批量删除用户收货地址
     * 
     * @param ids 需要删除的用户收货地址主键
     * @return 结果
     */
    @Override
    public int deleteUserReceiveAddressByIds(Long[] ids)
    {
        return userReceiveAddressMapper.deleteUserReceiveAddressByIds(ids);
    }

    /**
     * 删除用户收货地址信息
     * 
     * @param id 用户收货地址主键
     * @return 结果
     */
    @Override
    public int deleteUserReceiveAddressById(Long id)
    {
        return userReceiveAddressMapper.deleteUserReceiveAddressById(id);
    }

    /**
     * 用户添加收货地址
     *
     * @param userReceiveAddress 用户收货地址
     * @return 结果
     */
    @Override
    public int userAddReceiveAddress(UserReceiveAddress userReceiveAddress){
        //用户ID
        Long userId = userReceiveAddress.getUserId();
        //是否默认 0：否 1:是
        Integer defaultFlag = userReceiveAddress.getDefaultFlag();
        //如果设置默认收货地址
        if (defaultFlag != null && defaultFlag.equals(1)){
            //设置所有收货地址为非默认
            userReceiveAddressMapper.setAllAddressNotDefault(userId);
        }
        userReceiveAddress.setCreateTime(DateUtils.getNowDate());
        return userReceiveAddressMapper.insertUserReceiveAddress(userReceiveAddress);
    }

    /**
     * 用户编辑收货地址
     *
     * @param userReceiveAddress 用户收货地址
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int userEditReceiveAddress(UserReceiveAddress userReceiveAddress){
        //用户ID
        Long userId = SecurityUtils.getUserId();
        //用户收货地址ID
        Long id = userReceiveAddress.getId();
        //用户旧的收货地址信息
        UserReceiveAddress userReceiveAddressOld = userReceiveAddressMapper.selectUserReceiveAddressById(id);
        if (!userReceiveAddressOld.getUserId().equals(userId)){
            throw new ServiceException("获取用户信息异常", HttpStatus.UNAUTHORIZED);
        }
        //是否默认 0：否 1:是
        Integer defaultFlag = userReceiveAddress.getDefaultFlag();
        //如果设置默认收货地址
        if (defaultFlag != null && defaultFlag.equals(1)){
            //设置所有收货地址为非默认
            userReceiveAddressMapper.setAllAddressNotDefault(userId);
        }
        userReceiveAddress.setUpdateTime(DateUtils.getNowDate());
        return userReceiveAddressMapper.updateUserReceiveAddress(userReceiveAddress);
    }

    /**
     * 用户删除收货地址
     *
     * @param ids
     * @return 结果
     */
    @Override
    public int userRemoveReceiveAddress(List<Long> ids){
        //用户ID
        Long userId = SecurityUtils.getUserId();
        //获取即将删除的收货地址
        UserReceiveAddress userReceiveAddress = new UserReceiveAddress();
        userReceiveAddress.getParams().put("ids",ids);
        List<UserReceiveAddress> userReceiveAddresses = userReceiveAddressMapper.selectUserReceiveAddressList(userReceiveAddress);
        //检验身份
        if (userReceiveAddresses.stream().filter(a->!a.getUserId().equals(userId)).count() > 0){
            throw new ServiceException("用户收货地址校验异常", HttpStatus.UNAUTHORIZED);
        }
        return userReceiveAddressMapper.deleteUserReceiveAddressByIds(ids.toArray(new Long[ids.size()]));
    }
}
