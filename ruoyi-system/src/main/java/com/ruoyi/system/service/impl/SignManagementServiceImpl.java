package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SignManagement;
import com.ruoyi.system.mapper.SignManagementMapper;
import com.ruoyi.system.service.ISignManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignManagementServiceImpl implements ISignManagementService {

    @Autowired
    private SignManagementMapper signManagementMapper;

    @Override
    public List<SignManagement> InsertMoneyDays(SignManagement signManagement) {
        return signManagementMapper.InsertMoneyDays(signManagement);
    }

    @Override
    public void UpdateSwitch(Integer id, Integer state) {
        signManagementMapper.UpdateSwitch(id, state);
    }

    @Override
    public List<SignManagement> querygetAll(Integer id ) {
        return signManagementMapper.querygetAll(id);
    }

    @Override
    public void updateparameter(SignManagement signManagement) {
        signManagementMapper.updateparameter(signManagement);
    }


}
