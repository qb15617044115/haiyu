package com.ruoyi.system.domain;

import lombok.Data;

@Data
public class UserVisitLog {
    private Long id;
    private Long roomId;
    private String userId;
    private String sessionId;
    private String ip;
    private String transport;
    private String enterTime;
    private String leaveTime;
    private String referrer;
    private String area;
    private String createTime;
    private String username;
    private String userNickname;
}
