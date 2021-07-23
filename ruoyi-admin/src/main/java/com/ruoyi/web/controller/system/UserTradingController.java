package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.UserTradingLog;
import com.ruoyi.system.service.IUserTradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//        List<SignManagement> queryMax=iUserTradingService.querygetAll(signManagement.getId());
//        try{
//            for (SignManagement quer: queryMax){
//                float fMax=quer.getMaximumnumberofdailycheckins();
//                float fAmount=quer.getContinuoussigninamount();
//                if (Float.parseFloat(userTradingLog.getTradingAmount().toString()) >= fMax   &&
//                        Float.parseFloat(userTradingLog.getTradingAmount().toString()) <= fAmount){
//                    System.err.println("测试1"+ qut);
//                    return AjaxResult.success("连续签到红包"+qut);
//                }else {
//                    System.err.println("测试2"+qut);
//                    return AjaxResult.success(qut);
//                }}
//        }catch(Exception e){
//            System.err.println("编辑错误");
//            e.printStackTrace();
//        }


@RestController
public class UserTradingController {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private IUserTradingService iUserTradingService;

    @PostMapping("/index/queryUserTrading")
    public AjaxResult queryUserTrading(){

        UserTradingLog userTradingLog = new UserTradingLog();
        System.err.println("测试接收签到红包流水"+userTradingLog.toString());
        userTradingLog.setTradingSource("签到红包");
        List<UserTradingLog> qut=iUserTradingService.queryUserTradingLog(userTradingLog);
        return AjaxResult.success(qut);
    }

    @PostMapping("/index/queryByName")
    public AjaxResult queryByName(@RequestBody UserTradingLog userTradingLog) {
        System.err.println("测试精准搜索框: " + userTradingLog.getUserName());
        if (userTradingLog.getUserName() != null) {
            List<UserTradingLog> ge1 = iUserTradingService.queryUserTradingLogByUserName(userTradingLog);
            System.err.println("测试精准查询结果" + ge1);
            return AjaxResult.success(ge1);
        }
        System.err.println("测试模糊搜索框: "+userTradingLog.getNickName());
        if (userTradingLog.getNickName() != null) {

            List<UserTradingLog> ge2 = iUserTradingService.queryUserTradingLogByNickName(userTradingLog);
            System.err.println("测试模糊查询结果" + ge2);
            return AjaxResult.success(ge2);
        }
       return  AjaxResult.success("请输入昵称/账户");
    }

}
