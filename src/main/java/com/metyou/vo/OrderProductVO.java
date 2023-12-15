package com.metyou.vo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderProductVO {
    private Long orderNo;

    private Integer userId;

    private Integer shippingId;

    private BigDecimal payment;

    private Integer paymentType;

    private Integer postage;

    private Integer status;

    private Date paymentTime;

    private Date sendTime;

    private Date endTime;

    private Date closeTime;

    private Date createTime;

    private Date updateTime;

    private Integer id;

    private String username;

    private String password;

    private String wechat;

    private String email;

    private String phone;

    private Integer role;

    private Integer productId;

    private String productName;

    private String productImage;

    private BigDecimal currentUnitPrice;

    private Integer quantity;

    private BigDecimal totalPrice;

    private Integer staffId;

    private Date startserviceTime;

    private Date endserviceTime;

    private Integer serviceTime;

    private Integer delayTime;

    public OrderProductVO(Long orderNo,
                          BigDecimal payment,
                          Integer paymentType,
                          Date paymentTime,
                          Integer id,
                          String username,
                          String wechat,
                          Integer productId,
                          String productName,
                          BigDecimal currentUnitPrice,
                          Integer quantity,
                          String productImage,
                          Integer serviceTime,
                          Integer delayTime) {
        this.orderNo = orderNo;
        this.payment = payment;
        this.paymentType = paymentType;
        this.paymentTime = paymentTime;
        this.id = id;
        this.username = username;
        this.wechat = wechat;
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.currentUnitPrice = currentUnitPrice;
        this.quantity = quantity;
        this.serviceTime = serviceTime;
        this.delayTime = delayTime;
    }

    public OrderProductVO(Long orderNo, Integer userId, Integer shippingId, BigDecimal payment, Integer paymentType, Integer postage, Integer status, Date paymentTime, Date sendTime, Date endTime, Date closeTime, Date createTime, Date updateTime, Integer id, String username, String password, String wechat, String email, String phone, Integer role, Integer productId, String productName, String productImage, BigDecimal currentUnitPrice, Integer quantity, BigDecimal totalPrice, Integer staffId, Date startserviceTime, Date endserviceTime, Integer serviceTime, Integer delayTime) {
        this.orderNo = orderNo;
        this.userId = userId;
        this.shippingId = shippingId;
        this.payment = payment;
        this.paymentType = paymentType;
        this.postage = postage;
        this.status = status;
        this.paymentTime = paymentTime;
        this.sendTime = sendTime;
        this.endTime = endTime;
        this.closeTime = closeTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.id = id;
        this.username = username;
        this.password = password;
        this.wechat = wechat;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.currentUnitPrice = currentUnitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.staffId = staffId;
        this.startserviceTime = startserviceTime;
        this.endserviceTime = endserviceTime;
        this.serviceTime = serviceTime;
        this.delayTime = delayTime;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShippingId() {
        return shippingId;
    }

    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getPostage() {
        return postage;
    }

    public void setPostage(Integer postage) {
        this.postage = postage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public BigDecimal getCurrentUnitPrice() {
        return currentUnitPrice;
    }

    public void setCurrentUnitPrice(BigDecimal currentUnitPrice) {
        this.currentUnitPrice = currentUnitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Date getStartserviceTime() {
        return startserviceTime;
    }

    public void setStartserviceTime(Date startserviceTime) {
        this.startserviceTime = startserviceTime;
    }

    public Date getEndserviceTime() {
        return endserviceTime;
    }

    public void setEndserviceTime(Date endserviceTime) {
        this.endserviceTime = endserviceTime;
    }

    public Integer getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Integer serviceTime) {
        this.serviceTime = serviceTime;
    }

    public Integer getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Integer delayTime) {
        this.delayTime = delayTime;
    }
}
