package com.ruoyi.web.controller.api;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.SystemGoodsInfo;
import com.ruoyi.system.service.ISystemGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统商品信息Controller
 * 
 * @author ruoyi
 * @date 2024-12-03
 */
@RestController
@RequestMapping("/api/systemGoodsInfo")
public class ApiSystemGoodsInfoController extends BaseController
{
    @Autowired
    private ISystemGoodsInfoService systemGoodsInfoService;

    /**
     * 查询系统商品信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SystemGoodsInfo systemGoodsInfo)
    {
        startPage();
        List<SystemGoodsInfo> list = systemGoodsInfoService.selectSystemGoodsInfoList(systemGoodsInfo);
        return getDataTable(list);
    }
}
