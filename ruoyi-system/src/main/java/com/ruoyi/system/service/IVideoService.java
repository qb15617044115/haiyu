package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.vo.VideoVO;

public interface IVideoService {
    AjaxResult getVideoList(VideoVO videoVO);

    AjaxResult addLiveVideo(VideoVO videoVO, SysUser user);

    AjaxResult delLiveVideo(VideoVO videoVO);

    AjaxResult editLiveVideo(VideoVO videoVO);
}
