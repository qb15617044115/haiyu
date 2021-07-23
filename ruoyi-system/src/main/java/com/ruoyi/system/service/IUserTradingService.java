package com.ruoyi.system.service;

import com.ruoyi.system.domain.SignManagement;
import com.ruoyi.system.domain.UserTradingLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserTradingService {

    List<UserTradingLog> queryUserTradingLog(UserTradingLog userTradingLog);

    List<SignManagement> querygetAll(@Param("id") Integer id);

    List<UserTradingLog> queryUserTradingLogByUserName(UserTradingLog userTradingLog);

    List<UserTradingLog> queryUserTradingLogByNickName(UserTradingLog userTradingLog);
}
