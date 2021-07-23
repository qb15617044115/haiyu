package com.zhixin.vo.req;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RedEnvelopeReqVO {

    //主播id
    private Long userId;
    //主播名称
    private String userName;
    //红包id
    private Long redEnvelopeId;
    //金额
    private BigDecimal money;
    //最大金额
    private BigDecimal maxMoney;
    //最小金额
    private BigDecimal minMoney;
    //数量
    private Integer number;
    //数量
    private Integer size;
    //页数
    private Integer page;

}
