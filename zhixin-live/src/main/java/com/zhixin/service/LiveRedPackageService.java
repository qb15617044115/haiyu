package com.zhixin.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.zhixin.vo.req.RedEnvelopeReqVO;

public interface LiveRedPackageService {

    AjaxResult sendRedEnvelope(RedEnvelopeReqVO redEnvelopeReqVO);

    AjaxResult getRedEnvelopeRecord(RedEnvelopeReqVO redEnvelopeReqVO);
}
