package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.logDict.SiteMessageLogDict;
import com.ruoyi.system.domain.SiteMessage;
import com.ruoyi.system.service.ISiteMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户通知Controller
 * 
 * @author ruoyi
 * @date 2023-11-10
 */
@RestController
@RequestMapping("/system/siteMessage")
public class SiteMessageController extends BaseController
{
    @Autowired
    private ISiteMessageService siteMessageService;

    /**
     * 查询用户通知列表
     */
    @PreAuthorize("@ss.hasPermi('system:siteMessage:list')")
    @GetMapping("/list")
    public TableDataInfo list(SiteMessage siteMessage)
    {
        startPage();
        startOrderBy("id desc");
        siteMessage.setIsPrivate(0);
        List<SiteMessage> list = siteMessageService.selectSiteMessageList(siteMessage);
        return getDataTable(list);
    }

    /**
     * 获取用户通知详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:siteMessage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(siteMessageService.selectSiteMessageById(id));
    }

    /**
     * 新增用户通知
     */
    @PreAuthorize("@ss.hasPermi('system:siteMessage:add')")
    @Log(title = "新增用户通知", businessType = BusinessType.INSERT,dict = SiteMessageLogDict.class)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody SiteMessage siteMessage)
    {
        if (siteMessage.getUserId() == null){
            throw new ServiceException("请选择需要通知的用户");
        }
        siteMessage.setIsPrivate(0);
        return toAjax(siteMessageService.insertSiteMessage(siteMessage));
    }

    /**
     * 修改用户通知
     */
    @PreAuthorize("@ss.hasPermi('system:siteMessage:edit')")
    @Log(title = "新增用户通知", businessType = BusinessType.UPDATE,dict = SiteMessageLogDict.class)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody SiteMessage siteMessage)
    {
        if (siteMessage.getId() == null){
            throw new ServiceException("请选择需要修改的选项");
        }
        if (siteMessage.getUserId() == null){
            throw new ServiceException("请选择需要通知的用户");
        }
        return toAjax(siteMessageService.updateSiteMessage(siteMessage));
    }

    /**
     * 修改公告标题多语言
     */
    @PreAuthorize("@ss.hasPermi('system:siteMessage:edit')")
    @Log(title = "修改公告标题多语言", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping(value = "updateMsgTitleLang")
    public AjaxResult updateMsgTitleLang(@RequestBody SiteMessage siteMessage)
    {
        if (siteMessage.getId() == null){
            throw new ServiceException("请选择需要修改的选项");
        }
        return toAjax(siteMessageService.updateMsgTitleLang(siteMessage.getId(),siteMessage.getMsgTitleLang()));
    }

    /**
     * 修改公告内容多语言
     */
    @PreAuthorize("@ss.hasPermi('system:siteMessage:edit')")
    @Log(title = "修改公告内容多语言", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping(value = "updateMsgContentLang")
    public AjaxResult updateMsgContentLang(@RequestBody SiteMessage siteMessage)
    {
        if (siteMessage.getId() == null){
            throw new ServiceException("请选择需要修改的选项");
        }
        return toAjax(siteMessageService.updateMsgContentLang(siteMessage.getId(),siteMessage.getMsgContentLang()));
    }

    /**
     * 删除用户通知
     */
    @PreAuthorize("@ss.hasPermi('system:siteMessage:remove')")
    @Log(title = "删除用户通知", businessType = BusinessType.DELETE,dict = SiteMessageLogDict.class,
            saveParamNames = {"id","msgTitle","siteMessages"})
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(siteMessageService.deleteSiteMessageByIds(ids));
    }
}
