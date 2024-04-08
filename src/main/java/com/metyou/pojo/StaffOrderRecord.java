package com.metyou.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class StaffOrderRecord {
    private Integer id;

    private Integer orderId;

    private Integer staffId;

    private String staffName;

    private BigDecimal commission;

    private Integer orderStatus;

    private String creator;

    private Integer creatorId;

    private Date createTime;

    private Date updateTime;

    private String note;

    public StaffOrderRecord(Integer id, Integer orderId, Integer staffId, String staffName, BigDecimal commission, Integer orderStatus, String creator, Integer creatorId, Date createTime, Date updateTime, String note) {
        this.id = id;
        this.orderId = orderId;
        this.staffId = staffId;
        this.staffName = staffName;
        this.commission = commission;
        this.orderStatus = orderStatus;
        this.creator = creator;
        this.creatorId = creatorId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.note = note;
    }

    public StaffOrderRecord() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}