package com.zhixin.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysWalletLog;
import com.zhixin.domain.LiveRedEnvelopeUserInfo;
import com.zhixin.mapper.LiveRedPackageMapper;
import com.zhixin.domain.ZhixinLiveRedEnvelope;
import com.zhixin.service.LiveRedPackageService;
import com.zhixin.util.SnowflakesAlgorithm;
import com.zhixin.util.ZhixinRandomAmountUtil;
import com.zhixin.vo.req.RedEnvelopeReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class LiveReadPackageServiceImpl implements LiveRedPackageService {


    @Value("${live.liveUrl}")
    private String liveUrl;

    @Value("${live.authCode}")
    private String authCode;

    @Autowired
    private LiveRedPackageMapper liveRedPackageMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult sendRedEnvelope(RedEnvelopeReqVO redEnvelopeReqVO) throws Exception {
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
        Map<String,Object> liveMap = getLiveTime(redEnvelopeReqVO.getUserId());
        if(liveMap == null){
            return AjaxResult.error("直播间异常");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


        BigDecimal beforeMoney = user.getMoney();
        user.setMoney(user.getMoney().subtract(redEnvelopeReqVO.getMoney()));
        //修改主播余额
        liveRedPackageMapper.updateUserMoney(user);
        //添加一条红包记录
        Long hbId = SnowflakesAlgorithm.getGenerateId();
        liveMap.put("hb_id",hbId);
        ZhixinLiveRedEnvelope zhixinLiveRedenvelope = new ZhixinLiveRedEnvelope();
        zhixinLiveRedenvelope.setId(hbId);
        zhixinLiveRedenvelope.setSendUserId(redEnvelopeReqVO.getUserId());
        zhixinLiveRedenvelope.setMoney(redEnvelopeReqVO.getMoney());
        zhixinLiveRedenvelope.setRandomAmount(resultMap.get("data").toString());
        zhixinLiveRedenvelope.setTotal(redEnvelopeReqVO.getNumber());
        zhixinLiveRedenvelope.setLiveStartTime(sdf.parse(liveMap.get("createTime").toString()));
        zhixinLiveRedenvelope.setRemainingMoney(redEnvelopeReqVO.getMoney());
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
        if(sendLiveRed(liveMap)){
            throw new RuntimeException("红包推送失败");
        }
        redisTemplate.opsForHash().put("live-red-envelope:"+liveMap.get("id"),hbId.toString(),JSON.toJSONString(resultMap.get("data")));
        return AjaxResult.success(redEnvelopeReqVO);
    }

    @Override
    public AjaxResult getRedEnvelopeRecord(RedEnvelopeReqVO redEnvelopeReqVO) {
        redEnvelopeReqVO.setPage((redEnvelopeReqVO.getPage() - 1) * redEnvelopeReqVO.getSize());
        Map<String,Object> resultMap = new HashMap<>();
        int count = liveRedPackageMapper.getRedEnvelopeRecordCount(redEnvelopeReqVO);
        if(count > 0){
            resultMap.put("data",liveRedPackageMapper.getRedEnvelopeRecord(redEnvelopeReqVO));
        }
        resultMap.put("count",count);
        return AjaxResult.success(resultMap);
    }

    @Override
    public AjaxResult getRedEnvelopeReceiveRecord(RedEnvelopeReqVO redEnvelopeReqVO) {
        if(redEnvelopeReqVO.getRedEnvelopeId() == null){
            return AjaxResult.error("参数异常");
        }
        String userinfo = liveRedPackageMapper.getRedEnvelopeReceiveRecord(redEnvelopeReqVO);
        if(StringUtil.isEmpty(userinfo)){
            return AjaxResult.success();
        }
        List<LiveRedEnvelopeUserInfo> resultList = JSONObject.parseArray("["+userinfo+"]",LiveRedEnvelopeUserInfo.class);
        List<LiveRedEnvelopeUserInfo> list = new ArrayList<>();
        int start = (redEnvelopeReqVO.getPage() - 1) * redEnvelopeReqVO.getSize();
        int end = start + redEnvelopeReqVO.getSize() - 1;
        for (; start <= end; start ++ ){
            if(start < resultList.size()){
                list.add(resultList.get(start));
            }else {
                break;
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("count",resultList.size());
        map.put("data",list);
        return AjaxResult.success(map);
    }

    public Map<String, Object> getLiveTime(Long userId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("live_user_id",userId);
        jsonObject.put("auth_code",authCode);
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/getliveroom").body(JSON.toJSONString(jsonObject)).execute().body();
        Map<String,Object> resultMap = JSONObject.parseObject(body);
        if(resultMap != null && resultMap.get("status").equals(200)){
            return JSONObject.parseObject(resultMap.get("obj").toString());
        }
        return null;
    }

    public boolean sendLiveRed(Map<String,Object> map){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("room_id",map.get("id"));
        jsonObject.put("bag_id",map.get("hb_id"));
        jsonObject.put("bag_type","redbag");
        jsonObject.put("auth_code",authCode);
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/sendroomactive").body(JSON.toJSONString(jsonObject)).execute().body();
        Map<String,Object> resultMap = JSONObject.parseObject(body);
        if(resultMap != null && resultMap.get("status").equals(200)){
            return false;
        }
        return true;
    }


}
