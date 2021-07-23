package com.ruoyi.system.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserTradingLog {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private Integer tradingType;

    private String tradingSource;

    private BigDecimal tradingAmount;

    private BigDecimal tradingBeforeMoney;

    private BigDecimal tradingAfterMoney;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tradingTime;

    private String userName;

    private String nickName;

    public UserTradingLog(){

    }

    public UserTradingLog(Long id, Long userId, Integer tradingType, String tradingSource, BigDecimal tradingAmount, BigDecimal tradingBeforeMoney, BigDecimal tradingAfterMoney, Date tradingTime, String userName, String nickName) {
        this.id = id;
        this.userId = userId;
        this.tradingType = tradingType;
        this.tradingSource = tradingSource;
        this.tradingAmount = tradingAmount;
        this.tradingBeforeMoney = tradingBeforeMoney;
        this.tradingAfterMoney = tradingAfterMoney;
        this.tradingTime = tradingTime;
        this.userName = userName;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "UserTradingLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", tradingType=" + tradingType +
                ", tradingSource='" + tradingSource + '\'' +
                ", tradingAmount=" + tradingAmount +
                ", tradingBeforeMoney=" + tradingBeforeMoney +
                ", tradingAfterMoney=" + tradingAfterMoney +
                ", tradingTime=" + tradingTime +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTradingType() {
        return tradingType;
    }

    public void setTradingType(Integer tradingType) {
        this.tradingType = tradingType;
    }

    public String getTradingSource() {
        return tradingSource;
    }

    public void setTradingSource(String tradingSource) {
        this.tradingSource = tradingSource;
    }

    public BigDecimal getTradingAmount() {
        return tradingAmount;
    }

    public void setTradingAmount(BigDecimal tradingAmount) {
        this.tradingAmount = tradingAmount;
    }

    public BigDecimal getTradingBeforeMoney() {
        return tradingBeforeMoney;
    }

    public void setTradingBeforeMoney(BigDecimal tradingBeforeMoney) {
        this.tradingBeforeMoney = tradingBeforeMoney;
    }

    public BigDecimal getTradingAfterMoney() {
        return tradingAfterMoney;
    }

    public void setTradingAfterMoney(BigDecimal tradingAfterMoney) {
        this.tradingAfterMoney = tradingAfterMoney;
    }

    public Date getTradingTime() {
        return tradingTime;
    }

    public void setTradingTime(Date tradingTime) {
        this.tradingTime = tradingTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
