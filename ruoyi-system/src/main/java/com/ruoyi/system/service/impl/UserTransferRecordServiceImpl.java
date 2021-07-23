package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.TxzhUser;
import com.ruoyi.system.domain.UserTransferRecord;
import com.ruoyi.system.mapper.UserTransferRecordMapper;
import com.ruoyi.system.service.IUserTransferRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserTransferRecordServiceImpl implements IUserTransferRecordService {
    @Autowired
    private UserTransferRecordMapper userTransferRecordMapper;

    @Override
    public AjaxResult giveRedPacketsLog(TxzhUser txzhUser) {
        if(txzhUser.getId() == null){
            return AjaxResult.error("用户id不能为空");
        }
        List<UserTransferRecord> list = userTransferRecordMapper.listByUserId(txzhUser);
        int count = userTransferRecordMapper.listCountByUserId(txzhUser);
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("count",count);
        return AjaxResult.success(map);
    }
}
