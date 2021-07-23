package com.ruoyi.system.domain;

import java.math.BigDecimal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ruoyi.system.domain.vo.BaseDomain;

import lombok.Data;
/**
 * <p>
 * 用户表
 * </p>
 *
 * @author HaiBing.Liang
 * @since 2021-06-03
 */

public class TxzhUser extends BaseDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String nickname;

    private String doodling;

    private String email;

    private String phone;

    private Integer sex;

    private String password;

    private String tradePassword;

    private BigDecimal money;

    private BigDecimal freezeMoney;

    private Integer point;

    private Integer type;

    private Integer status;

    private Integer createTime;

    private String circliImg;

    private Integer isCustomerService;

    private Integer agentId;

    private Integer updateTime;

    private String clientId;

    private Boolean qPermition;

    private String tjUsername;

    private String ip;

    private String ipCityname;

    private Boolean ipStatus;

    private Boolean phoneStatus;

    private Boolean phoneType;

    private Boolean isRobot;

    private Long storge;

    private String deptCode;

    private String msmCode;

    private String headImg;

    private Integer deptId;

    protected String groupNickname;

    protected String verificationCode;

    private Integer noticeState;

    private Integer vibrationState;

    private Integer voiceState;

    private String condition;

    private UserBankCard userBankCard;

    private String deptName;


    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public UserBankCard getUserBankCard() {
        return userBankCard;
    }

    public void setUserBankCard(UserBankCard userBankCard) {
        this.userBankCard = userBankCard;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDoodling() {
        return doodling;
    }

    public void setDoodling(String doodling) {
        this.doodling = doodling;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getFreezeMoney() {
        return freezeMoney;
    }

    public void setFreezeMoney(BigDecimal freezeMoney) {
        this.freezeMoney = freezeMoney;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public String getCircliImg() {
        return circliImg;
    }

    public void setCircliImg(String circliImg) {
        this.circliImg = circliImg;
    }

    public Integer getIsCustomerService() {
        return isCustomerService;
    }

    public void setIsCustomerService(Integer isCustomerService) {
        this.isCustomerService = isCustomerService;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Boolean getqPermition() {
        return qPermition;
    }

    public void setqPermition(Boolean qPermition) {
        this.qPermition = qPermition;
    }

    public String getTjUsername() {
        return tjUsername;
    }

    public void setTjUsername(String tjUsername) {
        this.tjUsername = tjUsername;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIpCityname() {
        return ipCityname;
    }

    public void setIpCityname(String ipCityname) {
        this.ipCityname = ipCityname;
    }

    public Boolean getIpStatus() {
        return ipStatus;
    }

    public void setIpStatus(Boolean ipStatus) {
        this.ipStatus = ipStatus;
    }

    public Boolean getPhoneStatus() {
        return phoneStatus;
    }

    public void setPhoneStatus(Boolean phoneStatus) {
        this.phoneStatus = phoneStatus;
    }

    public Boolean getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(Boolean phoneType) {
        this.phoneType = phoneType;
    }

    public Boolean getRobot() {
        return isRobot;
    }

    public void setRobot(Boolean robot) {
        isRobot = robot;
    }

    public Long getStorge() {
        return storge;
    }

    public void setStorge(Long storge) {
        this.storge = storge;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getMsmCode() {
        return msmCode;
    }

    public void setMsmCode(String msmCode) {
        this.msmCode = msmCode;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getGroupNickname() {
        return groupNickname;
    }

    public void setGroupNickname(String groupNickname) {
        this.groupNickname = groupNickname;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Integer getNoticeState() {
        return noticeState;
    }

    public void setNoticeState(Integer noticeState) {
        this.noticeState = noticeState;
    }

    public Integer getVibrationState() {
        return vibrationState;
    }

    public void setVibrationState(Integer vibrationState) {
        this.vibrationState = vibrationState;
    }

    public Integer getVoiceState() {
        return voiceState;
    }

    public void setVoiceState(Integer voiceState) {
        this.voiceState = voiceState;
    }
}
