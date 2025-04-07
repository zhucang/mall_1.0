package com.ruoyi.web.controller.seller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
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
@RequestMapping("/seller/homePage")
public class Seller_WebBackgroundController {

    @Autowired
    private IWebBackgroundService webBackgroundService;

    /**
     * 商户首页报表
     */
    @GetMapping(value = "/sellerHomePageReport")
    public AjaxResult sellerHomePageReport(){
        //商户ID
        Long sellerId = SecurityUtils.getUserId();
        return AjaxResult.success(webBackgroundService.systemHomePageReport(sellerId));
    }
}
