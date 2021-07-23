package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

public class UserAccount extends BaseEntity {
    /** 账号 */
    private String account;
    /** 密码 */
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
