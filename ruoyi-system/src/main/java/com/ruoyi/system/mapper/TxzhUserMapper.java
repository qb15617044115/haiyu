package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TxzhUser;

import com.ruoyi.system.domain.vo.UserChargeWithdrawal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface TxzhUserMapper {

    /**
     * 根据数组集合批量查询出 userId 对应的 TxzhUser
     */
    List<TxzhUser> BatchFindByUserId(@Param("deptIds") List<Long> deptIds, @Param("txzhUser") TxzhUser txzhUser);


    //重置密码
    int UpdatePSWD(@Param("password") String password, @Param("id") Long id);

    TxzhUser getByid(@Param("id") Long id);

    TxzhUser getByName(@Param("username") String username);

    //聊天记录
    List<TxzhUser> GetQueryImgName(@Param("txzhUser") TxzhUser txzhUser);

    List<TxzhUser> GetQueryById(@Param("id") Integer id);


    TxzhUser selectById(Long receiverId);

    TxzhUser selectByUserChargeWithdrawalId(UserChargeWithdrawal userChargeWithdrawal);

    void updateMoneyById(@Param("id") Long id, @Param("money") BigDecimal money);

    int updateMoneyAndFreezeMoney(UserChargeWithdrawal userChargeWithdrawal);

    int updateFreezeMoney(UserChargeWithdrawal userChargeWithdrawal);

    void insertTxzhUser(TxzhUser txzhUser);

    int selecCountByUsername(TxzhUser txzhUser);

    void blackTxzhUser(TxzhUser txzhUser);

    int countFindByUserId(@Param("deptIds") List<Long> deptIds, @Param("txzhUser") TxzhUser txzhUser);

    void updateTxzhUser(TxzhUser txzhUser);
}
