package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.AttrValue;
import com.ruoyi.system.domain.LangMgr;
import com.ruoyi.system.mapper.AttrValueMapper;
import com.ruoyi.system.service.IAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 属性值Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-12-20
 */
@Service
public class AttrValueServiceImpl implements IAttrValueService 
{
    @Autowired
    private AttrValueMapper attrValueMapper;

    /**
     * 查询属性值
     * 
     * @param id 属性值主键
     * @return 属性值
     */
    @Override
    public AttrValue selectAttrValueById(String id)
    {
        return attrValueMapper.selectAttrValueById(id);
    }

    /**
     * 查询属性值列表
     * 
     * @param attrValue 属性值
     * @return 属性值
     */
    @Override
    public List<AttrValue> selectAttrValueList(AttrValue attrValue)
    {
        return attrValueMapper.selectAttrValueList(attrValue);
    }

    /**
     * 新增属性值
     * 
     * @param attrValue 属性值
     * @return 结果
     */
    @Override
    public int insertAttrValue(AttrValue attrValue)
    {
        return attrValueMapper.insertAttrValue(attrValue);
    }

    /**
     * 修改属性值
     * 
     * @param attrValue 属性值
     * @return 结果
     */
    @Override
    public int updateAttrValue(AttrValue attrValue)
    {
        return attrValueMapper.updateAttrValue(attrValue);
    }

    /**
     * 修改属性值名称多语言配置
     * @param attrValueId 属性值ID
     * @param attrValueNameLang 属性值名称语言包
     * @return
     */
    @Override

    public int updateAttrValueNameLang(Long attrValueId, LangMgr attrValueNameLang){
        AttrValue attrValue = new AttrValue();
        attrValue.setId(attrValueId);
        attrValue.setAttrValueNameLang(attrValueNameLang);
        return attrValueMapper.updateAttrValue(attrValue);
    }

    /**
     * 批量删除属性值
     * 
     * @param ids 需要删除的属性值主键
     * @return 结果
     */
    @Override
    public int deleteAttrValueByIds(String[] ids)
    {
        return attrValueMapper.deleteAttrValueByIds(ids);
    }

    /**
     * 删除属性值信息
     * 
     * @param id 属性值主键
     * @return 结果
     */
    @Override
    public int deleteAttrValueById(String id)
    {
        return attrValueMapper.deleteAttrValueById(id);
    }
}
