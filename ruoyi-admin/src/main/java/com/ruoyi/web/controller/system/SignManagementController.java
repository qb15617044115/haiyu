package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SignManagement;
import com.ruoyi.system.service.ISignManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//签到管理
@RestController
public class SignManagementController {

    @Autowired
    private ISignManagementService iSignManagementService;

    @PostMapping("/AddSignManagement")
    public AjaxResult AddSignManagement(@RequestBody SignManagement signManagement){
        signManagement.setId(1);
        System.err.println("测试签到前:"+ signManagement.toString());
        try {
            iSignManagementService.updateparameter(signManagement);
        }catch (Exception e){
            System.err.println("signManagement: 判断错误");
            e.printStackTrace();
        }
        return AjaxResult.success(iSignManagementService.querygetAll(signManagement.getId()));
    }

    @PostMapping("/GetSignManagement")
    public AjaxResult GetSignManagement(SignManagement signManagement){
        signManagement.setId(1);
        System.err.println("测试签到后:"+ signManagement.toString());
        return AjaxResult.success(iSignManagementService.querygetAll(signManagement.getId()));
    }

}