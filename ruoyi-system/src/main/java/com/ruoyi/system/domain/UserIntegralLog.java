package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class UserIntegralLog {

    // id
    private Long id;
    // 用户id
    private Long userId;
    // 用户积分
    private Integer integral;
    // 用户
    private Integer integralType;
    // 用户操作前积分
    private Integer userBeforeIntegral;
    // 用户操作后积分
    private Integer userAfterIntegral;
    // 来源类型
    private Integer sourceType;
    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    // 主播id
    private Long liveId;
    private String liveNickname;
}
