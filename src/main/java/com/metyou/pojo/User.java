package com.metyou.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class User {
    private Integer id;

    private String username;

    private String password;

    private String wechat;

    private String email;

    private String phone;

    private String question;

    private String answer;

    private Integer role;

    private Integer balance;

    private Integer integral;

    private Integer level;

    private String description;

    private String headpic;

    private Date createTime;

    private Date updateTime;


    public User() {
        super();
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public User(Integer id, String username, String password, String wechat, String email, String phone, String question, String answer, Integer role, Integer balance, Integer integral, Integer level, String description, String headpic, Date createTime, Date updateTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.wechat = wechat;
        this.email = email;
        this.phone = phone;
        this.question = question;
        this.answer = answer;
        this.role = role;
        this.balance = balance;
        this.integral = integral;
        this.level = level;
        this.description = description;
        this.headpic = headpic;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public User(Integer id, String username, String password, String email, String phone, String question, String answer, Integer role, String description, String headpic, Date createTime, Date updateTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.question = question;
        this.answer = answer;
        this.role = role;
        this.description = description;
        this.headpic = headpic;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public User(Integer id, String username, String password, String wechat, String email, String phone, String question, String answer, Integer role, String description, String headpic, Date createTime, Date updateTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.wechat = wechat;
        this.email = email;
        this.phone = phone;
        this.question = question;
        this.answer = answer;
        this.role = role;
        this.description = description;
        this.headpic = headpic;
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
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
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