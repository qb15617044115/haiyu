package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class TxzhCircle implements Serializable {

    private Integer id;//字段id

    private BigInteger userId;//用户id

    private String conTent;//内容

    private Integer thumbsUp;//点赞数量

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//发朋友圈的时间

    private String imgs;//图片

    private String headImg;//头像

    private String nickName;//用户昵称

    private Integer type;//1 为图片朋友圈  2 为视频朋友圈

    private String video;//视频连接

    private Integer hiddenContent;//朋友圈违规内容隐藏

    public TxzhCircle() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getConTent() {
        return conTent;
    }

    public void setConTent(String conTent) {
        this.conTent = conTent;
    }

    public Integer getThumbsUp() {
        return thumbsUp;
    }

    public void setThumbsUp(Integer thumbsUp) {
        this.thumbsUp = thumbsUp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Integer getHiddenContent() {
        return hiddenContent;
    }

    public void setHiddenContent(Integer hiddenContent) {
        this.hiddenContent = hiddenContent;
    }

    public TxzhCircle(Integer id, BigInteger userId, String conTent, Integer thumbsUp, Date createTime, String imgs, String headImg, String nickName, Integer type, String video, Integer hiddenContent) {
        this.id = id;
        this.userId = userId;
        this.conTent = conTent;
        this.thumbsUp = thumbsUp;
        this.createTime = createTime;
        this.imgs = imgs;
        this.headImg = headImg;
        this.nickName = nickName;
        this.type = type;
        this.video = video;
        this.hiddenContent = hiddenContent;
    }

    @Override
    public String toString() {
        return "TxzhCircle{" +
                "id=" + id +
                ", userId=" + userId +
                ", conTent='" + conTent + '\'' +
                ", thumbsUp=" + thumbsUp +
                ", createTime=" + createTime +
                ", imgs='" + imgs + '\'' +
                ", headImg='" + headImg + '\'' +
                ", nickName='" + nickName + '\'' +
                ", type=" + type +
                ", video='" + video + '\'' +
                ", hiddenContent=" + hiddenContent +
                '}';
    }
}
