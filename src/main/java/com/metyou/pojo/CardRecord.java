package com.metyou.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class CardRecord {
    private Integer id;

    private Integer cardId;

    private BigDecimal money;

    private String operator;

    private Date createTime;

    private String creator;

    private Integer creatorId;

    private String note;

    public CardRecord(Integer id, Integer cardId, BigDecimal money, String operator, Date createTime, String creator, Integer creatorId, String note) {
        this.id = id;
        this.cardId = cardId;
        this.money = money;
        this.operator = operator;
        this.createTime = createTime;
        this.creator = creator;
        this.creatorId = creatorId;
        this.note = note;
    }

    public CardRecord() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}