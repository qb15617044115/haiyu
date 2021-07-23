package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.TxzhConfigAll;
import org.apache.ibatis.annotations.Mapper;
import sun.security.krb5.Config;

import java.util.List;

@Mapper
public interface TxzhConfigAllMapper {

    List<TxzhConfigAll> listConfigAll();

    void addConfigAll(TxzhConfigAll txzhConfigAll);

    void updateConfigAll(TxzhConfigAll txzhConfigAll);

    List<TxzhConfigAll> listCheckUpdate(TxzhConfigAll txzhConfigAll);

    void addCheckUpdate(TxzhConfigAll txzhConfigAll);

    void deleteConfigAll(TxzhConfigAll txzhConfigAll);

    int selectCountByCodeNumber(TxzhConfigAll txzhConfigAll);

    TxzhConfigAll getRTK();

    int updateRTKStatus(TxzhConfigAll txzhConfigAll);

    List<TxzhConfigAll> listIntegralConfig();

    void batchUpdateLevel(TxzhConfigAll list);
}
