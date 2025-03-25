package com.ruoyi.system.service;

import com.ruoyi.system.domain.UserReceiveAddress;

import java.util.List;

/**
 * 用户收货地址Service接口
 * 
 * @author ruoyi
 * @date 2024-12-27
 */
public interface IUserReceiveAddressService 
{
    /**
     * 查询用户收货地址
     * 
     * @param id 用户收货地址主键
     * @return 用户收货地址
     */
    public UserReceiveAddress selectUserReceiveAddressById(Long id);

    /**
     * 查询用户收货地址列表
     * 
     * @param userReceiveAddress 用户收货地址
     * @return 用户收货地址集合
     */
    public List<UserReceiveAddress> selectUserReceiveAddressList(UserReceiveAddress userReceiveAddress);

    /**
     * 新增用户收货地址
     * 
     * @param userReceiveAddress 用户收货地址
     * @return 结果
     */
    public int insertUserReceiveAddress(UserReceiveAddress userReceiveAddress);

    /**
     * 修改用户收货地址
     * 
     * @param userReceiveAddress 用户收货地址
     * @return 结果
     */
    public int updateUserReceiveAddress(UserReceiveAddress userReceiveAddress);

    /**
     * 批量删除用户收货地址
     * 
     * @param ids 需要删除的用户收货地址主键集合
     * @return 结果
     */
    public int deleteUserReceiveAddressByIds(Long[] ids);

    /**
     * 删除用户收货地址信息
     * 
     * @param id 用户收货地址主键
     * @return 结果
     */
    public int deleteUserReceiveAddressById(Long id);

    /**
     * 用户添加收货地址
     *
     * @param userReceiveAddress 用户收货地址
     * @return 结果
     */
    public int userAddReceiveAddress(UserReceiveAddress userReceiveAddress);

    /**
     * 用户编辑收货地址
     *
     * @param userReceiveAddress 用户收货地址
     * @return 结果
     */
    public int userEditReceiveAddress(UserReceiveAddress userReceiveAddress);

    /**
     * 用户删除收货地址
     *
     * @param ids
     * @return 结果
     */
    public int userRemoveReceiveAddress(List<Long> ids);
}
