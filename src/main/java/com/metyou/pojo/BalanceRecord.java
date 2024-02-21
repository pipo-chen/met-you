package com.metyou.pojo;

import java.util.Date;

public class BalanceRecord {
    private Integer id;

    private Integer userId;

    private String operationType;

    private Integer money;

    private Integer balance;

    private Integer creatorId;

    private Date createTime;

    private Date updateTime;

    public BalanceRecord(Integer id, Integer userId, String operationType, Integer money, Integer balance, Integer creatorId, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.operationType = operationType;
        this.money = money;
        this.balance = balance;
        this.creatorId = creatorId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public BalanceRecord() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType == null ? null : operationType.trim();
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}