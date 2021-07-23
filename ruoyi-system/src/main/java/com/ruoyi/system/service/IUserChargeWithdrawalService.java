package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.TxzhUser;
import com.ruoyi.system.domain.vo.UserChargeWithdrawal;

import java.util.List;
import java.util.Map;

public interface IUserChargeWithdrawalService {

    Map<String,Object> getTopUpList(TxzhUser txzhUser) throws Exception;

    void updateTopUpState(UserChargeWithdrawal userChargeWithdrawal) throws Exception;

    Map<String,Object> getWithdrawalList(TxzhUser txzhUser) throws Exception;

    void updateWithdrawalList(UserChargeWithdrawal userChargeWithdrawal) throws Exception;

    void completeWithdrawal(UserChargeWithdrawal userChargeWithdrawal) throws Exception;
}
