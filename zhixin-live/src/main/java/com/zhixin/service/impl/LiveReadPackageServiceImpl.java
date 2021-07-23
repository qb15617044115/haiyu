package com.zhixin.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.SysWalletLog;
import com.zhixin.mapper.LiveRedPackageMapper;
import com.zhixin.domain.ZhixinLiveRedEnvelope;
import com.zhixin.service.LiveRedPackageService;
import com.zhixin.util.SnowflakesAlgorithm;
import com.zhixin.util.ZhixinRandomAmountUtil;
import com.zhixin.vo.req.RedEnvelopeReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LiveReadPackageServiceImpl implements LiveRedPackageService {


    @Autowired
    private LiveRedPackageMapper liveRedPackageMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    @Transactional
    public AjaxResult sendRedEnvelope(RedEnvelopeReqVO redEnvelopeReqVO) {
        if(redEnvelopeReqVO.getNumber() == null || redEnvelopeReqVO.getNumber() == 0 || redEnvelopeReqVO.getUserId() == null){
            return AjaxResult.error("参数异常");
        }
        SysUser user = liveRedPackageMapper.getUserMoney(redEnvelopeReqVO.getUserId());
        if(user == null){
            return AjaxResult.error("未找到主播信息");
        }else if(user.getMoney().compareTo(redEnvelopeReqVO.getMoney()) == -1){
            return AjaxResult.error("余额不足请先充值");
        }
        Map<String,Object> resultMap = ZhixinRandomAmountUtil.getRandomAmount(redEnvelopeReqVO.getMoney(),redEnvelopeReqVO.getMaxMoney(),redEnvelopeReqVO.getMinMoney(),redEnvelopeReqVO.getNumber().toString());
        if(resultMap == null){
            return AjaxResult.error("服务器内部错误");
        }
        if(resultMap.get("state").equals(false)){
            return AjaxResult.error(resultMap.get("message").toString());
        }
        BigDecimal beforeMoney = user.getMoney();
        user.setMoney(user.getMoney().subtract(redEnvelopeReqVO.getMoney()));
        //修改主播余额
        liveRedPackageMapper.updateUserMoney(user);
        //添加一条红包记录
        Long hbId = SnowflakesAlgorithm.getGenerateId();
        ZhixinLiveRedEnvelope zhixinLiveRedenvelope = new ZhixinLiveRedEnvelope();
        zhixinLiveRedenvelope.setId(hbId);
        zhixinLiveRedenvelope.setSendUserId(redEnvelopeReqVO.getUserId());
        zhixinLiveRedenvelope.setMoney(redEnvelopeReqVO.getMoney());
        zhixinLiveRedenvelope.setRandomAmount(resultMap.get("data").toString());
        zhixinLiveRedenvelope.setTotal(redEnvelopeReqVO.getNumber());
        zhixinLiveRedenvelope.setRemainingMoney(redEnvelopeReqVO.getMoney());
        zhixinLiveRedenvelope.setReceiveUserInfo("[]");
        liveRedPackageMapper.insertLiveRedEnvelope(zhixinLiveRedenvelope);
        //添加主播支出记录
        SysWalletLog sysWalletLog = new SysWalletLog();
        sysWalletLog.setId(SnowflakesAlgorithm.getGenerateId());
        sysWalletLog.setLiveUserId(redEnvelopeReqVO.getUserId());
        sysWalletLog.setOperationId(redEnvelopeReqVO.getUserId());
        sysWalletLog.setBeforeMoney(beforeMoney);
        sysWalletLog.setOperationMoney(redEnvelopeReqVO.getMoney());
        sysWalletLog.setAfterMoney(user.getMoney());
        sysWalletLog.setType(2);
        liveRedPackageMapper.insertLiveWalletLog(sysWalletLog);
        redisTemplate.opsForHash().put("live-red-envelope:"+redEnvelopeReqVO.getUserId(),hbId.toString(),resultMap.get("data").toString());
        return AjaxResult.success(resultMap);
    }

    @Override
    public AjaxResult getRedEnvelopeRecord(RedEnvelopeReqVO redEnvelopeReqVO) {
        redEnvelopeReqVO.setPage(redEnvelopeReqVO.getPage() * redEnvelopeReqVO.getSize());
        Map<String,Object> resultMap = new HashMap<>();
        int count = liveRedPackageMapper.getRedEnvelopeRecordCount();
        if(count > 0){
            resultMap.put("data",liveRedPackageMapper.getRedEnvelopeRecord(redEnvelopeReqVO));
        }
        resultMap.put("count",count);
        return AjaxResult.success(resultMap);
    }
}
