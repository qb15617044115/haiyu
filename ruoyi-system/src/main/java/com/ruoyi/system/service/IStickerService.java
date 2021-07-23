package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.vo.LiveVO;

public interface IStickerService {
    AjaxResult getStickerInfoList(LiveVO liveVO);

    AjaxResult delStickerInfo(LiveVO liveVO);

    AjaxResult addStickerInfo(LiveVO liveVO);
}
