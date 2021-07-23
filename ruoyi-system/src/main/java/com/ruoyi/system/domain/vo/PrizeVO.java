package com.ruoyi.system.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PrizeVO {
    // id
    private Long id;
    // 房间id
    private Long roomId;
    // 用户id
    private Long userId;
    // 奖品id
    private Long prizeId;
    // 奖品名称
    private String prizeName;
    // 昵称
    private String UserNickname;
    // 奖品名称
    private BigDecimal prizeCost;
    // 奖品id数组(逗号分割,接收参数时使用)
    private String prizeIds;
    // 奖品价格(逗号分割,接收参数时使用)
    private String prizeCosts;
    // 奖品概率(逗号分割,接收参数时使用)
    private String prizeProbs;
    // 奖品名称(逗号分割,接收参数时使用)
    private String prizeNames;
    // 奖品图片(逗号分割,接收参数时使用)
    private String prizeImgurls;
    // 奖品数量(逗号分割,接收参数时使用)
    private String prizeNums;
    // 直播时间
    private String roomCtime;
    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    // 主播id
    private Long liveUserId;
    // 主播昵称
    private String liveUserNickname;
}
