package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SignManagement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SignManagementMapper {

     List<SignManagement> InsertMoneyDays(@Param("signManagement") SignManagement signManagement);

     void UpdateSwitch(@Param("id") Integer id,@Param("state") Integer state);

     List<SignManagement> querygetAll(@Param("id") Integer id);

     void updateparameter(SignManagement signManagement);













}
