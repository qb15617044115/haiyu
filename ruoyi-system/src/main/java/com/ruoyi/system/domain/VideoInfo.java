package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class VideoInfo {
    private Long id;
    private String userId;
    private String userNickname;
    private String videoName;
    private String logoUrl;
    private String pullUrl;
    private String organId;
    private String isIncludeSuborgan;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expireTime;
    private Integer watchLimitMax;
    private Integer watchNum;
    private Integer likeNum;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date closeTime;
    private Integer status;
    private Integer onlineFixNum;
    private Integer onlineVarNum;
    private String backgroundUrl;
    private String videoType;
    private String createTime;
}
