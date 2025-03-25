package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.PopUp;

import java.util.List;

/**
 * 弹窗信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-21
 */
public interface PopUpMapper 
{
    /**
     * 查询弹窗信息
     * 
     * @param id 弹窗信息主键
     * @return 弹窗信息
     */
    public PopUp selectPopUpById(Long id);

    /**
     * 查询弹窗信息列表
     * 
     * @param popUp 弹窗信息
     * @return 弹窗信息集合
     */
    public List<PopUp> selectPopUpList(PopUp popUp);

    /**
     * 新增弹窗信息
     * 
     * @param popUp 弹窗信息
     * @return 结果
     */
    public int insertPopUp(PopUp popUp);

    /**
     * 修改弹窗信息
     * 
     * @param popUp 弹窗信息
     * @return 结果
     */
    public int updatePopUp(PopUp popUp);

    /**
     * 删除弹窗信息
     * 
     * @param id 弹窗信息主键
     * @return 结果
     */
    public int deletePopUpById(Long id);

    /**
     * 批量删除弹窗信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePopUpByIds(Long[] ids);
}
