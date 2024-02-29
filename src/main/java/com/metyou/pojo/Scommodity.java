package com.metyou.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Scommodity {
    private Integer id;

    private String name;

    private String descr;

    private String detail;

    private String headimg;

    private BigDecimal prcie;

    private Integer status;

    private Date createTime;

    private String note;

    public Scommodity(Integer id, String name, String descr, String detail, String headimg, BigDecimal prcie, Integer status, Date createTime, String note) {
        this.id = id;
        this.name = name;
        this.descr = descr;
        this.detail = detail;
        this.headimg = headimg;
        this.prcie = prcie;
        this.status = status;
        this.createTime = createTime;
        this.note = note;
    }

    public Scommodity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg == null ? null : headimg.trim();
    }

    public BigDecimal getPrcie() {
        return prcie;
    }

    public void setPrcie(BigDecimal prcie) {
        this.prcie = prcie;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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