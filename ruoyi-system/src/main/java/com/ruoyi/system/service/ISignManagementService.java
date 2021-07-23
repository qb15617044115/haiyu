package com.ruoyi.system.service;

import com.ruoyi.system.domain.SignManagement;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ISignManagementService {

     List<SignManagement> InsertMoneyDays(SignManagement signManagement);

     void UpdateSwitch(Integer id, Integer state);

     List<SignManagement> querygetAll(@Param("id") Integer  id);

     void updateparameter(SignManagement signManagement);
}
