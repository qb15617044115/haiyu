package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Withdrawal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface WithdrawalMapper {

//     void  addWithdrawal(Withdrawal withdrawal);

     List<Withdrawal> queryWithdrawal(@Param("id")Integer id);

     void  updatewithdrawal(Withdrawal withdrawal);



}
