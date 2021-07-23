package com.ruoyi.system.service;

import com.ruoyi.system.domain.Withdrawal;

import java.util.List;

public interface IWithdrawalService {

//      void addWithdrawal(Withdrawal withdrawal);

      List<Withdrawal> queryWithdrawal(Integer  id);

      void  updatewithdrawal(Withdrawal withdrawal);



}
