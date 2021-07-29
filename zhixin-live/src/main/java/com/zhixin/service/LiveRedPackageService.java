package com.zhixin.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.zhixin.vo.req.RedEnvelopeReqVO;

public interface LiveRedPackageService {

    AjaxResult sendRedEnvelope(RedEnvelopeReqVO redEnvelopeReqVO) throws Exception;

    AjaxResult getRedEnvelopeRecord(RedEnvelopeReqVO redEnvelopeReqVO);

    AjaxResult getRedEnvelopeReceiveRecord(RedEnvelopeReqVO redEnvelopeReqVO);
}
