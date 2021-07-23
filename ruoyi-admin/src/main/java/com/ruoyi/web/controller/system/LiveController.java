package com.ruoyi.web.controller.system;

import com.ruoyi.common.constant.LiveConstant;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.vo.CloseLiveVO;
import com.ruoyi.system.domain.vo.GetLiveVO;
import com.ruoyi.system.domain.vo.LiveVO;
import com.ruoyi.system.domain.vo.OpenLiveVO;
import com.ruoyi.system.service.ILiveService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.impl.LiveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

@RestController
@RequestMapping("/user/live")
public class LiveController {
    @Autowired
    private ISysUserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ILiveService liveService;
    @PostMapping("/openLive")
    public AjaxResult openLive(@RequestBody OpenLiveVO openLiveVO){
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return userService.openLive(openLiveVO,loginUser.getUser());
    }

    @PostMapping("/getLiveList")
    public AjaxResult getLiveList(@RequestBody GetLiveVO getLiveVO){
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return userService.getLiveList(getLiveVO,loginUser.getUser());
    }

    @PostMapping("/closeLive")
    public AjaxResult closeLive(@RequestBody CloseLiveVO closeLiveVO){
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return userService.closeLive(closeLiveVO,loginUser.getUser());
    }
    /** 设置更新房间在线数量信息 */
    @PostMapping("/updateRoomInfo")
    public AjaxResult updateRoomInfo(@RequestBody LiveVO liveVO){
        return liveService.updateRoomInfo(liveVO);
    }
    /** 更新主播信息 */
    @PostMapping("/updateLiveUserInfo")
    public AjaxResult updateLiveUserInfo(@RequestBody LiveVO liveVO){
        return liveService.updateLiveUserInfo(liveVO);
    }

    /** 设置房间控制开关配置 */
    @PostMapping("/setLiveConf")
    public AjaxResult setLiveConf(@RequestBody LiveVO liveVO){
        return liveService.setLiveConf(liveVO);
    }

    /** 获取房间用户访问记录 */
    @PostMapping("/getRoomVisitLog")
    public AjaxResult getRoomVisitLog(@RequestBody LiveVO liveVO){
        return liveService.getRoomVisitLog(liveVO);
    }

    /** 获取房间在线用户id列表 */
    @PostMapping("/getOnlineUserIds")
    public AjaxResult getOnlineUserIds(@RequestBody LiveVO liveVO){
        return  liveService.getOnlineUserIds(liveVO);
    }

    /** 获取在线房间总数量 */
    @PostMapping("/getOnlineRoomCount")
    public AjaxResult getOnlineRoomCount(){
        return liveService.getOnlineRoomCount();
    }

    /** 获取房间在线数量k线图数据 */
    @PostMapping("/getOnlineKData")
    public AjaxResult getOnlineKData(@RequestBody LiveVO liveVO){
        return liveService.getOnlineKData(liveVO);
    }

    /** 获取房间用户在线数量 */
    @PostMapping("/getOnlineCount")
    public AjaxResult getOnlineCount(@RequestBody LiveVO liveVO){
        return  liveService.getOnlineCount(liveVO);
    }

    /** 获取房间聊天信息记录 */
    @PostMapping("/getChatMsgLog")
    public AjaxResult getChatMsgLog(@RequestBody LiveVO liveVO){
        return liveService.getChatMsgLog(liveVO);
    }

    /** 获取房间黑名单列表 */
    @PostMapping("/getBlackUserIds")
    public AjaxResult getBlackUserIds(){
        return liveService.getBlackUserIds();
    }

    /** 管理房间用户 */
    @PostMapping("/forbidUser")
    public AjaxResult forbidUser(@RequestBody LiveVO liveVO){
        return liveService.forbidUser(liveVO);
    }

    /** 管理(删除)房间用户 */
    @PostMapping("/delForbidUser")
    public AjaxResult delForbidUser(@RequestBody LiveVO liveVO){
        return liveService.delForbidUser(liveVO);
    }

    /** 设置房间视频背景图 */
    @PostMapping("/setRoomBackGround")
    public AjaxResult setRoomBackGround(@RequestBody LiveVO liveVO){
        return liveService.setRoomBackGround(liveVO);
    }

    /** 获取房间公告发送记录 */
    @PostMapping("/getRoomNoticeLog")
    public AjaxResult getRoomNoticeLog(@RequestBody LiveVO liveVO){
        return liveService.getRoomNoticeLog(liveVO);
    }

    /** 发送直播间系统公告 */
    @PostMapping("/sendRoomNotice")
    public AjaxResult sendRoomNotice(@RequestBody LiveVO liveVO){
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return liveService.sendRoomNotice(liveVO,loginUser.getUser());
    }

    /** 导出用户访问直播间记录 */
    @GetMapping("/exportRoomVisitList")
    public AjaxResult exportRoomVisitList(LiveVO liveVO, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        return liveService.exportRoomVisitList(liveVO,request,response);
    }

    /** 获取房间用户签到记录 */
    @PostMapping("/getSignList")
    public AjaxResult getSignList(@RequestBody LiveVO liveVO){
        return liveService.getSignList(liveVO);
    }

    /** 发送直播间系统红包和抽奖活动 */
    @PostMapping("/sendRoomActive")
    public AjaxResult sendRoomActive(@RequestBody LiveVO liveVO){
        return liveService.sendRoomActive(liveVO);
    }
    /** 获取房间虚拟数量 */
    @PostMapping("/getRoomFixVarNum")
    public AjaxResult getRoomFixVarNum(@RequestBody LiveVO liveVO){
        return liveService.getRoomFixVarNum(liveVO);
    }
    /** 获取房间被踢名单 */
    @PostMapping("/getKickUserIds")
    public AjaxResult getKickUserIds(@RequestBody LiveVO liveVO){
        return liveService.getKickUserIds(liveVO);
    }
    /** 获取房间视频背景图 */
    @PostMapping("/getRoomBackground")
    public AjaxResult getRoomBackground(@RequestBody LiveVO liveVO){
        return liveService.getRoomBackground(liveVO);
    }
    /** 获取房间控制开关配置 */
    @PostMapping("/getLiveConfInfo")
    public AjaxResult getLiveConfInfo(@RequestBody LiveVO liveVO){
        return liveService.getLiveConfInfo(liveVO);
    }
    /** 实时控制房间视频显示背景图 */
    @PostMapping("/realSetRoomBackImg")
    public AjaxResult realSetRoomBackImg(@RequestBody LiveVO liveVO){
        return liveService.realSetRoomBackImg(liveVO);
    }
    /** 获取所有直播列表 */
    @PostMapping("/getLiveListAll")
    public AjaxResult getLiveListAll(@RequestBody SysUser sysUser){
        return liveService.getLiveListAll(sysUser);
    }
    /** 获取直播间新ip列表 */
    @PostMapping("/getNewIPList")
    public AjaxResult getNewIPList(@RequestBody LiveVO liveVO) throws ParseException {
        return liveService.getNewIPList(liveVO);
    }
    /** 获取直播间访问k线(非实时统计用) */
    @PostMapping("/getRoomKData")
    public AjaxResult getRoomKData(@RequestBody LiveVO liveVO){
        return liveService.getRoomKData(liveVO);
    }

    /** 删除直播间系统公告 */
    @PostMapping("/delRoomNotice")
    public AjaxResult delRoomNotice(@RequestBody LiveVO liveVO){
        return liveService.delRoomNotice(liveVO);
    }
}
