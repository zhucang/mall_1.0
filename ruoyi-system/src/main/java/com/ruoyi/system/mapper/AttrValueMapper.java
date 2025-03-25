package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.AttrValue;

import java.util.List;

/**
 * 属性值Mapper接口
 * 
 * @author ruoyi
 * @date 2024-12-20
 */
public interface AttrValueMapper 
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
     * 删除属性值
     * 
     * @param id 属性值主键
     * @return 结果
     */
    public int deleteAttrValueById(String id);

    /**
     * 批量删除属性值
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAttrValueByIds(String[] ids);
}
