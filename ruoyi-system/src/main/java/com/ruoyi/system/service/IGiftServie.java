package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.vo.GiftVO;

public interface IGiftServie {
    AjaxResult getGiftInfoList(GiftVO giftVO);

    AjaxResult addGiftInfo(GiftVO giftVO);

    AjaxResult delGIftInfo(GiftVO giftVO);

    AjaxResult updateGiftInfo(GiftVO giftVO);

    AjaxResult getGiftList(GiftVO giftVO);
}
