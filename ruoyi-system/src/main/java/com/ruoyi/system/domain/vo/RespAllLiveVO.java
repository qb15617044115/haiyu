package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RespAllLiveVO {
    // 主播id
    private String live_user_id;
    // 主播名称
    private String userNickname;
    // 新用户数量
    private BigDecimal newUserCount;
    // 房间 id
    private String roomName;
    // 房间峰值
    private BigDecimal userCount;
    // 观看人数
    private BigDecimal watchNum;
    // 点赞数量
    private BigDecimal likeNum;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // 创建时间
    private Date createTime;
    // 房间id
    private BigDecimal room_id;
}
