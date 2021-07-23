package com.zhixin.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.zhixin.service.LiveRedPackageService;
import com.zhixin.vo.req.RedEnvelopeReqVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/live")
public class LiveRedPackageController {

    @Autowired
    private LiveRedPackageService liveRedPackageService;

    @RequestMapping(value = "/sendRedEnvelope",method = RequestMethod.POST)
    public AjaxResult sendRedEnvelope(@RequestBody RedEnvelopeReqVO redEnvelopeReqVO){
        return liveRedPackageService.sendRedEnvelope(redEnvelopeReqVO);
    }

    @RequestMapping(value = "/getRedEnvelopeRecord",method = RequestMethod.POST)
    public AjaxResult getRedEnvelopeRecord(@RequestBody RedEnvelopeReqVO redEnvelopeReqVO){
        return liveRedPackageService.getRedEnvelopeRecord(redEnvelopeReqVO);
    }
}
