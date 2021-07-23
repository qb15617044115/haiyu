package com.ruoyi.system.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.vo.LiveVO;
import com.ruoyi.system.service.IStickerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StickerServiceImpl implements IStickerService {

    @Value("${live.authCode}")
    private String authCode;
    @Value("${live.liveUrl}")
    private String liveUrl;
    @Override
    public AjaxResult getStickerInfoList(LiveVO liveVO) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("page",liveVO.getPage().toString());
        jsonObject.put("size",liveVO.getSize().toString());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/sticker/getstickerinfolist").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult delStickerInfo(LiveVO liveVO) {
        if(liveVO.getStickerId() == null){
            return AjaxResult.error("表情id不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("id",liveVO.getStickerId().toString());
        jsonObject.put("is_hard",liveVO.getIsHard().toString());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/sticker/delstickerinfo").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult addStickerInfo(LiveVO liveVO) {
        if(StringUtils.isBlank(liveVO.getStickerName()) || StringUtils.isBlank(liveVO.getStickerUrl()) || liveVO.getStickerType() == null){
            return AjaxResult.error("三个参数肯定有错误!");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("name",liveVO.getStickerName());
        jsonObject.put("url",liveVO.getStickerUrl());
        jsonObject.put("type",liveVO.getStickerType());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/sticker/addstickerinfo").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }
}
