package com.metyou.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Card {
    private Integer id;

    private Integer userId;

    private String wechat;

    private String username;

    private Integer level;

    private Integer status;

    private String service;

    private BigDecimal balance;

    private Date createTime;

    private String note;

    public Card(Integer id, Integer userId, String wechat, String username, Integer level, Integer status, String service, BigDecimal balance, Date createTime, String note) {
        this.id = id;
        this.userId = userId;
        this.wechat = wechat;
        this.username = username;
        this.level = level;
        this.status = status;
        this.service = service;
        this.balance = balance;
        this.createTime = createTime;
        this.note = note;
    }

    public Card() {
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

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service == null ? null : service.trim();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}