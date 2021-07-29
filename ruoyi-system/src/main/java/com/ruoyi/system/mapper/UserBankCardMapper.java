package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TxzhUser;
import com.ruoyi.system.domain.UserBankCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserBankCardMapper {
    void batchInsertBankCard(@Param("list") List<UserBankCard> list);

    List<UserBankCard> listByUserId(TxzhUser user);
}
