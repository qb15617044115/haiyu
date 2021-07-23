package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.TxzhUser;

public interface IUserTransferRecordService {
    AjaxResult giveRedPacketsLog(TxzhUser txzhUser);
}
