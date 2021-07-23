package com.ruoyi.system.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.Prize;
import com.ruoyi.system.domain.TxzhUser;
import com.ruoyi.system.domain.vo.PrizeVO;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.TxzhUserMapper;
import com.ruoyi.system.service.IPrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PrizeServiceImpl implements IPrizeService {
    @Autowired
    private TxzhUserMapper txzhUserMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Value("${live.authCode}")
    private String authCode;
    @Value("${live.liveUrl}")
    private String liveUrl;
    @Override
    public AjaxResult addLottery(Prize prize) {
        if(prize.getRoomId() == null && prize.getLiveUserId() == null){
            return AjaxResult.error("必须写入主播id或者房间id");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        if(prize.getRoomId() != null){
            jsonObject.put("room_id",prize.getRoomId().toString());
        }else {
            jsonObject.put("room_id","");
        }
        if (prize.getLiveUserId() != null){
            jsonObject.put("live_user_id",prize.getLiveUserId().toString());
        }
        jsonObject.put("prize_name",prize.getPrizeName());
        jsonObject.put("prize_imgurl",prize.getPrizeImgurl());
        if(prize.getPrizeCost() != null){
            jsonObject.put("prize_cost",prize.getPrizeCost().toString());
        }
        if(prize.getPrizeProb() != null){
            jsonObject.put("prize_prob",prize.getPrizeProb().toString());
        }
        if(prize.getPrizeNum() != null){
            jsonObject.put("prize_num",prize.getPrizeNum().toString());
        }
        String body = HttpUtil.createPost(liveUrl + "/api/auth/lottery/addlottery").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult delLottery(Prize prize) {
        if(prize.getRoomId() == null && prize.getLiveUserId() == null){
            return AjaxResult.error("必须写入主播id或者房间id");
        }
        if(prize.getId() == null){
            return AjaxResult.error("礼品id不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        if(prize.getRoomId() != null){
            jsonObject.put("room_id",prize.getRoomId().toString());
        }else {
            jsonObject.put("room_id","");
        }
        if (prize.getLiveUserId() != null){
            jsonObject.put("live_user_id",prize.getLiveUserId().toString());
        }
        jsonObject.put("prize_id",prize.getId().toString());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/lottery/dellottery").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult editLottery(Prize prize) {
        if(prize.getRoomId() == null && prize.getLiveUserId() == null){
            return AjaxResult.error("必须写入主播id或者房间id");
        }
        if(prize.getId() == null){
            return AjaxResult.error("礼品id不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        if(prize.getRoomId() != null){
            jsonObject.put("room_id",prize.getRoomId().toString());
        }else {
            jsonObject.put("room_id","");
        }
        if (prize.getLiveUserId() != null){
            jsonObject.put("live_user_id",prize.getLiveUserId().toString());
        }
        jsonObject.put("prize_id",prize.getId().toString());
        if(StringUtils.isNotBlank(prize.getPrizeName())){
            jsonObject.put("prize_name",prize.getPrizeName());
        }
        if(StringUtils.isNotBlank(prize.getPrizeImgurl())){
            jsonObject.put("prize_imgurl",prize.getPrizeImgurl());
        }
        if(prize.getPrizeCost() != null){
            jsonObject.put("prize_cost",prize.getPrizeCost().toString());
        }
        if(prize.getPrizeProb() != null){
            jsonObject.put("prize_prob",prize.getPrizeProb().toString());
        }
        if(prize.getPrizeNum() != null){
            jsonObject.put("prize_num",prize.getPrizeNum().toString());
        }
        String body = HttpUtil.createPost(liveUrl + "/api/auth/lottery/editlottery").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult getLotteryList(Prize prize) {
        if(prize.getRoomId() == null && prize.getLiveUserId() == null){
            return AjaxResult.error("必须写入主播id或者房间id");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        if(prize.getRoomId() != null){
            jsonObject.put("room_id",prize.getRoomId().toString());
        }else {
            jsonObject.put("room_id","");
        }
        if (prize.getLiveUserId() != null){
            jsonObject.put("live_user_id",prize.getLiveUserId().toString());
        }
        String body = HttpUtil.createPost(liveUrl + "/api/auth/lottery/getlotterylist").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        List<Prize> list = JSON.parseArray(JSON.toJSONString(result.get("obj")), Prize.class);
        if(list.size() <= 0){
            return AjaxResult.success(new ArrayList<>());
        }else{
            return AjaxResult.success(list);
        }
    }

    @Override
    public AjaxResult getPrizedList(Prize prize) {
        if(prize.getRoomId() == null && prize.getLiveUserId() == null){
            return AjaxResult.error("必须写入主播id或者房间id");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        if(prize.getRoomId() != null){
            jsonObject.put("room_id",prize.getRoomId().toString());
        }else {
            jsonObject.put("room_id","");
        }
        if (prize.getLiveUserId() != null){
            jsonObject.put("live_user_id",prize.getLiveUserId().toString());
        }
        jsonObject.put("page",prize.getPage().toString());
        jsonObject.put("size",prize.getSize().toString());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/lottery/getprizedlist").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        Map<String,Object> map = new HashMap<>();
        JSONObject obj = JSON.parseObject(JSON.toJSONString(result.get("obj")));
        List<PrizeVO> list = JSON.parseArray(JSON.toJSONString(obj.get("data")), PrizeVO.class);
        if(list.isEmpty()){
            map.put("list",new ArrayList<>());
            map.put("count",0);
            return AjaxResult.success(map);
        }
        for (PrizeVO prizeVO : list) {
            TxzhUser byid = txzhUserMapper.getByid(prizeVO.getUserId());
            if(byid != null){
                prizeVO.setUserNickname(byid.getNickname());
            }
        }
        map.put("list",list);
        map.put("count",obj.get("total"));
        return AjaxResult.success(map);
    }

    @Override
    public AjaxResult addLotteryActive(Prize prize) {
        if(prize.getRoomId() == null && prize.getLiveUserId() == null){
            return AjaxResult.error("必须写入主播id或者房间id");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        if(prize.getRoomId() != null){
            jsonObject.put("room_id",prize.getRoomId().toString());
        }else {
            jsonObject.put("room_id","");
        }
        if (prize.getLiveUserId() != null){
            jsonObject.put("live_user_id",prize.getLiveUserId().toString());
        }
        if(StringUtils.isNotBlank(prize.getLotteryIds())){
            jsonObject.put("lottery_ids",prize.getLotteryIds());
        }
        if(StringUtils.isNotBlank(prize.getUserIds())){
            jsonObject.put("user_ids",prize.getUserIds());
        }
        jsonObject.put("lottery_power",prize.getLotteryPower().toString());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/lottery/addlotteryactive").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result);
    }

    @Override
    public AjaxResult getActiveList(Prize prize) {
        if(prize.getRoomId() == null && prize.getLiveUserId() == null && StringUtils.isBlank(prize.getLiveUserNickname())){
            return AjaxResult.error("必须写入主播id或者房间id");
        }
        Map<String,Object> map = new HashMap<>();

        List<Long> ids = new ArrayList<>();
        if(StringUtils.isNotBlank(prize.getLiveUserNickname())){
            ids = sysUserMapper.selectByNickname(prize.getLiveUserNickname());
        }
        if(ids.isEmpty() && StringUtils.isNotBlank(prize.getLiveUserNickname())){
            map.put("list",new ArrayList<>());
            map.put("count",0);
            return AjaxResult.success(map);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        if(prize.getRoomId() != null){
            jsonObject.put("room_id",prize.getRoomId().toString());
        }else {
            jsonObject.put("room_id","");
        }
        if(ids.isEmpty()){
            jsonObject.put("user_ids","");
        }else{
            String join = StringUtils.join(ids, ",");
            jsonObject.put("user_ids",join);
        }
        jsonObject.put("page",prize.getPage().toString());
        jsonObject.put("size",prize.getSize().toString());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/lottery/getactivelist").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        JSONObject obj = JSON.parseObject(JSON.toJSONString(result.get("obj")));
        List<PrizeVO> list = JSON.parseArray(JSON.toJSONString(obj.get("data")), PrizeVO.class);
        for (PrizeVO datum : list) {
            SysUser sysUser = sysUserMapper.selectUserById(datum.getLiveUserId());
            if(sysUser != null){
                datum.setLiveUserNickname(sysUser.getNickName());
            }
        }
        map.put("list",list);
        map.put("count",obj.get("total"));
        return AjaxResult.success(map);
    }
}
