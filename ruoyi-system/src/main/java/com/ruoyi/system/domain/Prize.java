package com.ruoyi.system.domain;

import com.ruoyi.system.domain.vo.BaseDomain;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Prize extends BaseDomain {
    // 奖品id
    private Long id;
    // 奖品名称
    private String prizeName;
    // 奖品图片地址
    private String prizeImgurl;
    // 奖品价格
    private BigDecimal prizeCost;
    // 礼物概率
    private BigDecimal prizeProb;
    // 礼物数量
    private Integer prizeNum;
    // 房间id
    private Long roomId;
    // 主播id
    private Long liveUserId;
    // ??
    private Integer prizeOrder;
    // 是否删除
    private Integer isDel;
    // 时间
    private String createTime;
    // 奖品id数组
    private String lotteryIds;
    // 用户id数组
    private String userIds;
    // 是否开启抽奖
    private Integer lotteryPower;
    // 主播昵称
    private String liveUserNickname;
}
