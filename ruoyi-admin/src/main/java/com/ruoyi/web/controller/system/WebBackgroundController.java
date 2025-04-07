package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.service.IWebBackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商户后台
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/homePage")
public class WebBackgroundController {

    @Autowired
    private IWebBackgroundService webBackgroundService;

    /**
     * 后台首页报表
     */
    @GetMapping(value = "/systemHomePageReport")
    public AjaxResult systemHomePageReport(){
        return AjaxResult.success(webBackgroundService.systemHomePageReport(null));
    }
}
