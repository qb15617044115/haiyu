package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SignManagement;
import com.ruoyi.system.domain.UserTradingLog;
import com.ruoyi.system.mapper.UserTradingMapper;
import com.ruoyi.system.service.IUserTradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTradingServiceImpl implements IUserTradingService {

    @Autowired
    private UserTradingMapper userTradingMapper;

    @Override
    public List<UserTradingLog> queryUserTradingLog(UserTradingLog userTradingLog) {
        return userTradingMapper.queryUserTradingLog(userTradingLog);
    }

    @Override
    public List<SignManagement> querygetAll(Integer id) {
        return userTradingMapper.querygetAll(id);
    }

    @Override
    public List<UserTradingLog> queryUserTradingLogByUserName(UserTradingLog userTradingLog) {
        return userTradingMapper.queryUserTradingLogByUserName(userTradingLog);
    }

    @Override
    public List<UserTradingLog> queryUserTradingLogByNickName(UserTradingLog userTradingLog) {
        return userTradingMapper.queryUserTradingLogByNickName(userTradingLog);
    }
}
