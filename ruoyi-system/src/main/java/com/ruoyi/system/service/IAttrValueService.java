package com.ruoyi.system.service;

import com.ruoyi.system.domain.AttrValue;
import com.ruoyi.system.domain.LangMgr;

import java.util.List;

/**
 * 属性值Service接口
 * 
 * @author ruoyi
 * @date 2024-12-20
 */
public interface IAttrValueService 
{
    /**
     * 查询属性值
     * 
     * @param id 属性值主键
     * @return 属性值
     */
    public AttrValue selectAttrValueById(String id);

    /**
     * 查询属性值列表
     * 
     * @param attrValue 属性值
     * @return 属性值集合
     */
    public List<AttrValue> selectAttrValueList(AttrValue attrValue);

    /**
     * 新增属性值
     * 
     * @param attrValue 属性值
     * @return 结果
     */
    public int insertAttrValue(AttrValue attrValue);

    /**
     * 修改属性值
     * 
     * @param attrValue 属性值
     * @return 结果
     */
    public int updateAttrValue(AttrValue attrValue);

    /**
     * 修改属性值名称多语言配置
     * @param attrValueId 属性值ID
     * @param attrValueNameLang 属性值名称语言包
     * @return
     */

    public int updateAttrValueNameLang(Long attrValueId, LangMgr attrValueNameLang);

    /**
     * 批量删除属性值
     * 
     * @param ids 需要删除的属性值主键集合
     * @return 结果
     */
    public int deleteAttrValueByIds(String[] ids);

    /**
     * 删除属性值信息
     * 
     * @param id 属性值主键
     * @return 结果
     */
    public int deleteAttrValueById(String id);
}
