package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.Prize;

public interface IPrizeService {
    AjaxResult addLottery(Prize prize);

    AjaxResult delLottery(Prize prize);

    AjaxResult editLottery(Prize prize);

    AjaxResult getLotteryList(Prize prize);

    AjaxResult getPrizedList(Prize prize);

    AjaxResult addLotteryActive(Prize prize);

    AjaxResult getActiveList(Prize prize);
}
