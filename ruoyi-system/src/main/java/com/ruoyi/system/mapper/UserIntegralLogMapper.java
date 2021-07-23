package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TxzhUser;
import com.ruoyi.system.domain.UserIntegralLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserIntegralLogMapper {

    List<UserIntegralLog> listByUserId(TxzhUser txzhUser);

    int countById(TxzhUser txzhUser);
}
