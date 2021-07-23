package com.ruoyi.system.domain;

import java.io.Serializable;
import java.math.BigInteger;

public class SysColumn implements Serializable {

    private Integer id;  //模块id
    private String  moduleNumber; //模块排序编号
    private String  moduleName;//模块名称
    private String  moduleType;//模块类型
    private String  moduleInitial;//模块首字母
    private String  typeContent;//类型内容
    private BigInteger sysUserId;//用户id
    private BigInteger    userId;

    public SysColumn() {
    }

    public SysColumn(Integer id, String moduleNumber, String moduleName, String moduleType, String moduleInitial, String typeContent, BigInteger sysUserId, BigInteger userId) {
        this.id = id;
        this.moduleNumber = moduleNumber;
        this.moduleName = moduleName;
        this.moduleType = moduleType;
        this.moduleInitial = moduleInitial;
        this.typeContent = typeContent;
        this.sysUserId = sysUserId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModuleNumber() {
        return moduleNumber;
    }

    public void setModuleNumber(String moduleNumber) {
        this.moduleNumber = moduleNumber;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getModuleInitial() {
        return moduleInitial;
    }

    public void setModuleInitial(String moduleInitial) {
        this.moduleInitial = moduleInitial;
    }

    public String getTypeContent() {
        return typeContent;
    }

    public void setTypeContent(String typeContent) {
        this.typeContent = typeContent;
    }

    public BigInteger getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(BigInteger sysUserId) {
        this.sysUserId = sysUserId;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SysColumn{" +
                "id=" + id +
                ", moduleNumber='" + moduleNumber + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", moduleType='" + moduleType + '\'' +
                ", moduleInitial='" + moduleInitial + '\'' +
                ", typeContent='" + typeContent + '\'' +
                ", sysUserId=" + sysUserId +
                ", userId=" + userId +
                '}';
    }
}
