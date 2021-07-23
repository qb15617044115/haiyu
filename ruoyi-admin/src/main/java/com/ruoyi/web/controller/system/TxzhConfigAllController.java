package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.TxzhConfigAll;
import com.ruoyi.system.service.ITxzhConfigAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/configAll")
public class TxzhConfigAllController {

    @Autowired
    private ITxzhConfigAllService txzhConfigAllService;
    // 获取configAll
    @PostMapping("/listConfigAll")
    public AjaxResult listConfigAll(){
        return txzhConfigAllService.listConfigAll();
    }
    // 添加或修改configAll
    @PostMapping("/addOrUpdateConfigAll")
    public AjaxResult addConfigAll(@RequestBody TxzhConfigAll txzhConfigAll){
        return txzhConfigAllService.addConfigAll(txzhConfigAll);
    }
    // 获取实时动态状态
    @PostMapping("/getRTK")
    public AjaxResult getRTK(){
        return txzhConfigAllService.getRTK();
    }
    // 修改实时动态状态
    @PostMapping("/updateRTKStatus")
    public AjaxResult updateRTKStatus(@RequestBody TxzhConfigAll txzhConfigAll){
        return txzhConfigAllService.updateRTKStatus(txzhConfigAll);
    }
    // 获取积分管理
    @PostMapping("/listIntegralConfig")
    public AjaxResult listIntegralConfig(){
        return txzhConfigAllService.listIntegralConfig();
    }
    // 修改等级积分
    @PostMapping("/updateIntegralConfig")
    public AjaxResult updateIntegralConfig(@RequestBody TxzhConfigAll txzhConfigAll){
        return txzhConfigAllService.updateIntegralConfig(txzhConfigAll);
    }
}
