package com.ruoyi.system.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class TxzhCustomerAdmin implements Serializable {


  private  long id;

  private  String username;

  private  String nickname;

  private  String doodling;

  private  String email;

  private  String phone;

  private  Integer sex;

  private  String password;

  private  String tradePassword;

  private  BigDecimal money;

  private  BigDecimal freeze_money;

  private  Integer  point;

  private  Integer  type;

  private  Integer  status;

  private  Integer  createTime;

  private  String circliImg;

  private Integer isCustomerService;

  private Integer agentId;

  private Integer updateTime;

  private String clientId;

  private boolean  qpermition;

  private String tjusername;

  private String ip;

  private String ipCityname;

  private boolean ipStatus;

  private boolean phonestatus;

  private boolean phonetype;

  private boolean is_robot;

  private long storge;

  private String head_img;

  private long dept_id;


  public TxzhCustomerAdmin() {
  }

  public TxzhCustomerAdmin(long id, String username, String nickname, String doodling, String email, String phone, Integer sex, String password, String tradePassword, BigDecimal money, BigDecimal freeze_money, Integer point, Integer type, Integer status, Integer createTime, String circliImg, Integer isCustomerService, Integer agentId, Integer updateTime, String clientId, boolean qpermition, String tjusername, String ip, String ipCityname, boolean ipStatus, boolean phonestatus, boolean phonetype, boolean is_robot, long storge, String head_img, long dept_id) {
    this.id = id;
    this.username = username;
    this.nickname = nickname;
    this.doodling = doodling;
    this.email = email;
    this.phone = phone;
    this.sex = sex;
    this.password = password;
    this.tradePassword = tradePassword;
    this.money = money;
    this.freeze_money = freeze_money;
    this.point = point;
    this.type = type;
    this.status = status;
    this.createTime = createTime;
    this.circliImg = circliImg;
    this.isCustomerService = isCustomerService;
    this.agentId = agentId;
    this.updateTime = updateTime;
    this.clientId = clientId;
    this.qpermition = qpermition;
    this.tjusername = tjusername;
    this.ip = ip;
    this.ipCityname = ipCityname;
    this.ipStatus = ipStatus;
    this.phonestatus = phonestatus;
    this.phonetype = phonetype;
    this.is_robot = is_robot;
    this.storge = storge;
    this.head_img = head_img;
    this.dept_id = dept_id;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
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

  public BigDecimal getFreeze_money() {
    return freeze_money;
  }

  public void setFreeze_money(BigDecimal freeze_money) {
    this.freeze_money = freeze_money;
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

  public boolean isQpermition() {
    return qpermition;
  }

  public void setQpermition(boolean qpermition) {
    this.qpermition = qpermition;
  }

  public String getTjusername() {
    return tjusername;
  }

  public void setTjusername(String tjusername) {
    this.tjusername = tjusername;
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

  public boolean isIpStatus() {
    return ipStatus;
  }

  public void setIpStatus(boolean ipStatus) {
    this.ipStatus = ipStatus;
  }

  public boolean isPhonestatus() {
    return phonestatus;
  }

  public void setPhonestatus(boolean phonestatus) {
    this.phonestatus = phonestatus;
  }

  public boolean isPhonetype() {
    return phonetype;
  }

  public void setPhonetype(boolean phonetype) {
    this.phonetype = phonetype;
  }

  public boolean isIs_robot() {
    return is_robot;
  }

  public void setIs_robot(boolean is_robot) {
    this.is_robot = is_robot;
  }

  public long getStorge() {
    return storge;
  }

  public void setStorge(long storge) {
    this.storge = storge;
  }

  public String getHead_img() {
    return head_img;
  }

  public void setHead_img(String head_img) {
    this.head_img = head_img;
  }

  public long getDept_id() {
    return dept_id;
  }

  public void setDept_id(long dept_id) {
    this.dept_id = dept_id;
  }
}
