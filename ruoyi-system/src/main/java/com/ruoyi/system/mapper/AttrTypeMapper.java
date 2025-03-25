package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.AttrType;

import java.util.List;

/**
 * 属性类型Mapper接口
 * 
 * @author ruoyi
 * @date 2024-12-20
 */
public interface AttrTypeMapper 
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
     * 删除属性类型
     * 
     * @param id 属性类型主键
     * @return 结果
     */
    public int deleteAttrTypeById(String id);

    /**
     * 批量删除属性类型
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAttrTypeByIds(String[] ids);
}
