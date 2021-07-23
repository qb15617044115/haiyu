package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SignEnvelopeRecord;
import com.ruoyi.system.mapper.SignEnvelopeRecordMapper;
import com.ruoyi.system.service.ISignEnvelopeRecorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignEnvelopeRecorServiceImpl implements ISignEnvelopeRecorService {

    @Autowired
    private SignEnvelopeRecordMapper signEnvelopeRecordMapper;


    @Override
    public List<SignEnvelopeRecord> querySignEnvelopeRecord(SignEnvelopeRecord signEnvelopeRecord) {
        return signEnvelopeRecordMapper.querySignEnvelopeRecord(signEnvelopeRecord);
    }


}
