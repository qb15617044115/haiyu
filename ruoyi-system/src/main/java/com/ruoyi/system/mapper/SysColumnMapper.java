package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysColumn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface SysColumnMapper {

     List<SysColumn> queryList(SysColumn sysColumn);

     void insertColumn(SysColumn sysColumn);

     List<String> getTuPian(String moduleType);

     void updateColumn(SysColumn sysColumn);

     int deleteColumn(SysColumn sysColumn);

     List<SysColumn> queryUpdateByid(@Param("id") Integer id);


}
