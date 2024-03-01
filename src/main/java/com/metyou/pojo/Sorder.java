package com.metyou.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Sorder {
    private Integer id;

    private Integer userId;

    private Integer commodityId;

    private Integer supervisId;

    private String supervisName;

    private BigDecimal salePrice;

    private BigDecimal originPrice;

    private Integer payway;

    private Integer status;

    private BigDecimal balance;

    private Integer cardId;

    private Date beginTime;

    private Date endTime;

    private String note;

    private Date createTime;

    public Sorder(Integer userId, Integer commodityId, String supervisName, BigDecimal salePrice, Integer payway, Integer cardId, String note) {
        this.userId = userId;
        this.commodityId = commodityId;
        this.supervisName = supervisName;
        this.salePrice = salePrice;
        this.payway = payway;
        this.cardId = cardId;
        this.note = note;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Sorder(Integer id, Integer userId, Integer commodityId, Integer supervisId, String supervisName, BigDecimal salePrice, BigDecimal originPrice, Integer payway, Integer status, BigDecimal balance, Integer cardId, Date beginTime, Date endTime, String note, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.commodityId = commodityId;
        this.supervisId = supervisId;
        this.supervisName = supervisName;
        this.salePrice = salePrice;
        this.originPrice = originPrice;
        this.payway = payway;
        this.status = status;
        this.balance = balance;
        this.cardId = cardId;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.note = note;
        this.createTime = createTime;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Sorder() {
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

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getSupervisId() {
        return supervisId;
    }

    public void setSupervisId(Integer supervisId) {
        this.supervisId = supervisId;
    }

    public String getSupervisName() {
        return supervisName;
    }

    public void setSupervisName(String supervisName) {
        this.supervisName = supervisName == null ? null : supervisName.trim();
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(BigDecimal originPrice) {
        this.originPrice = originPrice;
    }

    public Integer getPayway() {
        return payway;
    }

    public void setPayway(Integer payway) {
        this.payway = payway;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}