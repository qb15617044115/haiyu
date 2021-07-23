package com.ruoyi.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TradeMapper {

     void UpdateTradePassword(@Param("id") Long id, @Param("tradePassword") String tradePassword);

}
