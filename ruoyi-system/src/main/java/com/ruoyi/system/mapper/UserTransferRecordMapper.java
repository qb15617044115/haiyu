package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TxzhUser;
import com.ruoyi.system.domain.UserTransferRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserTransferRecordMapper {
    List<UserTransferRecord> listByUserId(TxzhUser txzhUser);
    int listCountByUserId(TxzhUser txzhUser);
}
