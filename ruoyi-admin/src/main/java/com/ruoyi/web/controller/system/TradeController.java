package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.TxzhUser;
import com.ruoyi.system.service.ITradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeController {

    @Autowired
    private ITradeService iTradeService;

//    @PostMapping("/updateTrade")
//    public AjaxResult updateTrade(@RequestBody TxzhUser txzhUser ){
//        System.err.println("测试修改交易密码"+txzhUser.toString());
//        String tradepassword=txzhUser.getTradePassword();
//        String traPassword=SecurityUtils.encryptPassword(tradepassword);
//       iTradeService.UpdateTradePassword(txzhUser.getId(),traPassword);
//        return AjaxResult.success("修改成功");
//    }
    @PostMapping("/updateTrade")
    public AjaxResult updateTrade(@RequestBody TxzhUser txzhUser ){
        System.err.println("测试修改交易密码"+txzhUser.toString());

        try{
            if (!txzhUser.getTradePassword().matches("^[0-9_]+$")){
                return AjaxResult.success("输入非法");
            }else {
                String tradepassword=txzhUser.getTradePassword();
                String traPassword=SecurityUtils.encryptPassword(tradepassword);
                iTradeService.UpdateTradePassword(txzhUser.getId(),traPassword);
                return AjaxResult.success("修改成功");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return AjaxResult.success();
    }

}
