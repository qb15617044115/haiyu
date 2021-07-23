package com.ruoyi.system.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.LiveConstant;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.VideoInfo;
import com.ruoyi.system.domain.vo.VideoVO;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class VideoServiceImpl implements IVideoService {
    @Value("${live.authCode}")
    private String authCode;
    @Value("${live.liveUrl}")
    private String liveUrl;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public AjaxResult getVideoList(VideoVO videoVO) {
        // 判断参数是否正确
        if(videoVO.getVideoType() == null){
            return AjaxResult.error("参数错误");
        }
        if(!LiveConstant.VIDEO_TYPE.equals(videoVO.getVideoType()) && !LiveConstant.LIVEBACK_TYPE.equals(videoVO.getVideoType())){
            return AjaxResult.error("参数错误");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("page", videoVO.getPage().toString());
        jsonObject.put("size",videoVO.getSize().toString());
        if(videoVO.getVideoType().equals(LiveConstant.VIDEO_TYPE)){
            jsonObject.put("video_type", LiveConstant.VIDEO_KEY);
        }
        if(videoVO.getVideoType().equals(LiveConstant.LIVEBACK_TYPE)){
            jsonObject.put("video_type", LiveConstant.LIVEBACK_KEY);
        }
        String body = HttpUtil.createPost(liveUrl + "/api/auth/video/getvideolist").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        JSONObject parseObject = JSON.parseObject(JSON.toJSONString(result.get("obj")));
        JSONArray objects = JSON.parseArray(JSON.toJSONString(parseObject.get("data")));
        List<VideoInfo> list = new ArrayList<>();
        for (Object object : objects) {
            JSONObject obj = JSON.parseObject(JSON.toJSONString(object));
            VideoInfo linfo = JSON.parseObject(JSON.toJSONString(obj.get("linfo")), VideoInfo.class);
            SysUser sysUser = sysUserMapper.selectUserById(Long.parseLong(linfo.getUserId()));
            if(sysUser != null){
                linfo.setUserNickname(sysUser.getNickName());
            }
            list.add(linfo);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("count",parseObject.get("total"));
        return AjaxResult.success(result.get("msg").toString(),map);
    }

    @Override
    public AjaxResult addLiveVideo(VideoVO videoVO, SysUser user) {
        // 判断参数是否正确
        if(StringUtils.isBlank(videoVO.getVideoName())){
            return AjaxResult.error("视频名称不能为空");
        }
        if(StringUtils.isBlank(videoVO.getLogoUrl())){
            return AjaxResult.error("视频封面不能为空");
        }
        if(StringUtils.isBlank(videoVO.getVideoUrl())){
            return AjaxResult.error("视频链接不能为空");
        }
        if(videoVO.getVideoType() == null){
            return AjaxResult.error("参数错误");
        }
        if(!LiveConstant.VIDEO_TYPE.equals(videoVO.getVideoType()) && !LiveConstant.LIVEBACK_TYPE.equals(videoVO.getVideoType())){
            return AjaxResult.error("参数错误");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("video_name",videoVO.getVideoName());
        jsonObject.put("logo_url",videoVO.getLogoUrl());
        jsonObject.put("video_url",videoVO.getVideoUrl());
        if(videoVO.getVideoType().equals(LiveConstant.VIDEO_TYPE)){
            jsonObject.put("video_type", LiveConstant.VIDEO_KEY);
        }
        if(videoVO.getVideoType().equals(LiveConstant.LIVEBACK_TYPE)){
            jsonObject.put("video_type", LiveConstant.LIVEBACK_KEY);
        }
        jsonObject.put("user_id",user.getUserId().toString());
        jsonObject.put("expire_time",videoVO.getExpireTime());
        if(videoVO.getWatchNum() != null){
            jsonObject.put("watch_num",videoVO.getWatchNum().toString());
        }
        String body = HttpUtil.createPost(liveUrl + "/api/auth/video/addlivevideo").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult delLiveVideo(VideoVO videoVO) {
        // 判断参数是否正确
        if(videoVO.getVideoId() == null){
            return AjaxResult.error("视频id不能为空");
        }
        if(videoVO.getUserId() == null){
            return AjaxResult.error("用户id不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("video_id",videoVO.getVideoId().toString());
        jsonObject.put("user_id",videoVO.getUserId().toString());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/video/dellivevideo").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }

        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult editLiveVideo(VideoVO videoVO) {
        if (videoVO.getVideoId() == null){
            return AjaxResult.error("视频id不能为空");
        }
        if(videoVO.getUserId() == null){
            return AjaxResult.error("用户id不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("video_id",videoVO.getVideoId().toString());
        jsonObject.put("user_id",videoVO.getUserId());
        if(StringUtils.isNotBlank(videoVO.getVideoName())){
            jsonObject.put("video_name",videoVO.getVideoName());
        }
        if(StringUtils.isNotBlank(videoVO.getLogoUrl())){
            jsonObject.put("logo_url",videoVO.getLogoUrl());
        }
        if(StringUtils.isNotBlank(videoVO.getVideoUrl())){
            jsonObject.put("video_url",videoVO.getVideoUrl());
        }
        if (videoVO.getVideoId() != null){
            if(videoVO.getVideoType().equals(LiveConstant.VIDEO_TYPE)){
                jsonObject.put("video_type", LiveConstant.VIDEO_KEY);
            }
            if(videoVO.getVideoType().equals(LiveConstant.LIVEBACK_TYPE)){
                jsonObject.put("video_type", LiveConstant.LIVEBACK_KEY);
            }
        }
        if (StringUtils.isNotBlank(videoVO.getExpireTime())){
            jsonObject.put("expire_time",videoVO.getExpireTime());
        }
        if (videoVO.getWatchNum() != null){
            jsonObject.put("watch_num",videoVO.getWatchNum().toString());
        }
        String body = HttpUtil.createPost(liveUrl + "/api/auth/video/editlivevideo").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }
}
