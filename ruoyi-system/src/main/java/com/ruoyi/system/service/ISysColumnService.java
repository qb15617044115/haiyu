package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysColumn;

import java.util.List;

public interface ISysColumnService {

     List<SysColumn> queryList(SysColumn sysColumn);

     void insertColumn(SysColumn sysColumn);

     List<String> getTuPian(String moduleType);

     void updateColumn(SysColumn sysColumn);

     int deleteColumn(SysColumn sysColumn);

     List<SysColumn> queryUpdateByid(Integer id);

}
