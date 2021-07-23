package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SignEnvelopeRecord;
import com.ruoyi.system.service.ISignEnvelopeRecorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SignEnvelopeRecorController  {

        @Autowired
        private ISignEnvelopeRecorService iSignEnvelopeRecorService;

        @PostMapping("/querySignEnvelopeRecor")
        public AjaxResult querySignEnvelopeRecor(@RequestBody SignEnvelopeRecord signEnvelopeRecord){

                        System.err.println("测试领签到红包"+signEnvelopeRecord.toString());
                        List<SignEnvelopeRecord> query = iSignEnvelopeRecorService.querySignEnvelopeRecord(signEnvelopeRecord);
                        System.err.println("查询签到领红包列表");
                        return AjaxResult.success("签到领红包列表"+query);
        }





}




