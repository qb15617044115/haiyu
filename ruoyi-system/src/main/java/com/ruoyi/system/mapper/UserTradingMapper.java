package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SignManagement;
import com.ruoyi.system.domain.UserTradingLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserTradingMapper {

    List<UserTradingLog> queryUserTradingLog(UserTradingLog userTradingLog);

    List<SignManagement> querygetAll(@Param("id") Integer id);

    List<UserTradingLog> queryUserTradingLogByUserName(UserTradingLog userTradingLog);

    List<UserTradingLog> queryUserTradingLogByNickName(UserTradingLog userTradingLog);
}
