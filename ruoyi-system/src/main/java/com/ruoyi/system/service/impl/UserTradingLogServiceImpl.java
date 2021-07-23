package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.TxzhUser;
import com.ruoyi.system.domain.UserTradingLog;
import com.ruoyi.system.mapper.UserTradingLogServiceMapper;
import com.ruoyi.system.service.IUserTradingLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserTradingLogServiceImpl implements IUserTradingLogService {
    @Autowired
    private UserTradingLogServiceMapper userTradingLogServiceMapper;

    @Override
    public AjaxResult payList(TxzhUser txzhUser) throws Exception {
        Map<String,Object> map = new HashMap<>();
        if(txzhUser.getId() == null){
            return AjaxResult.error("参数异常");
        }
        List<UserTradingLog> list = userTradingLogServiceMapper.selectByUserId(txzhUser);
        int i = userTradingLogServiceMapper.selectCountByUserId(txzhUser);
        // 查询出总支出
        BigDecimal totalOut = userTradingLogServiceMapper.selectTotalByUserId(txzhUser,1);
        BigDecimal totalIn = userTradingLogServiceMapper.selectTotalByUserId(txzhUser,2);
        map.put("list",list);
        map.put("count",i);
        map.put("totalOut",totalOut);
        map.put("totalIn",totalIn);
        return AjaxResult.success(map);
    }

    @Override
    public void insertTradingLog(UserTradingLog userTradingLog) {
        userTradingLogServiceMapper.insertTradingLog(userTradingLog);
    }
}
