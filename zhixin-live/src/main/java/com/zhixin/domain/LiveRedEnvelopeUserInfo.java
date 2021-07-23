package com.zhixin.domain;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class LiveRedEnvelopeUserInfo {

    private Long userid;

    private String username;

    private String nickname;

    private BigDecimal money;

    private String time;
}
