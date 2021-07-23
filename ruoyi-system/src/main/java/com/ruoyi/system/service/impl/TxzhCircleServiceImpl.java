package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.TxzhCircle;
import com.ruoyi.system.mapper.TxzhCircleMapper;
import com.ruoyi.system.service.ITxzhCircleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TxzhCircleServiceImpl implements ITxzhCircleService {


    @Autowired
    private TxzhCircleMapper txzhCircleMapper;


    @Override
    public int deleteCircle(TxzhCircle txzhCircle) {
        return txzhCircleMapper.deleteCircle(txzhCircle);
    }

    @Override
    public int updatehiddenContent(Integer id, Integer hiddenContent) {
        return txzhCircleMapper.updatehiddenContent(id,hiddenContent);
    }

//    @Override
//    public int updatehiddenContent(Integer id,Integer hiddenContent);) {
//        return txzhCircleMapper.updatehiddenContent(id,hiddenContent);
//    }

    @Override
    public List<TxzhCircle> queryAll(TxzhCircle txzhCircle) {
        return txzhCircleMapper.queryAll(txzhCircle);
    }

    @Override
    public List<TxzhCircle> queryList(TxzhCircle txzhCircle) {
        return txzhCircleMapper.queryList(txzhCircle);
    }

    @Override
    public List<TxzhCircle> queryByid(Integer  id) {
        return txzhCircleMapper.queryByid(id);
    }
}
