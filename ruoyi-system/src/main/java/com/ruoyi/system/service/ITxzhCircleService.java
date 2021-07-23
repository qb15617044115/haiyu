package com.ruoyi.system.service;

import com.ruoyi.system.domain.TxzhCircle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITxzhCircleService {

    //逻辑删除
    int deleteCircle(TxzhCircle txzhCircle);

    //隐藏
    int updatehiddenContent(Integer id,Integer hiddenContent);

    //查询
    List<TxzhCircle> queryAll(TxzhCircle txzhCircle);

    //列表
    List<TxzhCircle> queryList(TxzhCircle txzhCircle);

    List<TxzhCircle> queryByid(Integer  id);
}
