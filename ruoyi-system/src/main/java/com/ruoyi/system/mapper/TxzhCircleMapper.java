package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TxzhCircle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TxzhCircleMapper{

    //删除
         int deleteCircle(TxzhCircle txzhCircle);

   //隐藏
         int updatehiddenContent(@Param("id") Integer id,@Param("hiddenContent") Integer hiddenContent);
   //查询
        List<TxzhCircle> queryAll(TxzhCircle txzhCircle);
  //列表
       List<TxzhCircle> queryList(TxzhCircle txzhCircle);
  //将更改后的id该数据返回
       List<TxzhCircle> queryByid(@Param("id") Integer  id);

}
