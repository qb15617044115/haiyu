package com.zhixin.domain;


import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ZhixinLiveRedEnvelope {

    private Long id;

    private Long sendUserId;

    private Integer state;

    private BigDecimal money;

    private BigDecimal remainingMoney;

    private int total;

    private String randomAmount;

    private String receiveUserInfo;

    private Date createDate;
}
