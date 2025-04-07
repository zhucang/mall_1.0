package com.ruoyi.system.service;


import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.system.domain.LangMgr;

import java.util.List;
import java.util.Map;

public interface IWebBackgroundService {

    /**
     * 首页报表
     */
    public Map<String, Object> systemHomePageReport(Long sellerId);
}
