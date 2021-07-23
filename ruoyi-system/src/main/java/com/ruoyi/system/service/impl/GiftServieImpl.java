package com.ruoyi.system.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.TxzhUser;
import com.ruoyi.system.domain.vo.GiftVO;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.TxzhUserMapper;
import com.ruoyi.system.service.IGiftServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GiftServieImpl implements IGiftServie {
    @Value("${live.authCode}")
    private String authCode;
    @Value("${live.liveUrl}")
    private String liveUrl;
    @Autowired
    private TxzhUserMapper txzhUserMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public AjaxResult getGiftInfoList(GiftVO giftVO) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("size",giftVO.getSize().toString());
        jsonObject.put("page",giftVO.getPage().toString());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/gift/getgiftinfolist").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult addGiftInfo(GiftVO giftVO) {
        if(StringUtils.isBlank(giftVO.getGiftName()) || StringUtils.isBlank(giftVO.getGiftUrl()) || StringUtils.isBlank(giftVO.getGiftCost())){
            return AjaxResult.error("参数错误了!");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("gift_name",giftVO.getGiftName());
        jsonObject.put("gift_url",giftVO.getGiftUrl());
        jsonObject.put("gift_cost",giftVO.getGiftCost());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/gift/addgiftinfo").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult delGIftInfo(GiftVO giftVO) {
        // 判断参数是否正确
        if(giftVO.getGiftId() == null){
            return AjaxResult.error("礼物id不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("gift_id", giftVO.getGiftId().toString());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/gift/delgiftinfo").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult updateGiftInfo(GiftVO giftVO) {
        // 判断参数是否正确
        if(giftVO.getGiftId() == null){
            return AjaxResult.error("礼物id不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("gift_id",giftVO.getGiftId().toString());
        if(StringUtils.isNotBlank(giftVO.getGiftCost())){
            jsonObject.put("gift_cost",giftVO.getGiftCost());
        }
        if(StringUtils.isNotBlank(giftVO.getGiftUrl())){
            jsonObject.put("gift_url",giftVO.getGiftUrl());
        }
        if(StringUtils.isNotBlank(giftVO.getGiftName())){
            jsonObject.put("gift_name",giftVO.getGiftName());
        }
        String body = HttpUtil.createPost(liveUrl + "/api/auth/gift/updategiftinfo").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult getGiftList(GiftVO giftVO) {
        if(giftVO.getLiveUserId() == null){
            return AjaxResult.error("主播id不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        if(giftVO.getRoomId() == null){
            jsonObject.put("room_id","");
        }else{
            jsonObject.put("room_id",giftVO.getRoomId().toString());
        }
        jsonObject.put("live_user_id",giftVO.getLiveUserId().toString());
        jsonObject.put("page",giftVO.getPage().toString());
        jsonObject.put("size",giftVO.getSize().toString());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/gift/getgiftlist").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        // 解析返回数据
        JSONObject obj = JSON.parseObject(JSON.toJSONString(result.get("obj")));
        List<GiftVO> data = JSON.parseArray(JSON.toJSONString(obj.get("data")), GiftVO.class);
        for (GiftVO datum : data) {
            TxzhUser txzhUser = txzhUserMapper.getByid(datum.getUserId());
            if(txzhUser != null){
                datum.setNickname(txzhUser.getNickname());
                datum.setUsername(txzhUser.getUsername());
            }
            SysUser sysUser = sysUserMapper.selectUserById(datum.getLiveUserId());
            if(sysUser != null){
                datum.setLiveUserNickname(sysUser.getNickName());
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("list",data);
        map.put("count",obj.get("total"));
        return AjaxResult.success(result.get("msg").toString(),map);
    }
}
