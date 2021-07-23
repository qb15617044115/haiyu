package com.ruoyi.system.domain;

import lombok.Data;

@Data
public class LiveOnlineUser {
    private Long userId;
    private String username;
    private String nickname;
    private Integer say;
    private Integer kick;
    private Integer video;
    private Integer black;
}
