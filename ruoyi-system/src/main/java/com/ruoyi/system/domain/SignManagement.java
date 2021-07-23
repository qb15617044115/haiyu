package com.ruoyi.system.domain;

import java.io.Serializable;

public class SignManagement implements Serializable {

    private Integer id ;

    private float dailycheckinminimumamount;

    private float maximumnumberofdailycheckins;

    private Integer consecutivedays;

    private float continuoussigninamount;

    private Integer state;

    public SignManagement() {
    }

    public SignManagement(Integer id, float dailycheckinminimumamount, float maximumnumberofdailycheckins, Integer consecutivedays, float continuoussigninamount, Integer state) {
        this.id = id;
        this.dailycheckinminimumamount = dailycheckinminimumamount;
        this.maximumnumberofdailycheckins = maximumnumberofdailycheckins;
        this.consecutivedays = consecutivedays;
        this.continuoussigninamount = continuoussigninamount;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getDailycheckinminimumamount() {
        return dailycheckinminimumamount;
    }

    public void setDailycheckinminimumamount(float dailycheckinminimumamount) {
        this.dailycheckinminimumamount = dailycheckinminimumamount;
    }

    public float getMaximumnumberofdailycheckins() {
        return maximumnumberofdailycheckins;
    }

    public void setMaximumnumberofdailycheckins(float maximumnumberofdailycheckins) {
        this.maximumnumberofdailycheckins = maximumnumberofdailycheckins;
    }

    public Integer getConsecutivedays() {
        return consecutivedays;
    }

    public void setConsecutivedays(Integer consecutivedays) {
        this.consecutivedays = consecutivedays;
    }

    public float getContinuoussigninamount() {
        return continuoussigninamount;
    }

    public void setContinuoussigninamount(float continuoussigninamount) {
        this.continuoussigninamount = continuoussigninamount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "SignManagement{" +
                "id=" + id +
                ", dailycheckinminimumamount=" + dailycheckinminimumamount +
                ", maximumnumberofdailycheckins=" + maximumnumberofdailycheckins +
                ", consecutivedays=" + consecutivedays +
                ", continuoussigninamount=" + continuoussigninamount +
                ", state=" + state +
                '}';
    }
}
