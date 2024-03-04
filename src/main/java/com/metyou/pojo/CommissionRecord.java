package com.metyou.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class CommissionRecord {
    private Integer id;

    private Integer staffId;

    private String staffName;

    private Integer orderId;

    private String operator;

    private String creator;

    private Integer creatorId;

    private BigDecimal commission;

    private Date createTime;

    private String note;

    public CommissionRecord(Integer id, Integer staffId, String staffName, Integer orderId, String operator, String creator, Integer creatorId, BigDecimal commission, Date createTime, String note) {
        this.id = id;
        this.staffId = staffId;
        this.staffName = staffName;
        this.orderId = orderId;
        this.operator = operator;
        this.creator = creator;
        this.creatorId = creatorId;
        this.commission = commission;
        this.createTime = createTime;
        this.note = note;
    }

    public CommissionRecord() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
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

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
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