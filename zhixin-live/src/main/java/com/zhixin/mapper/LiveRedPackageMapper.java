package com.zhixin.mapper;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.SysWalletLog;
import com.zhixin.domain.ZhixinLiveRedEnvelope;
import com.zhixin.vo.req.RedEnvelopeReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LiveRedPackageMapper {

    int updateUserMoney(SysUser sysUser);

    SysUser getUserMoney(@Param("userid") Long userid);

    int insertLiveRedEnvelope(ZhixinLiveRedEnvelope zhixinLiveRedenvelope);

    List<Map<String,Object>> getRedEnvelopeRecord(RedEnvelopeReqVO redEnvelopeReqVO);

    int getRedEnvelopeRecordCount();

    int insertLiveWalletLog(SysWalletLog sysWalletLog);

}
