package com.ruoyi.web.controller.system;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.Withdrawal;
import com.ruoyi.system.service.IWithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WithdrawalController {

//    //提现管理
//    @Autowired
//    private IWithdrawalService iWithdrawalService;
//
//    @PostMapping("/InsertWithdrawal")//system/menu/index
//    public List<Withdrawal> InsertWithdrawal(@RequestBody Withdrawal withdrawal){
//        System.err.println("测试提现管理 : " + withdrawal.toString());
//        try {
//                iWithdrawalService.updatewithdrawal(withdrawal);
//                System.err.println("Cash management: 修改设置成功");
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        List<Withdrawal> lw=iWithdrawalService.queryWithdrawal(withdrawal.getId());
//        return lw;
//    }

    //提现管理
    @Autowired
    private IWithdrawalService iWithdrawalService;

    @PostMapping("/InsertWithdrawal")//system/menu/index
    public AjaxResult InsertWithdrawal(@RequestBody Withdrawal withdrawal){
        withdrawal.setId(1);
        System.err.println("测试提现管理 : " + withdrawal.toString());
        try {
                iWithdrawalService.updatewithdrawal(withdrawal);
                System.err.println("Cash management: 修改设置成功");
        }catch(Exception e){
            e.printStackTrace();
        }
        return AjaxResult.success(iWithdrawalService.queryWithdrawal(withdrawal.getId()));
    }

    @PostMapping("/GetWithdrawal")
    public AjaxResult GetWithdrawal( Withdrawal withdrawal){
        System.err.println("测试"+withdrawal.toString());
        withdrawal.setId(1);
        return AjaxResult.success(iWithdrawalService.queryWithdrawal(withdrawal.getId()));
    }
}
