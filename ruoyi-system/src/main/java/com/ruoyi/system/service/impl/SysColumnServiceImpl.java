package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SysColumn;
import com.ruoyi.system.mapper.SysColumnMapper;
import com.ruoyi.system.service.ISysColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class SysColumnServiceImpl implements ISysColumnService {

    @Autowired
    private SysColumnMapper sysColumnMapper;

    @Override
    public List<SysColumn> queryList(@RequestBody SysColumn sysColumn) {

        return sysColumnMapper.queryList(sysColumn);
    }

    @Override
    public void insertColumn(SysColumn sysColumn) {
         sysColumnMapper.insertColumn(sysColumn);
    }

    @Override
    public List<String> getTuPian(String moduleType) {
        return sysColumnMapper.getTuPian(moduleType);
    }

    @Override
    public void updateColumn(SysColumn sysColumn) {
         sysColumnMapper.updateColumn(sysColumn);
    }

    @Override
    public int deleteColumn(SysColumn sysColumn) {
        return sysColumnMapper.deleteColumn(sysColumn);
    }

    @Override
    public List<SysColumn> queryUpdateByid(Integer id) {
        return sysColumnMapper.queryUpdateByid(id);
    }
}
