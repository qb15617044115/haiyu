package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.TxzhUser;
import com.ruoyi.system.domain.UserTradingLog;

public interface IUserTradingLogService {

    AjaxResult payList(TxzhUser txzhUser) throws Exception;

    void insertTradingLog(UserTradingLog userTradingLog);
}
