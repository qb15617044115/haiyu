package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysWalletLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysWalletLogMapper {

    void insert(SysWalletLog sysWalletLog);

    List<SysWalletLog> list(SysWalletLog sysWalletLog);

    int listCount(SysWalletLog sysWalletLog);
}
