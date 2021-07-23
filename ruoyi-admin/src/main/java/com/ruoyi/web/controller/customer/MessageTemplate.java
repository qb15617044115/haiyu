package com.ruoyi.web.controller.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class MessageTemplate {
    // 消息 id
    private Long id;
    // 内容
    private String content;
    // 消息类型   1为发送者   0为接收者
    private int type;
    // 消息状态   1为已读    0为未读d
    private int status;
    // 发送时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    // 红包或转账id
    private String transferId;
    //消息类型(1 普通消息 2 红包 3 文件 4 图片 5 视频 6 语音 7 群发红包 8 转账)
    private int messageType;
    //红包剩余数量
    private int num;
    //红包领取状态
    private int packageState;
    //金额
    private BigDecimal money;
    //转账状态
    private int transferState;
    //领取id
    private List<Long> userIds;
    //随机金额数组
    private List<BigDecimal> moneys;

}
