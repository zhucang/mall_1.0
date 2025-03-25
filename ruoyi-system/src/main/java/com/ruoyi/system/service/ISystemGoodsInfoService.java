package com.ruoyi.system.service;

import com.ruoyi.system.domain.LangMgr;
import com.ruoyi.system.domain.SystemGoodsInfo;

import java.util.List;

/**
 * 系统商品信息Service接口
 * 
 * @author ruoyi
 * @date 2024-12-03
 */
public interface ISystemGoodsInfoService 
{
    /**
     * 查询系统商品信息
     * 
     * @param id 系统商品信息主键
     * @return 系统商品信息
     */
    public SystemGoodsInfo selectSystemGoodsInfoById(Long id);

    /**
     * 查询系统商品信息列表
     * 
     * @param systemGoodsInfo 系统商品信息
     * @return 系统商品信息集合
     */
    public List<SystemGoodsInfo> selectSystemGoodsInfoList(SystemGoodsInfo systemGoodsInfo);

    /**
     * 新增系统商品信息
     * 
     * @param systemGoodsInfo 系统商品信息
     * @return 结果
     */
    public int insertSystemGoodsInfo(SystemGoodsInfo systemGoodsInfo);

    /**
     * 修改系统商品信息
     * 
     * @param systemGoodsInfo 系统商品信息
     * @return 结果
     */
    public int updateSystemGoodsInfo(SystemGoodsInfo systemGoodsInfo);

    /**
     * 修改商品名称多语言配置
     * @param systemGoodsInfoId 系统商品ID
     * @param goodsNameLang 商品名称多语言
     * @return
     */
    public int updateGoodsNameLang(Long systemGoodsInfoId, LangMgr goodsNameLang);

    /**
     * 修改商品详情多语言配置
     * @param systemGoodsInfoId 系统商品ID
     * @param goodsDescLang 商品名称多语言
     * @return
     */
    public int updateGoodsDescLang(Long systemGoodsInfoId, LangMgr goodsDescLang);

    /**
     * 批量删除系统商品信息
     * 
     * @param ids 需要删除的系统商品信息主键集合
     * @return 结果
     */
    public int deleteSystemGoodsInfoByIds(String[] ids);

    /**
     * 删除系统商品信息信息
     * 
     * @param id 系统商品信息主键
     * @return 结果
     */
    public int deleteSystemGoodsInfoById(String id);
}
