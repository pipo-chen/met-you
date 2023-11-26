package com.metyou.pojo;

import java.util.Date;

public class Staff {
    private Integer id;

    private String username;

    private String wechat;

    private String email;

    private String phone;

    private Integer role;

    private Integer status;

    private String detail;

    private String slogan;

    private String mainImage;

    private String gender;

    private Date createTime;

    private Date updateTime;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Staff(Integer id, String username, String wechat, String email, String phone, Integer role, Integer status, String detail, String slogan, String mainImage, String gender, Date createTime, Date updateTime) {
        this.id = id;
        this.username = username;
        this.wechat = wechat;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.status = status;
        this.detail = detail;
        this.slogan = slogan;
        this.mainImage = mainImage;
        this.gender = gender;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
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
