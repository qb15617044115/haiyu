package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TxzhUser;
import com.ruoyi.system.domain.vo.UserChargeWithdrawal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserChargeWithdrawalMapper {

    List<UserChargeWithdrawal> listByUserId(@Param("txzhUser") TxzhUser txzhUser, @Param("type") int type);

    void updateTopUpStateById(UserChargeWithdrawal userChargeWithdrawal);

    int updateCompleteById(Long id);

    int updateWithdrawalById(UserChargeWithdrawal userChargeWithdrawal);

    UserChargeWithdrawal selectById(Long id);

    int countByUserId(@Param("txzhUser") TxzhUser txzhUser, @Param("type") int type);

    String selectBankCardByBankId(String bankId);
}
