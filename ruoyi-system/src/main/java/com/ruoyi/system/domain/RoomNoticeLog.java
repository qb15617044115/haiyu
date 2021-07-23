package com.ruoyi.system.domain;

import lombok.Data;

@Data
public class RoomNoticeLog {
    private Long id;
    private Long roomId;
    private String userId;
    private String userNickname;
    private String title;
    private String content;
    private String createTime;
}
