package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * 代理信息
 *
 * @author ruoyi
 * 日志优化完成
 */
@RestController
@RequestMapping("/system/agentUser")
public class AgentUserController extends BaseController {

//    @Autowired
//    private IAgentUserService agentUserService;

    @Autowired
    private ISysUserService userService;

    /**
     * 获取代理用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:agentUser:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUser user) {
        startPage();
        return getDataTable(new ArrayList<>());
    }

    /**
     * 获取代理用户信息列表
     */
    @GetMapping("/listNoPreAuthorize")
    public TableDataInfo listNoPreAuthorize(SysUser user) {
        startPage();
        return getDataTable(new ArrayList<>());
    }
}
