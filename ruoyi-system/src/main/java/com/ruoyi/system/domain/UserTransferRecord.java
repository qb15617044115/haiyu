package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserTransferRecord {
    // id
    private Long id;
    // 用户id
    private Long userId;
    // 接收者id
    private Long receiveId;
    // 发送金额
    private BigDecimal amount;
    // 剩余金额
    private BigDecimal money;
    // 数量
    private Integer total;
    // 类型(1 单人红包 ，  2 群发红包  ，3 转账)
    private Integer type;
    // 转账状态(1:接受 2:未接受 3:未接受完 4:已过期退还)
    private Integer state;
    // 接收人
    private String recipient;
    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
