package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SellerAccountApplyRecord;

import java.util.List;

/**
 * 商户入驻申请记录Mapper接口
 * 
 * @author ruoyi
 * @date 2025-01-03
 */
public interface SellerAccountApplyRecordMapper 
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
     * 删除商户入驻申请记录
     * 
     * @param id 商户入驻申请记录主键
     * @return 结果
     */
    public int deleteSellerAccountApplyRecordById(Long id);

    /**
     * 批量删除商户入驻申请记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSellerAccountApplyRecordByIds(Long[] ids);
}
