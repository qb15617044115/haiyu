package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.TxzhConfigAll;

public interface ITxzhConfigAllService {
    AjaxResult listConfigAll();

    AjaxResult addConfigAll(TxzhConfigAll txzhConfigAll);

    AjaxResult getRTK();

    AjaxResult updateRTKStatus(TxzhConfigAll txzhConfigAll);

    AjaxResult listIntegralConfig();

    AjaxResult updateIntegralConfig(TxzhConfigAll txzhConfigAll);
}
