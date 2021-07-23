package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserChargeWithdrawal extends BaseDomain{

    private Long id;

    private Long userId;

    private BigDecimal money;

    private int type;

    private int state;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auditTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private boolean complete;

    private String lastFour;

    private String bankId;
}
