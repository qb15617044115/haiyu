package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TxzhUser;
import com.ruoyi.system.domain.UserTradingLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface UserTradingLogServiceMapper {

    List<UserTradingLog> selectByUserId(TxzhUser txzhUser);

    int selectCountByUserId(TxzhUser txzhUser);

    BigDecimal selectTotalByUserId(@Param("txzhUser") TxzhUser txzhUser, @Param("type") int type);

    void insertTradingLog(UserTradingLog userTradingLog);
}
