package com.ruoyi.system.service;

import com.ruoyi.system.domain.AttrType;
import com.ruoyi.system.domain.LangMgr;

import java.util.List;

/**
 * 属性类型Service接口
 * 
 * @author ruoyi
 * @date 2024-12-20
 */
public interface IAttrTypeService 
{
    /**
     * 查询属性类型
     * 
     * @param id 属性类型主键
     * @return 属性类型
     */
    public AttrType selectAttrTypeById(String id);

    /**
     * 查询属性类型列表
     * 
     * @param attrType 属性类型
     * @return 属性类型集合
     */
    public List<AttrType> selectAttrTypeList(AttrType attrType);

    /**
     * 新增属性类型
     * 
     * @param attrType 属性类型
     * @return 结果
     */
    public int insertAttrType(AttrType attrType);

    /**
     * 修改属性类型
     * 
     * @param attrType 属性类型
     * @return 结果
     */
    public int updateAttrType(AttrType attrType);

    /**
     * 修改属性类型名称多语言配置
     * @param attrTypeId 属性类型ID
     * @param attrTypeNameLang 属性类型名称语言包
     * @return
     */

    public int updateAttrTypeNameLang(Long attrTypeId, LangMgr attrTypeNameLang);

    /**
     * 批量删除属性类型
     * 
     * @param ids 需要删除的属性类型主键集合
     * @return 结果
     */
    public int deleteAttrTypeByIds(String[] ids);

    /**
     * 删除属性类型信息
     * 
     * @param id 属性类型主键
     * @return 结果
     */
    public int deleteAttrTypeById(String id);
}
