package com.ruoyi.web.controller.customer;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.TxzhCustomerAdmin;
import com.ruoyi.system.service.ITxchCustomerAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminSystemController {

    @Autowired
    private ITxchCustomerAdminService iTxchCustomerAdminService;

    @GetMapping("list")
    public AjaxResult queryCustomer(@PathVariable("deptId") Long deptId){
        List<TxzhCustomerAdmin> txzhCustomerAdmin=this.iTxchCustomerAdminService.getAllCustomer(deptId);
        System.out.println(txzhCustomerAdmin);
        return AjaxResult.success(txzhCustomerAdmin);
    }





}
