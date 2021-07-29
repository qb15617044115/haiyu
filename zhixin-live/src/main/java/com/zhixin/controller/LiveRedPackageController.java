package com.zhixin.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.zhixin.service.LiveRedPackageService;
import com.zhixin.vo.req.RedEnvelopeReqVO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/live")
@CrossOrigin
public class LiveRedPackageController {

    @Autowired
    private LiveRedPackageService liveRedPackageService;

    @RequestMapping(value = "/sendRedEnvelope",method = RequestMethod.POST)
    public AjaxResult sendRedEnvelope(@RequestBody RedEnvelopeReqVO redEnvelopeReqVO){
        try {
            return liveRedPackageService.sendRedEnvelope(redEnvelopeReqVO);
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("系统内部错误，错误消息:" + e.getMessage());
        }
    }

    @RequestMapping(value = "/getRedEnvelopeRecord",method = RequestMethod.POST)
    public AjaxResult getRedEnvelopeRecord(@RequestBody RedEnvelopeReqVO redEnvelopeReqVO){
        return liveRedPackageService.getRedEnvelopeRecord(redEnvelopeReqVO);
    }

    @RequestMapping(value = "/getRedEnvelopeReceiveRecord",method = RequestMethod.POST)
    public AjaxResult getRedEnvelopeReceiveRecord(@RequestBody RedEnvelopeReqVO redEnvelopeReqVO){
        return liveRedPackageService.getRedEnvelopeReceiveRecord(redEnvelopeReqVO);
    }
}
