package com.ruoyi.system.domain;

import com.sun.org.apache.bcel.internal.generic.FLOAD;
import org.apache.ibatis.type.FloatTypeHandler;

import java.io.Serializable;

public class Withdrawal implements Serializable {

    private Integer id;
    private Integer numbers;
    private float minMoney;
    private float maxMoney;

    public Withdrawal() {
    }

    public Withdrawal(Integer id, Integer numbers, float minMoney, float maxMoney) {
        this.id = id;
        this.numbers = numbers;
        this.minMoney = minMoney;
        this.maxMoney = maxMoney;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public float getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(float minMoney) {
        this.minMoney = minMoney;
    }

    public float getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(float maxMoney) {
        this.maxMoney = maxMoney;
    }

    @Override
    public String toString() {
        return "Withdrawal{" +
                "id=" + id +
                ", numbers=" + numbers +
                ", minMoney=" + minMoney +
                ", maxMoney=" + maxMoney +
                '}';
    }
}
