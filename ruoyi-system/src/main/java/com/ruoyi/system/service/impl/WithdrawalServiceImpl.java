package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.Withdrawal;
import com.ruoyi.system.mapper.WithdrawalMapper;
import com.ruoyi.system.service.IWithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WithdrawalServiceImpl  implements  IWithdrawalService {

    @Autowired
    private  WithdrawalMapper withdrawalMapper;

//    @Override
//    public void addWithdrawal(Withdrawal withdrawal) {
//        withdrawalMapper.addWithdrawal(withdrawal);
//    }

    @Override
    public List<Withdrawal> queryWithdrawal(Integer id) {
        return withdrawalMapper.queryWithdrawal(id);
    }

    @Override
    public void updatewithdrawal(Withdrawal withdrawal) {
          withdrawalMapper.updatewithdrawal(withdrawal);
    }



}
