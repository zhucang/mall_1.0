package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.AttrType;
import com.ruoyi.system.domain.LangMgr;
import com.ruoyi.system.mapper.AttrTypeMapper;
import com.ruoyi.system.service.IAttrTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 属性类型Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-12-20
 */
@Service
public class AttrTypeServiceImpl implements IAttrTypeService 
{
    @Resource
    private AttrTypeMapper attrTypeMapper;

    /**
     * 查询属性类型
     * 
     * @param id 属性类型主键
     * @return 属性类型
     */
    @Override
    public AttrType selectAttrTypeById(String id)
    {
        return attrTypeMapper.selectAttrTypeById(id);
    }

    /**
     * 查询属性类型列表
     * 
     * @param attrType 属性类型
     * @return 属性类型
     */
    @Override
    public List<AttrType> selectAttrTypeList(AttrType attrType)
    {
        return attrTypeMapper.selectAttrTypeList(attrType);
    }

    /**
     * 新增属性类型
     * 
     * @param attrType 属性类型
     * @return 结果
     */
    @Override
    public int insertAttrType(AttrType attrType)
    {
        return attrTypeMapper.insertAttrType(attrType);
    }

    /**
     * 修改属性类型
     * 
     * @param attrType 属性类型
     * @return 结果
     */
    @Override
    public int updateAttrType(AttrType attrType)
    {
        return attrTypeMapper.updateAttrType(attrType);
    }

    /**
     * 修改属性类型名称多语言配置
     * @param attrTypeId 属性类型ID
     * @param attrTypeNameLang 属性类型名称语言包
     * @return
     */

    public int updateAttrTypeNameLang(Long attrTypeId, LangMgr attrTypeNameLang){
        AttrType attrType = new AttrType();
        attrType.setId(attrTypeId);
        attrType.setAttrTypeNameLang(attrTypeNameLang);
        return attrTypeMapper.updateAttrType(attrType);
    }

    /**
     * 批量删除属性类型
     * 
     * @param ids 需要删除的属性类型主键
     * @return 结果
     */
    @Override
    public int deleteAttrTypeByIds(String[] ids)
    {
        return attrTypeMapper.deleteAttrTypeByIds(ids);
    }

    /**
     * 删除属性类型信息
     * 
     * @param id 属性类型主键
     * @return 结果
     */
    @Override
    public int deleteAttrTypeById(String id)
    {
        return attrTypeMapper.deleteAttrTypeById(id);
    }
}
