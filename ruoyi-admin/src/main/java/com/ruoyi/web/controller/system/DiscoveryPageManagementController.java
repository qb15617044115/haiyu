package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.DiscoveryPageManagement;
import com.ruoyi.system.service.IDiscoveryPageManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


//发现页管理
@RestController
public class DiscoveryPageManagementController {

    @Autowired
    private IDiscoveryPageManagementService iDiscoveryPageManagementService ;

    @PostMapping("/index/getAllDiscoveryPageManagement")
    public AjaxResult getAllDiscoveryPageManagement(@RequestBody DiscoveryPageManagement discoveryPageManagement){
        System.err.println("测试发现页上:"+discoveryPageManagement.toString());
        try{
            List<DiscoveryPageManagement> gets=iDiscoveryPageManagementService.querymoduleChoice(discoveryPageManagement);
            return AjaxResult.success(gets);
        }catch(Exception e){
            e.printStackTrace();
        }
        return AjaxResult.success();
    }
    @PostMapping("/index/PageManagement")
    public AjaxResult updateDiscoveryPageManagement(@RequestBody DiscoveryPageManagement discoveryPageManagement){
        return iDiscoveryPageManagementService.updateDiscoveryPageManagement(discoveryPageManagement);
    }

    @PostMapping("/index/insertDiscover")
    private AjaxResult insertDiscover(@RequestBody DiscoveryPageManagement discoveryPageManagement){

        System.err.println("测试新增："+discoveryPageManagement.toString());
        int names = iDiscoveryPageManagementService.queryDiscoveryName(discoveryPageManagement.getMenuname());
        if (names > 0) {
            System.err.println("新增失败，已重复");
            return AjaxResult.error("名称重复,添加失败");
        }
        System.err.println("新增成功");
        return AjaxResult.success(iDiscoveryPageManagementService.insertDiscovery(discoveryPageManagement));
    }

    @PostMapping("/index/deleteDiscover")
    public AjaxResult  deleteDiscover(@RequestBody DiscoveryPageManagement discoveryPageManagement) {
        System.err.println("测试自定义发现页删除ID为："+discoveryPageManagement.getId());
        iDiscoveryPageManagementService.deleteDiscovery(discoveryPageManagement);
        System.err.println("删除成功");
        return AjaxResult.success();
    }

    @PostMapping("/index/updateDiscovers")
    public  AjaxResult  updateDiscovers(@RequestBody DiscoveryPageManagement discoveryPageManagement){

        System.err.println("测试修改:"+discoveryPageManagement.toString());
        int names = iDiscoveryPageManagementService.queryDiscoveryName(discoveryPageManagement.getMenuname());
        if (names > 0) {
            System.err.println("修改失败，已重复");
            return AjaxResult.error("名称重复,修改失败");
        }
        try{
            iDiscoveryPageManagementService.updateDiscovery(discoveryPageManagement);
            System.err.println("修改成功");
        }catch(Exception e){
            System.err.println("修改失败");
            e.printStackTrace();
        }
        return AjaxResult.success(iDiscoveryPageManagementService.queryByQid(discoveryPageManagement.getId()));
    }
}