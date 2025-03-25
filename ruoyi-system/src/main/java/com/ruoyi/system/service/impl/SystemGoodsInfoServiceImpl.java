package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.LangMgr;
import com.ruoyi.system.domain.SystemGoodsInfo;
import com.ruoyi.system.mapper.SystemGoodsInfoMapper;
import com.ruoyi.system.service.ISystemGoodsInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统商品信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-12-03
 */
@Service
public class SystemGoodsInfoServiceImpl implements ISystemGoodsInfoService 
{
    @Resource
    private SystemGoodsInfoMapper systemGoodsInfoMapper;

    /**
     * 查询系统商品信息
     * 
     * @param id 系统商品信息主键
     * @return 系统商品信息
     */
    @Override
    public SystemGoodsInfo selectSystemGoodsInfoById(Long id)
    {
        return systemGoodsInfoMapper.selectSystemGoodsInfoById(id);
    }

    /**
     * 查询系统商品信息列表
     * 
     * @param systemGoodsInfo 系统商品信息
     * @return 系统商品信息
     */
    @Override
    public List<SystemGoodsInfo> selectSystemGoodsInfoList(SystemGoodsInfo systemGoodsInfo)
    {
        return systemGoodsInfoMapper.selectSystemGoodsInfoList(systemGoodsInfo);
    }

    /**
     * 新增系统商品信息
     * 
     * @param systemGoodsInfo 系统商品信息
     * @return 结果
     */
    @Override
    public int insertSystemGoodsInfo(SystemGoodsInfo systemGoodsInfo)
    {
        systemGoodsInfo.setCreateTime(DateUtils.getNowDate());
        return systemGoodsInfoMapper.insertSystemGoodsInfo(systemGoodsInfo);
    }

    /**
     * 修改系统商品信息
     * 
     * @param systemGoodsInfo 系统商品信息
     * @return 结果
     */
    @Override
    public int updateSystemGoodsInfo(SystemGoodsInfo systemGoodsInfo)
    {
        systemGoodsInfo.setUpdateTime(DateUtils.getNowDate());
        return systemGoodsInfoMapper.updateSystemGoodsInfo(systemGoodsInfo);
    }

    /**
     * 修改商品名称多语言配置
     * @param systemGoodsInfoId 系统商品ID
     * @param goodsNameLang 商品名称多语言
     * @return
     */
    public int updateGoodsNameLang(Long systemGoodsInfoId, LangMgr goodsNameLang){
        SystemGoodsInfo systemGoodsInfo = new SystemGoodsInfo();
        systemGoodsInfo.setId(systemGoodsInfoId);
        systemGoodsInfo.setGoodsNameLang(goodsNameLang);
        systemGoodsInfo.setUpdateTime(DateUtils.getNowDate());
        return systemGoodsInfoMapper.updateSystemGoodsInfo(systemGoodsInfo);
    }

    /**
     * 修改商品详情多语言配置
     * @param systemGoodsInfoId 系统商品ID
     * @param goodsDescLang 商品名称多语言
     * @return
     */
    public int updateGoodsDescLang(Long systemGoodsInfoId, LangMgr goodsDescLang){
        SystemGoodsInfo systemGoodsInfo = new SystemGoodsInfo();
        systemGoodsInfo.setId(systemGoodsInfoId);
        systemGoodsInfo.setGoodsDescLang(goodsDescLang);
        systemGoodsInfo.setUpdateTime(DateUtils.getNowDate());
        return systemGoodsInfoMapper.updateSystemGoodsInfo(systemGoodsInfo);
    }

    /**
     * 批量删除系统商品信息
     * 
     * @param ids 需要删除的系统商品信息主键
     * @return 结果
     */
    @Override
    public int deleteSystemGoodsInfoByIds(String[] ids)
    {
        return systemGoodsInfoMapper.deleteSystemGoodsInfoByIds(ids);
    }

    /**
     * 删除系统商品信息信息
     * 
     * @param id 系统商品信息主键
     * @return 结果
     */
    @Override
    public int deleteSystemGoodsInfoById(String id)
    {
        return systemGoodsInfoMapper.deleteSystemGoodsInfoById(id);
    }
}
