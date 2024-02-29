package com.metyou.pojo;

import java.util.Date;

public class Member {
    private Integer id;

    private String username;

    private String wechat;

    private String password;

    private String headimg;

    private Integer role;

    private String tag;

    private Integer points;

    private String comment;

    private Date birthday;

    private Date createTime;

    private String note;

    public Member(Integer id, String username, String wechat, String password, String headimg, Integer role, String tag, Integer points, String comment, Date birthday, Date createTime, String note) {
        this.id = id;
        this.username = username;
        this.wechat = wechat;
        this.password = password;
        this.headimg = headimg;
        this.role = role;
        this.tag = tag;
        this.points = points;
        this.comment = comment;
        this.birthday = birthday;
        this.createTime = createTime;
        this.note = note;
    }

    public Member() {
        super();
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
        this.username = username == null ? null : username.trim();
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg == null ? null : headimg.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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