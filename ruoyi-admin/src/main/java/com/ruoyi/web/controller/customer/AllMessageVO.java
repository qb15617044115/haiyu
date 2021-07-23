package com.ruoyi.web.controller.customer;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AllMessageVO {
    // 接收者 id
    private Long receiverId;
    // 头像
    private String headImg;
    // 昵称
    private String nickName;
    // 前端需要
    private Integer right = 0;
    // messageTemplate 对象
    private List<MessageTemplate> messageTemplates = new ArrayList<>();

    public AllMessageVO() {
    }
}
