package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SignEnvelopeRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SignEnvelopeRecordMapper {

    List<SignEnvelopeRecord> querySignEnvelopeRecord(SignEnvelopeRecord signEnvelopeRecord);



}
