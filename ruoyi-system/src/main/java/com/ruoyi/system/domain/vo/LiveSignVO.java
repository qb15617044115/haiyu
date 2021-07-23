package com.ruoyi.system.domain.vo;

import lombok.Data;

@Data
public class LiveSignVO {
    private Long id;
    private String userId;
    private String userNickname;
    private Long roomId;
    private String roomName;
    private String createTime;
    private Integer point;
}
