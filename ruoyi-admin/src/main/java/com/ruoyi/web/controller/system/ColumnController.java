package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysColumn;
import com.ruoyi.system.service.ISysColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ColumnController {

    @Autowired
    private ISysColumnService iSysColumnService;


    @PostMapping("/index/querySysColumn")
    public AjaxResult querySysColumn(@RequestBody SysColumn sysColumn){

               System.err.println("测试直播状态栏列表:"+sysColumn);
               if (sysColumn.getSysUserId()!=null){
               List<SysColumn> gets=iSysColumnService.queryList(sysColumn);
               System.err.println("測試"+gets);
                return AjaxResult.success(gets);
            }
        return AjaxResult.success();
    }

    @PostMapping("/index/insertColumn")
    public AjaxResult insertColumn(@RequestBody SysColumn sysColumn){
            System.err.println("测试增加栏目"+sysColumn.toString());
        try{

            iSysColumnService.insertColumn(sysColumn);
            System.err.println("新增成功");
            List<SysColumn> neirong=iSysColumnService.queryUpdateByid(sysColumn.getId());
            System.err.println("内容"+neirong);
            return AjaxResult.success(neirong);
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error();
        }
            }

            @PostMapping("/index/updateColumn")
            public AjaxResult updateColumn(@RequestBody SysColumn sysColumn){

                System.err.println("测试修改栏目"+sysColumn.toString());
                try{
                    iSysColumnService.updateColumn(sysColumn);
                    System.err.println("修改成功");
                    List<SysColumn> neirong=iSysColumnService.queryUpdateByid(sysColumn.getId());
                    return AjaxResult.success(neirong);
                }catch(Exception e){
                    e.printStackTrace();
                    return AjaxResult.error();
                }
            }

            @PostMapping("/index/deleteColumn")
            public AjaxResult deleteColumn(@RequestBody SysColumn sysColumn){
                    System.err.println("测试直播栏目删除"+sysColumn.getId());
                    iSysColumnService.deleteColumn(sysColumn);
                    System.err.println("删除成功");
                    return AjaxResult.success();
            }

}
