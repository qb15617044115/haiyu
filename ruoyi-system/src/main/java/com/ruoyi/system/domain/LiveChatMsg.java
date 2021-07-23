package com.ruoyi.system.domain;

import lombok.Data;

@Data
public class LiveChatMsg {
    private Long roomId;
    private String userId;
    private String sid;
    private String nickname;
    private String username;
    private String ip;
    private String refer;
    private String createTime;
    private String msg;
    private boolean isforbid;
}
