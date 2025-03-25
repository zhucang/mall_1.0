package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.UserShoppingOrderDetail;
import com.ruoyi.system.mapper.UserShoppingOrderDetailMapper;
import com.ruoyi.system.service.IUserShoppingOrderDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户购物订单明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-12-24
 */
@Service
public class UserShoppingOrderDetailServiceImpl implements IUserShoppingOrderDetailService 
{
    @Resource
    private UserShoppingOrderDetailMapper userShoppingOrderDetailMapper;

    /**
     * 查询用户购物订单明细
     * 
     * @param id 用户购物订单明细主键
     * @return 用户购物订单明细
     */
    @Override
    public UserShoppingOrderDetail selectUserShoppingOrderDetailById(Long id)
    {
        return userShoppingOrderDetailMapper.selectUserShoppingOrderDetailById(id);
    }

    /**
     * 查询用户购物订单明细列表
     * 
     * @param userShoppingOrderDetail 用户购物订单明细
     * @return 用户购物订单明细
     */
    @Override
    public List<UserShoppingOrderDetail> selectUserShoppingOrderDetailList(UserShoppingOrderDetail userShoppingOrderDetail)
    {
        return userShoppingOrderDetailMapper.selectUserShoppingOrderDetailList(userShoppingOrderDetail);
    }

    /**
     * 新增用户购物订单明细
     * 
     * @param userShoppingOrderDetail 用户购物订单明细
     * @return 结果
     */
    @Override
    public int insertUserShoppingOrderDetail(UserShoppingOrderDetail userShoppingOrderDetail)
    {
        userShoppingOrderDetail.setCreateTime(DateUtils.getNowDate());
        return userShoppingOrderDetailMapper.insertUserShoppingOrderDetail(userShoppingOrderDetail);
    }

    /**
     * 批量新增用户购物订单明细
     * @param userShoppingOrderDetails
     * @return
     */
    public int insertUserShoppingOrderDetails(List<UserShoppingOrderDetail> userShoppingOrderDetails){
        return userShoppingOrderDetailMapper.insertUserShoppingOrderDetails(userShoppingOrderDetails);
    }

    /**
     * 修改用户购物订单明细
     * 
     * @param userShoppingOrderDetail 用户购物订单明细
     * @return 结果
     */
    @Override
    public int updateUserShoppingOrderDetail(UserShoppingOrderDetail userShoppingOrderDetail)
    {
        userShoppingOrderDetail.setUpdateTime(DateUtils.getNowDate());
        return userShoppingOrderDetailMapper.updateUserShoppingOrderDetail(userShoppingOrderDetail);
    }

    /**
     * 批量删除用户购物订单明细
     * 
     * @param ids 需要删除的用户购物订单明细主键
     * @return 结果
     */
    @Override
    public int deleteUserShoppingOrderDetailByIds(Long[] ids)
    {
        return userShoppingOrderDetailMapper.deleteUserShoppingOrderDetailByIds(ids);
    }

    /**
     * 删除用户购物订单明细信息
     * 
     * @param id 用户购物订单明细主键
     * @return 结果
     */
    @Override
    public int deleteUserShoppingOrderDetailById(Long id)
    {
        return userShoppingOrderDetailMapper.deleteUserShoppingOrderDetailById(id);
    }
}
