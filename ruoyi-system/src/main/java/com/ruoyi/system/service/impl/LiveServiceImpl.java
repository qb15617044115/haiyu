package com.ruoyi.system.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.JsonObject;
import com.ruoyi.common.constant.LiveConstant;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.vo.*;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.TxzhUserMapper;
import com.ruoyi.system.service.ILiveService;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class LiveServiceImpl implements ILiveService {
    @Value("${live.authCode}")
    private String authCode;
    @Value("${live.liveUrl}")
    private String liveUrl;
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private TxzhUserMapper txzhUserMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public AjaxResult updateRoomInfo(LiveVO liveVO) {
        // 判断参数是否正常
        if(liveVO.getLiveUserId() == null){
            return AjaxResult.error("主播id不能为空");
        }
        if (liveVO.getOnlineFixNum() == null){
            return AjaxResult.error("直播间固定人数不能为空");
        }
        if(liveVO.getOnlineVarNum() == null){
            return AjaxResult.error("直播间动态基数不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("room_id","");
        jsonObject.put("live_user_id",liveVO.getLiveUserId().toString());
        jsonObject.put("online_fix_num",liveVO.getOnlineFixNum().toString());
        jsonObject.put("online_var_num",liveVO.getOnlineVarNum().toString());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/updateroominfo").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult updateLiveUserInfo(LiveVO liveVO) {
        // 判断参数是否正确
        if(liveVO.getLiveUserId() == null){
            return AjaxResult.error("主播id不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("live_user_id",liveVO.getLiveUserId().toString());
        if(StringUtils.isNotBlank(liveVO.getHeadImg())){
            jsonObject.put("head_img",liveVO.getHeadImg());
        }
        if(StringUtils.isNotBlank(liveVO.getNickname())){
            jsonObject.put("nickname",liveVO.getNickname());
        }
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/updateliveuserinfo").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult setLiveConf(LiveVO liveVO) {
        // 判断参数是否正确
        if(StringUtils.isBlank(liveVO.getConfKey())){
            return AjaxResult.error("房间配置参数不能为空");
        }
        if(StringUtils.isBlank(liveVO.getConfValue())){
            return AjaxResult.error("参数错误!");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("conf_key",liveVO.getConfKey());
        if(liveVO.getRoomId() == null){
            jsonObject.put("room_id","");
        }else{
            jsonObject.put("room_id",liveVO.getRoomId().toString());
        }
        if (liveVO.getLiveUserId() != null){
            jsonObject.put("live_user_id",liveVO.getLiveUserId().toString());
        }
        jsonObject.put("conf_value",liveVO.getConfValue());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/setliveconf").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult getRoomVisitLog(LiveVO liveVO) {
        // 判断参数是否正确
        if(liveVO.getLiveUserId() == null){
            return AjaxResult.error("主播id不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        if(liveVO.getRoomId() != null){
            jsonObject.put("room_id", liveVO.getRoomId().toString());
        }else{
            jsonObject.put("room_id","");
        }
        jsonObject.put("live_user_id",liveVO.getLiveUserId().toString());
        if (liveVO.getUserId() == null){
            jsonObject.put("user_id",null);

        }else{
            jsonObject.put("user_id",liveVO.getUserId().toString());

        }
        jsonObject.put("page",liveVO.getPage().toString());
        jsonObject.put("size",liveVO.getSize().toString());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/getroomvisitlog").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        JSONObject obj = JSON.parseObject(JSON.toJSONString(result.get("obj")));
        Map<String,Object> map = new HashMap<>();
        List<UserVisitLog> logs = JSON.parseArray(JSON.toJSONString(obj.get("data")),UserVisitLog.class);
        for (UserVisitLog log : logs) {
            TxzhUser user = txzhUserMapper.getByid(Long.parseLong(log.getUserId()));
            if(user != null){
                log.setUsername(user.getUsername());
                log.setUserNickname(user.getNickname());
            }
        }
        map.put("list",logs);
        map.put("count",obj.get("total"));
        return AjaxResult.success(result.get("msg").toString(),map);
    }

    @Override
    public AjaxResult getOnlineUserIds(LiveVO liveVO) {
        // 判断参数是否正确
        if(liveVO.getLiveUserId() == null){
            return AjaxResult.error("主播id不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("room_id","");
        if(StringUtils.isNotBlank(liveVO.getForbidType())){
            jsonObject.put("forbid_type",liveVO.getForbidType());
        } else{
            jsonObject.put("forbid_type","");
        }
        jsonObject.put("live_user_id",liveVO.getLiveUserId().toString());
        jsonObject.put("page",liveVO.getPage().toString());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/getonlineuserids").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        Map<String,Object> map = new HashMap<>();
        // 解析返回参数
        JSONObject obj = JSON.parseObject(JSON.toJSONString(result.get("obj")));
        List<String> data = JSON.parseArray(JSON.toJSONString(obj.get("data")), String.class);
        List<LiveOnlineUser> list = new ArrayList<>();
        for (String datum : data) {
            LiveOnlineUser lou = new LiveOnlineUser();
            // 截取字符串
            lou.setUserId(Long.parseLong(datum.split("@")[0]));
            String[] strings = datum.split("@")[1].split(";");
            for (String string : strings) {
                String[] split = string.split(":");
                if(split[0].equals("say")){
                    lou.setSay(Integer.parseInt(split[1]));
                }
                if(split[0].equals("kick")){
                    lou.setKick(Integer.parseInt(split[1]));
                }
                if(split[0].equals("video")){
                    lou.setVideo(Integer.parseInt(split[1]));
                }
                if(split[0].equals("black")){
                    lou.setBlack(Integer.parseInt(split[1]));
                }
            }
            TxzhUser byid = txzhUserMapper.getByid(lou.getUserId());
            if (byid != null){
                lou.setNickname(byid.getNickname());
                lou.setUsername(byid.getUsername());
            }
            list.add(lou);
        }
        map.put("list",list);
        map.put("count",obj.get("total"));
        return AjaxResult.success(result.get("msg").toString(),map);
    }

    @Override
    public AjaxResult getOnlineRoomCount() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/getonlineroomcount").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult getOnlineKData(LiveVO liveVO) {
        // 判断参数是否正确
        if(liveVO.getLiveUserId() == null){
            return AjaxResult.error("主播id不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("room_id","");
        jsonObject.put("live_user_id",liveVO.getLiveUserId().toString());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/getonlinekdata").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult getOnlineCount(LiveVO liveVO) {
        // 判断参数是否正确
        if(liveVO.getLiveUserId() == null){
            return AjaxResult.error("主播id不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("room_id","");
        jsonObject.put("live_user_id",liveVO.getLiveUserId().toString());
        if(StringUtils.isNotBlank(liveVO.getMin())){
            jsonObject.put("min",liveVO.getMin());
        }else{
            jsonObject.put("min","10");
        }
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/getonlinecount").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult getChatMsgLog(LiveVO liveVO) {
        // 判断参数是否正确
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("room_id",liveVO.getRoomId());
        if(liveVO.getLiveUserId() != null){
            jsonObject.put("live_user_id",liveVO.getLiveUserId().toString());
        }
        jsonObject.put("page",liveVO.getPage().toString());
        jsonObject.put("size", liveVO.getSize().toString());
        jsonObject.put("is_filter",liveVO.getIsFilter().toString());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/getchatmsglog").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        JSONObject obj = JSON.parseObject(JSON.toJSONString(result.get("obj")));
        List<LiveChatMsg> list = JSON.parseArray(JSON.toJSONString(obj.get("data")),LiveChatMsg.class);
        for (LiveChatMsg liveChatMsg : list) {
            TxzhUser byid = txzhUserMapper.getByid(Long.parseLong(liveChatMsg.getUserId()));
            if(byid != null){
                liveChatMsg.setUsername(byid.getUsername());
                liveChatMsg.setNickname(byid.getNickname());
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("count",obj.get("total"));
        return AjaxResult.success(result.get("msg").toString(),map);
    }

    @Override
    public AjaxResult getBlackUserIds() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/getblackuserids").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        Map<String,Object> map = new HashMap<>();
        /*JSONObject flag = JSON.parseObject(JSON.toJSONString(result.get("obj")));
        if(flag.isEmpty()){
            map.put("list",new ArrayList<>());
            map.put("count",0);
            return AjaxResult.success(map);
        }*/
        JSONArray obj = JSON.parseArray(JSON.toJSONString(result.get("obj")));
        if(obj.size() <= 0){
            map.put("list",new ArrayList<>());
            map.put("count",0);
            return AjaxResult.success(map);
        }
        List<String> list = JSON.parseArray(JSON.toJSONString(result.get("obj")),String.class);
        List<TxzhUser> users = new ArrayList<>();
        for (String s : list) {
            TxzhUser user = txzhUserMapper.getByid(Long.parseLong(s));
            if(user == null){
                continue;
            }
            users.add(user);
        }
        map.put("list",users);
        map.put("count",users.size());
        return AjaxResult.success(result.get("msg").toString(),map);
    }

    @Override
    public AjaxResult forbidUser(LiveVO liveVO) {
        // 判断参数是否正确
        if(liveVO.getLiveUserId() == null){
            return AjaxResult.error("主播id不能为空");
        }
        if(liveVO.getUserId() == null){
            return AjaxResult.error("用户id不能为空");
        }
        if(liveVO.getForbidType() == null){
            return AjaxResult.error("管理状态不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("room_id","");
        jsonObject.put("live_user_id",liveVO.getLiveUserId().toString());
        jsonObject.put("user_id",liveVO.getUserId().toString());
        jsonObject.put("forbid_type",liveVO.getForbidType());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/forbiduser").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult delForbidUser(LiveVO liveVO) {
        if(!liveVO.getForbidType().equals("black") && liveVO.getLiveUserId() == null){
            return AjaxResult.error("主播id不能为空");
        }
        if(liveVO.getUserId() == null){
            return AjaxResult.error("用户id不能为空");
        }
        if(liveVO.getForbidType() == null){
            return AjaxResult.error("管理状态不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        if(liveVO.getForbidType().equals("black")){
            jsonObject.put("room_id","0");
        }else{
            jsonObject.put("room_id","");
        }
        if(liveVO.getLiveUserId() != null){
            jsonObject.put("live_user_id",liveVO.getLiveUserId().toString());
        }
        jsonObject.put("user_id",liveVO.getUserId().toString());
        jsonObject.put("forbid_type",liveVO.getForbidType());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/delforbiduser").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult getFilterWordList() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        String body = HttpUtil.createPost(liveUrl + "/api/auth/filterword/getfilterwordList").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult delFilterWord(LiveVO liveVO) {
        // 判断参数是否正确
        if(liveVO.getWordId() == null){
            return AjaxResult.error("敏感词id不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("word_id",liveVO.getWordId().toString());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/filterword/delfilterword").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult addFilterWord(LiveVO liveVO) {
        // 判断参数是否正确
        if (StringUtils.isBlank(liveVO.getWord())){
            return AjaxResult.error("请输入敏感词");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("word",liveVO.getWord());
        if(StringUtils.isBlank(liveVO.getRule())){
            jsonObject.put("rule","");
        }else{
            jsonObject.put("rule",liveVO.getRule());
        }
        String body = HttpUtil.createPost(liveUrl + "/api/auth/filterword/addfilterword").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult setRoomBackGround(LiveVO liveVO) {
        // 判断参数是否正确
        if(liveVO.getLiveUserId() == null){
            return AjaxResult.error("主播id不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("room_id","");
        jsonObject.put("live_user_id",liveVO.getLiveUserId().toString());
        if(StringUtils.isNotBlank(liveVO.getBackgroundUrl())){
            jsonObject.put("background_url",liveVO.getBackgroundUrl());
        }else{
            jsonObject.put("background_url","");
        }
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/setroombackground").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult getRoomNoticeLog(LiveVO liveVO) {
        // 判断参数是否正确
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        if(liveVO.getRoomId() != null){
            jsonObject.put("room_id", liveVO.getRoomId().toString());
        }else{
            jsonObject.put("room_id","");
        }
        if(liveVO.getLiveUserId() != null){
            jsonObject.put("live_user_id",liveVO.getLiveUserId().toString());
        }
        if(StringUtils.isNotBlank(liveVO.getNoticeTitle())){
            jsonObject.put("title",liveVO.getNoticeTitle());
        }
        jsonObject.put("page",liveVO.getPage().toString());
        jsonObject.put("size",liveVO.getSize().toString());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/getroomnoticelog").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }

        JSONObject obj = JSON.parseObject(JSON.toJSONString(result.get("obj")));
        List<RoomNoticeLog> logs = JSON.parseArray(JSON.toJSONString(obj.get("data")), RoomNoticeLog.class);
        for (RoomNoticeLog log : logs) {
            if(StringUtils.isNotBlank(log.getUserId())){
                SysUser sysUser = sysUserMapper.selectUserById(Long.parseLong(log.getUserId()));
                if(sysUser != null){
                    log.setUserNickname(sysUser.getNickName());
                }
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("list",logs);
        map.put("count",obj.get("total"));
        return AjaxResult.success(result.get("msg").toString(),map);
    }

    @Override
    public AjaxResult sendRoomNotice(LiveVO liveVO,SysUser sysUser) {
        // 判断参数是否正确
        if(StringUtils.isBlank(liveVO.getNoticeTitle())){
            return AjaxResult.error("公告标题不能为空");
        }
        if(StringUtils.isBlank(liveVO.getNoticeContent())){
            return AjaxResult.error("请发送有效的公告");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        if(liveVO.getRoomId() != null){
            jsonObject.put("room_id", liveVO.getRoomId().toString());
        }else{
            jsonObject.put("room_id","");
        }
        if(liveVO.getLiveUserId() != null){
            jsonObject.put("live_user_id",liveVO.getLiveUserId().toString());
        }
        jsonObject.put("user_id",sysUser.getUserId().toString());
        jsonObject.put("title",liveVO.getNoticeTitle());
        jsonObject.put("notice_content",liveVO.getNoticeContent());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/sendroomnotice").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult exportRoomVisitList(LiveVO liveVO, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = simpleDateFormat.format(new Date());
        String fileName = "用户访问直播记录" + format + ".xls";
        String fileNameURL = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename="+fileNameURL+";"+"filename*=utf-8''"+fileNameURL);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        if(liveVO.getRoomId() != null){
            jsonObject.put("room_id",liveVO.getRoomId().toString());
        }else{
            jsonObject.put("room_id","");
        }
        if (liveVO.getUserId() == null){
            jsonObject.put("user_id",null);

        }else{
            jsonObject.put("user_id",liveVO.getUserId().toString());
        }
        jsonObject.put("page",liveVO.getPage().toString());
        jsonObject.put("size","50");
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/getroomvisitlog").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        JSONObject obj = JSON.parseObject(JSON.toJSONString(result.get("obj")));
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        int total = Integer.parseInt(obj.get("total").toString());
        if(!(total > 0)){
            return AjaxResult.error("数据为空");
        }
        List<UserVisitLog> logsResult = new ArrayList<>();
        // 算出页数
        int pages = total / 50 + 1;
        for (int i = 0; i <= pages; i++) {
            int page = i + 1;
            jsonObject.put("page", Integer.toString(page));
            String bodyR = HttpUtil.createPost(liveUrl + "/api/auth/live/getroomvisitlog").body(JSON.toJSONString(jsonObject)).execute().body();
            JSONObject resultR = JSON.parseObject(bodyR);
            JSONObject objR = JSON.parseObject(JSON.toJSONString(resultR.get("obj")));
            List<UserVisitLog> logs = JSON.parseArray(JSON.toJSONString(objR.get("data")),UserVisitLog.class);
            logsResult.addAll(logs);
        }
        try{
            // 创建一个excel文件
            Workbook wb = new HSSFWorkbook();

            Sheet sheet = wb.createSheet("用户访问直播记录");
            sheet.setColumnWidth(0,6000);
            sheet.setColumnWidth(1,6000);
            sheet.setColumnWidth(2,6000);
            sheet.setColumnWidth(3,6000);
            sheet.setColumnWidth(4,6000);
            sheet.setColumnWidth(5,6000);
            sheet.setColumnWidth(6,6000);
            sheet.setColumnWidth(7,6000);
            sheet.setColumnWidth(8,6000);
            sheet.setColumnWidth(9,6000);
            //标题行
            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("用户ID");
            row.createCell(1).setCellValue("用户名字");
            row.createCell(2).setCellValue("机构");
            row.createCell(3).setCellValue("主播");
            row.createCell(4).setCellValue("进入时间");
            row.createCell(5).setCellValue("离开时间");
            row.createCell(6).setCellValue("逗留时长(分钟)");
            row.createCell(7).setCellValue("IP");
            row.createCell(8).setCellValue("所属地区");
            row.createCell(9).setCellValue("访问来源");
            for (int i = 0; i < logsResult.size(); i++) {
                Row sheetRow = sheet.createRow(i + 1);
                sheetRow.createCell(0).setCellValue(logsResult.get(i).getUserId());
                TxzhUser txzhUser = txzhUserMapper.getByid(Long.parseLong(logsResult.get(i).getUserId()));
                if(txzhUser != null){
                    sheetRow.createCell(1).setCellValue(logsResult.get(i).getUserId());
                    sheetRow.createCell(2).setCellValue(sysDeptMapper.selectDeptById((long) txzhUser.getDeptId()).getDeptName());
                }else{
                    sheetRow.createCell(1).setCellValue("--");
                    sheetRow.createCell(2).setCellValue("--");
                }
                SysUser sysUser = sysUserMapper.selectUserById(Long.parseLong(logsResult.get(i).getSessionId()));
                if(sysUser != null){
                    sheetRow.createCell(3).setCellValue(sysUser.getNickName());
                }else{
                    sheetRow.createCell(3).setCellValue("--");
                }
                sheetRow.createCell(4).setCellValue(logsResult.get(i).getEnterTime());
                if(StringUtils.isBlank(logsResult.get(i).getLeaveTime())){
                    sheetRow.createCell(5).setCellValue("--");
                    sheetRow.createCell(6).setCellValue("--");
                }else{
                    sheetRow.createCell(5).setCellValue(logsResult.get(i).getLeaveTime());
                    // 计算两个时间相差的分钟
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date enterTime = sdf.parse(logsResult.get(i).getEnterTime());
                    Date leaveTime = sdf.parse(logsResult.get(i).getLeaveTime());
                    long between = DateUtil.between(enterTime, leaveTime, DateUnit.MINUTE);
                    sheetRow.createCell(6).setCellValue(between + "");
                }
                sheetRow.createCell(7).setCellValue(logsResult.get(i).getIp());
                if(StringUtils.isNotBlank(logsResult.get(i).getArea())){
                    sheetRow.createCell(8).setCellValue(logsResult.get(i).getArea());
                }else{
                    sheetRow.createCell(8).setCellValue("--");
                }
                if(StringUtils.isNotBlank(logsResult.get(i).getReferrer())){
                    sheetRow.createCell(9).setCellValue(logsResult.get(i).getReferrer());
                }else{
                    sheetRow.createCell(9).setCellValue("--");
                }
            }
            wb.write(response.getOutputStream());
            wb.close();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("导出失败,请联系管理员");
        }
        return AjaxResult.success();
    }

    @Override
    public AjaxResult getSignList(LiveVO liveVO) {
        if(liveVO.getRoomId() == null){
            return AjaxResult.error("房间id不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("room_id",liveVO.getRoomId().toString());
        if(liveVO.getUserId() != null){
            jsonObject.put("user_id",liveVO.getUserId());
        }
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/getsignlist").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        JSONObject obj = JSON.parseObject(JSON.toJSONString(result.get("obj")));
        List<LiveSignVO> data = JSON.parseArray(JSON.toJSONString(obj.get("data")), LiveSignVO.class);
        for (LiveSignVO vo : data) {
            TxzhUser txzhUser = txzhUserMapper.getByid(Long.parseLong(vo.getUserId()));
            if ((txzhUser != null)){
                vo.setUserNickname(txzhUser.getNickname());
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("list",data);
        map.put("count",obj.get("total"));
        return AjaxResult.success(result.get("msg").toString(),map);
    }

    @Override
    public AjaxResult sendRoomActive(LiveVO liveVO) {
        if(liveVO.getLiveUserId() == null){
            return AjaxResult.error("主播id不能为空");
        }
        if(liveVO.getBagId() == null){
            return AjaxResult.error("红包id不能为空");
        }
        if(StringUtils.isBlank(liveVO.getBagType())){
            return AjaxResult.error("红包类型不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        if(liveVO.getRoomId() == null){
            jsonObject.put("room_id","");
        }else{
            jsonObject.put("room_id",liveVO.getRoomId().toString());
        }
        if(liveVO.getLiveUserId() != null){
            jsonObject.put("live_user_id",liveVO.getUserId());
        }
        jsonObject.put("bag_id",liveVO.getBagId().toString());
        jsonObject.put("bag_type",liveVO.getBagType());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/sendroomactive").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult getRoomFixVarNum(LiveVO liveVO) {
        if(liveVO.getLiveUserId() == null){
            return AjaxResult.error("主播id不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        if(liveVO.getRoomId() != null){
            jsonObject.put("room_id",liveVO.getRoomId().toString());
        }else{
            jsonObject.put("room_id","");
        }
        jsonObject.put("live_user_id",liveVO.getLiveUserId().toString());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/getroomfixvarnum").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult getKickUserIds(LiveVO liveVO) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        if(liveVO.getRoomId() != null){
            jsonObject.put("room_id",liveVO.getRoomId().toString());
        }else{
            jsonObject.put("room_id","");
        }
        if(liveVO.getLiveUserId() != null){
            jsonObject.put("live_user_id",liveVO.getLiveUserId().toString());
        }else{
            jsonObject.put("live_user_id","");
        }
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/getkickuserids").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        Map<String,Object> map = new HashMap<>();
        /*JSONArray obj = JSON.parseArray(JSON.toJSONString(result.get("obj")));
        if(obj.size() <= 0){
            map.put("list",new ArrayList<>());
            map.put("count",0);
            return AjaxResult.success(map);
        }*/
        List<String> list = JSON.parseArray(JSON.toJSONString(result.get("obj")),String.class);
        List<TxzhUser> user = new ArrayList<>();
        for (String ids : list) {
            TxzhUser byid = txzhUserMapper.getByid(Long.parseLong(ids));
            if(byid != null){
                user.add(byid);
            }
        }
        map.put("list",user);
        map.put("count",user.size());
        return AjaxResult.success(result.get("msg").toString(),map);
    }

    @Override
    public AjaxResult getRoomBackground(LiveVO liveVO) {
        if(liveVO.getLiveUserId() == null){
            return  AjaxResult.error("主播id不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        if(liveVO.getRoomId() != null){
            jsonObject.put("room_id",liveVO.getRoomId().toString());
        }else{
            jsonObject.put("room_id","");
        }
        jsonObject.put("live_user_id",liveVO.getLiveUserId().toString());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/getroombackground").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult getLiveConfInfo(LiveVO liveVO) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        if(liveVO.getRoomId() != null){
            jsonObject.put("room_id",liveVO.getRoomId().toString());
        }else{
            jsonObject.put("room_id","");
        }
        if(liveVO.getLiveUserId() != null){
            jsonObject.put("live_user_id",liveVO.getLiveUserId().toString());
        }
        jsonObject.put("conf_key",liveVO.getConfKey());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/getliveconfinfo").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult realSetRoomBackImg(LiveVO liveVO) {
        if(liveVO.getLiveUserId() == null){
            return AjaxResult.error("主播id不能为空");
        }
        if(liveVO.getType() == null){
            return AjaxResult.error("操作类型不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        if(liveVO.getRoomId() != null){
            jsonObject.put("room_id",liveVO.getRoomId().toString());
        }else{
            jsonObject.put("room_id","");
        }
        jsonObject.put("live_user_id",liveVO.getLiveUserId().toString());
        jsonObject.put("type",liveVO.getType().toString());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/realsetroombackimg").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result.get("msg").toString(),result.get("obj"));
    }

    @Override
    public AjaxResult getLiveListAll(SysUser sysUser) {
        List<Long> ids = new ArrayList<>();
        if(StringUtils.isNotBlank(sysUser.getNickName())){
            ids = sysUserMapper.selectByNickname(sysUser.getNickName());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        if(ids.isEmpty()){
            jsonObject.put("room_id","0");
        }else{
            String join = StringUtils.join(ids,",");
            jsonObject.put("user_ids",join);
        }
        jsonObject.put("page",sysUser.getPage().toString());
        jsonObject.put("size",sysUser.getSize().toString());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/getlivelistall").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        Map<String,Object> map = new HashMap<>();
        JSONObject obj = JSON.parseObject(JSON.toJSONString(result.get("obj")));
        if(obj.isEmpty()){
            map.put("list",new ArrayList<>());
            map.put("count",0);
        }
        List<RespAllLiveVO> list = JSON.parseArray(JSON.toJSONString(obj.get("data")), RespAllLiveVO.class);
        for (RespAllLiveVO respAllLiveVO : list) {
            if(respAllLiveVO.getLive_user_id() != null){
                SysUser user = sysUserMapper.selectUserById(Long.parseLong(respAllLiveVO.getLive_user_id()));
                if(user != null){
                    respAllLiveVO.setUserNickname(user.getNickName());
                }
            }
        }
        map.put("list",list);
        map.put("count",obj.get("total"));
        return AjaxResult.success(map);
    }

    @Override
    public AjaxResult getNewIPList(LiveVO liveVO) throws ParseException {
        if(liveVO.getRoomId() == null){
            return AjaxResult.error("房间id不能为空");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("room_id",liveVO.getRoomId());
        if(StringUtils.isNotBlank(liveVO.getTime())){
            Date parse = sdf.parse(liveVO.getTime());
            jsonObject.put("time",sdf.format(DateUtil.beginOfDay(parse)));
        }else{
            String format = sdf.format(DateUtil.beginOfDay(new Date()));
            jsonObject.put("time",format);
        }
        jsonObject.put("page",liveVO.getPage().toString());
        jsonObject.put("size",liveVO.getSize().toString());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/getnewiplist").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        Map<String,Object> map = new HashMap<>();
        JSONObject obj = JSON.parseObject(JSON.toJSONString(result.get("obj")));
        if(obj.isEmpty()){
            map.put("list",new ArrayList<>());
            map.put("count",0);
        }
        List<NewIPVO> list = JSON.parseArray(JSON.toJSONString(obj.get("data")), NewIPVO.class);
        for (NewIPVO newIPVO : list) {
            TxzhUser byid = txzhUserMapper.getByid(Long.parseLong(newIPVO.getUser_id()));
            if(byid != null){
                newIPVO.setUser_nickname(byid.getNickname());
            }
        }
        map.put("list",list);
        map.put("count",obj.get("total"));
        return AjaxResult.success(map);
    }

    @Override
    public AjaxResult getRoomKData(LiveVO liveVO) {
        if(liveVO.getRoomId() == null){
            return AjaxResult.error("房间id不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("room_id",liveVO.getRoomId());
        if(StringUtils.isNotBlank(liveVO.getMin())){
            jsonObject.put("min",liveVO.getMin());
        }else{
            jsonObject.put("min","10");
        }
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/getroomkdata").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result);
    }

    @Override
    public AjaxResult delRoomNotice(LiveVO liveVO) {
        if(liveVO.getId() == null){
            return AjaxResult.error("公告id不能为空");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("auth_code",authCode);
        jsonObject.put("id",liveVO.getId().toString());
        String body = HttpUtil.createPost(liveUrl + "/api/auth/live/delroomnotice").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result);
    }
}
