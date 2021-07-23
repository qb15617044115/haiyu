package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class SignEnvelopeRecord implements Serializable {


    private Integer id;

    private Long userId; //用户签到ID

    private  float total;//签到得到的红包

    private  Integer theNumber;//第几次获取的

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    @Override
    public String toString() {
        return "Sign{" +
                "id=" + id +
                ", userId=" + userId +
                ", total=" + total +
                ", theNumber=" + theNumber +
                ", createTime=" + createTime +
                '}';
    }

    public SignEnvelopeRecord(Integer id, Long userId, float total, Integer theNumber, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.total = total;
        this.theNumber = theNumber;
        this.createTime = createTime;
    }

    public SignEnvelopeRecord() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Integer getTheNumber() {
        return theNumber;
    }

    public void setTheNumber(Integer theNumber) {
        this.theNumber = theNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
