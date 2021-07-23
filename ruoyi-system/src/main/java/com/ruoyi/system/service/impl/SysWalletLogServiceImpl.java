package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysWalletLog;
import com.ruoyi.system.mapper.SysWalletLogMapper;
import com.ruoyi.system.service.ISysWalletLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysWalletLogServiceImpl implements ISysWalletLogService {
    @Autowired
    private SysWalletLogMapper sysWalletLogMapper;

    @Override
    public AjaxResult listSysWalletLog(SysWalletLog sysWalletLog) {
        Map<String,Object> map = new HashMap<>();
        List<SysWalletLog> list = sysWalletLogMapper.list(sysWalletLog);
        int count = sysWalletLogMapper.listCount(sysWalletLog);
        map.put("list",list);
        map.put("count",count);
        return AjaxResult.success(map);
    }
}
