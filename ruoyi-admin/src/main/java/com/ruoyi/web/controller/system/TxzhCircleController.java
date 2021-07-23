package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.TxzhCircle;
import com.ruoyi.system.service.ITxzhCircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TxzhCircleController {

    @Autowired
    private ITxzhCircleService iTxzhCircleService;

    @PostMapping("/index/deleteTxzhCircleByid")
    public AjaxResult deleteTxzhCircleByid(@RequestBody TxzhCircle txzhCircle,TxzhCircle txci){
        System.err.println("测试删除: "+txzhCircle.getId() );

        if (txzhCircle.getId() != null){
            iTxzhCircleService.deleteCircle(txzhCircle);
            System.err.println("删除成功,页面删除成功");
        }else {
            return AjaxResult.error("删除ID不能为空");
        }
            return AjaxResult.success();
    }

    @PostMapping("/index/updateTxzhCircleByid")
    public AjaxResult updateTxzhCircleByid(@RequestBody TxzhCircle txzhCircle){

        System.err.println("测试隐藏:  "+"  HiddenContent: "+txzhCircle.getHiddenContent()+" | "+txzhCircle.getId());
        //   0 未隐藏  1 隐藏
//        try{
//            if (txzhCircle.getId() != null){
                iTxzhCircleService.updatehiddenContent(txzhCircle.getId(),txzhCircle.getHiddenContent());
                System.err.println("修改成功,隐藏成功");
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//            return AjaxResult.error("用户id,隐藏hiddenContent不能为空");
//        }
        return AjaxResult.success(iTxzhCircleService.queryByid(txzhCircle.getId()));
    }

        @PostMapping("/index/getList")
        public AjaxResult getList(TxzhCircle txzhCircle){
            System.err.println("测试列表数据"+txzhCircle.toString());
            try{
                List<TxzhCircle> gL=iTxzhCircleService.queryList(txzhCircle);
                System.err.println("查询成功");
                System.err.println("查询成功"+gL);
                return AjaxResult.success(gL);
            }catch(Exception e){
                e.printStackTrace();
            }
            return AjaxResult.success();
        }
}
