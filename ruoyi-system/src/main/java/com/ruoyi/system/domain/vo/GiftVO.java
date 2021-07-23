package com.ruoyi.system.domain.vo;

import lombok.Data;

@Data
public class GiftVO extends BaseDomain{
    // 礼物名称
    private String giftName;
    // 礼物地址
    private String giftUrl;
    // 礼物价格
    private String giftCost;
    // 礼物 id
    private Long giftId;
    // 主播id
    private Long liveUserId;
    // 送礼时间
    private String createTime;
    // 房间id
    private Long roomId;
    // 送礼记录id
    private Long id;
    // 用户id
    private Long userId;
    // 用户昵称
    private String nickname;
    // 用户名
    private String username;
    // 主播名称
    private String liveUserNickname;
}
