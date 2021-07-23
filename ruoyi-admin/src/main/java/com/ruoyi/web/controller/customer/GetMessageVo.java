package com.ruoyi.web.controller.customer;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetMessageVo {
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

    public GetMessageVo() {
    }

    public GetMessageVo(Long receiverId, List<MessageTemplate> messageTemplates, String headImg, String nickName) {
        this.receiverId = receiverId;
        this.messageTemplates = messageTemplates;
        this.headImg = headImg;
        this.nickName = nickName;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public List<MessageTemplate> getMessageTemplates() {
        return messageTemplates;
    }

    public void setMessageTemplates(List<MessageTemplate> messageTemplates) {
        this.messageTemplates = messageTemplates;
    }
}
