package com.ruoyi.system.service.impl;

import com.ruoyi.system.mapper.TradeMapper;
import com.ruoyi.system.service.ITradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class tradeServiceImpl implements ITradeService {

    @Autowired
    private TradeMapper tradeMapper;

    @Override
    public void UpdateTradePassword(Long id, String tradePassword) {
         tradeMapper.UpdateTradePassword(id,tradePassword);
    }
}
