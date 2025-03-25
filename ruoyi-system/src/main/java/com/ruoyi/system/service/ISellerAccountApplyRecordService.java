package com.ruoyi.system.service;

import com.ruoyi.system.domain.SellerAccountApplyRecord;

import java.util.List;

/**
 * 商户入驻申请记录Service接口
 * 
 * @author ruoyi
 * @date 2025-01-03
 */
public interface ISellerAccountApplyRecordService 
{
    /**
     * 查询商户入驻申请记录
     * 
     * @param id 商户入驻申请记录主键
     * @return 商户入驻申请记录
     */
    public SellerAccountApplyRecord selectSellerAccountApplyRecordById(Long id);

    /**
     * 查询商户入驻申请记录列表
     * 
     * @param sellerAccountApplyRecord 商户入驻申请记录
     * @return 商户入驻申请记录集合
     */
    public List<SellerAccountApplyRecord> selectSellerAccountApplyRecordList(SellerAccountApplyRecord sellerAccountApplyRecord);

    /**
     * 新增商户入驻申请记录
     * 
     * @param sellerAccountApplyRecord 商户入驻申请记录
     * @return 结果
     */
    public int insertSellerAccountApplyRecord(SellerAccountApplyRecord sellerAccountApplyRecord);

    /**
     * 修改商户入驻申请记录
     * 
     * @param sellerAccountApplyRecord 商户入驻申请记录
     * @return 结果
     */
    public int updateSellerAccountApplyRecord(SellerAccountApplyRecord sellerAccountApplyRecord);

    /**
     * 批量删除商户入驻申请记录
     * 
     * @param ids 需要删除的商户入驻申请记录主键集合
     * @return 结果
     */
    public int deleteSellerAccountApplyRecordByIds(Long[] ids);

    /**
     * 删除商户入驻申请记录信息
     * 
     * @param id 商户入驻申请记录主键
     * @return 结果
     */
    public int deleteSellerAccountApplyRecordById(Long id);

    /**
     * 商户入驻申请审核
     *
     * @param sellerAccountApplyRecord 商户入驻申请记录
     * @return 结果
     */
    public int reviewSellerAccountApply(SellerAccountApplyRecord sellerAccountApplyRecord);

    /**
     * 发起商户入驻申请
     *
     * @param sellerAccountApplyRecord 商户入驻申请记录
     * @return 结果
     */
    public int InitiateApply(SellerAccountApplyRecord sellerAccountApplyRecord);
}
