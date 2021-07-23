package com.ruoyi.system.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data

public class UserBankCard {
    private Long id;
    private String bankCard;
    private String bankName;
    private String bankAccountName;
    private String bankCardName;
    private String bankCode;
    private String bankType;
    private String bankProvince;
    private String bankCity;
    private String bankLog;
    private String idCard;
    private String bankUserName;
    private Long bankUserId;
    private int bankState;

    private Long createBy;

    private Date createTime;

    private List<String> bankCards = new ArrayList<>();
}
