package com.ruoyi.system.domain.vo;

import lombok.Data;

@Data
public class LiveVO extends BaseDomain{
    // 房间id
    private Long roomId;
    private Long onlineFixNum;
    private Long onlineVarNum;
    // 主播id
    private Long liveUserId;
    // 头像
    private String headImg;
    // 昵称
    private String nickname;
    // 房间配置
    private String confKey;
    // 0为关  1为开
    private String confValue;
    // 用户id
    private Long userId;
    // 1为过滤的消息  0为所有消息
    private Integer isFilter;
    // 管理房间类型  1为禁言  2为踢出  3为拉黑  4为禁止观看视频
    private String forbidType;
    // 敏感词 id
    private Long wordId;
    // 过滤标记符
    private String word;
    // 规则长度
    private String rule;
    // 表情id
    private Long stickerId;
    // 1为硬删除  其他为软删除
    private Integer isHard;
    // 表情名称
    private String stickerName;
    // 表情地址
    private String stickerUrl;
    // 表情类型
    private Integer stickerType;
    // 直播背景图
    private String backgroundUrl;
    // 公告内容
    private String noticeContent;
    // 公告标题
    private String noticeTitle;
    // 红包id
    private Long bagId;
    // 红包类型
    private String bagType;
    // 用户状态
    private Integer userState;
    // 分钟
    private String min;
    // 背景图状态
    private Integer type;
    // 查询新 ip 时间
    private String time;
    // 删除公告时使用
    private Long id;
}
