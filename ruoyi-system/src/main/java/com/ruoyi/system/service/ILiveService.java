package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.vo.LiveVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

public interface ILiveService {
    AjaxResult updateRoomInfo(LiveVO liveVO);

    AjaxResult updateLiveUserInfo(LiveVO liveVO);

    AjaxResult setLiveConf(LiveVO liveVO);

    AjaxResult getRoomVisitLog(LiveVO liveVO);

    AjaxResult getOnlineUserIds(LiveVO liveVO);

    AjaxResult getOnlineRoomCount();

    AjaxResult getOnlineKData(LiveVO liveVO);

    AjaxResult getOnlineCount(LiveVO liveVO);

    AjaxResult getChatMsgLog(LiveVO liveVO);

    AjaxResult getBlackUserIds();

    AjaxResult forbidUser(LiveVO liveVO);

    AjaxResult delForbidUser(LiveVO liveVO);

    AjaxResult getFilterWordList();

    AjaxResult delFilterWord(LiveVO liveVO);

    AjaxResult addFilterWord(LiveVO liveVO);

    AjaxResult setRoomBackGround(LiveVO liveVO);

    AjaxResult getRoomNoticeLog(LiveVO liveVO);

    AjaxResult sendRoomNotice(LiveVO liveVO, SysUser sysUser);

    AjaxResult exportRoomVisitList(LiveVO liveVO, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException;

    AjaxResult getSignList(LiveVO liveVO);

    AjaxResult sendRoomActive(LiveVO liveVO);

    AjaxResult getRoomFixVarNum(LiveVO liveVO);

    AjaxResult getKickUserIds(LiveVO liveVO);

    AjaxResult getRoomBackground(LiveVO liveVO);

    AjaxResult getLiveConfInfo(LiveVO liveVO);

    AjaxResult realSetRoomBackImg(LiveVO liveVO);

    AjaxResult getLiveListAll(SysUser sysUser);

    AjaxResult getNewIPList(LiveVO liveVO) throws ParseException;

    AjaxResult getRoomKData(LiveVO liveVO);

    AjaxResult delRoomNotice(LiveVO liveVO);
}
