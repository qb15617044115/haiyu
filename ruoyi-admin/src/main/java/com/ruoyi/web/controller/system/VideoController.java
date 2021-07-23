package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.vo.VideoVO;
import com.ruoyi.system.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private IVideoService videoService;
    @Autowired
    private TokenService tokenService;
    /** 获取视频列表 */
    @PostMapping("/getVideoList")
    public AjaxResult getVideoList(@RequestBody VideoVO videoVO){
        return videoService.getVideoList(videoVO);
    }
    /** 添加视频 */
    @PostMapping("/addLiveVideo")
    public AjaxResult addLiveVideo(@RequestBody VideoVO videoVO){
        LoginUser currentUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return videoService.addLiveVideo(videoVO,currentUser.getUser());
    }
    /** 删除视频 */
    @PostMapping("/delLiveVideo")
    public AjaxResult delLiveVideo(@RequestBody VideoVO videoVO){
        return videoService.delLiveVideo(videoVO);
    }
    /** 更新视频 */
    @PostMapping("/editLiveVideo")
    public AjaxResult editLiveVideo(@RequestBody VideoVO videoVO){
        return videoService.editLiveVideo(videoVO);
    }
}
