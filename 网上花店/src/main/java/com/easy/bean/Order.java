package com.easy.bean;

import java.math.BigDecimal;
import java.util.List;

public class Order {
private long id;
private String code;
private Integer uid;
private String ordertime;
private BigDecimal paymoney;
private String paytime;
private String deliverytime;
private String gettime;
private String endtime;
private int state;
private String oaddress;
private User user;
private Address address;
private String sum;
private List<Detail> list;

public List<Detail> getList() {
	return list;
}
public void setList(List<Detail> list) {
	this.list = list;
}
public Integer getUid() {
	return uid;
}
public void setUid(Integer uid) {
	this.uid = uid;
}
public String getOaddress() {
	return oaddress;
}
public void setOaddress(String oaddress) {
	this.oaddress = oaddress;
}
public String getSum() {
	return sum;
}
public void setSum(String sum) {
	this.sum = sum;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Address getAddress() {
	return address;
}
public void setAddress(Address address) {
	this.address = address;
}
@Override
public String toString() {
	return "Order [id=" + id + ", code=" + code + ", uid=" + uid + ", ordertime=" + ordertime + ", paymoney="
			+ paymoney + ", paytime=" + paytime + ", deliverytime=" + deliverytime + ", gettime=" + gettime
			+ ", endtime=" + endtime + ", state=" + state + "]";
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public long getuid() {
	return uid;
}
public void setuid(Integer uid) {
	this.uid = uid;
}
public String getOrdertime() {
	return ordertime;
}
public void setOrdertime(String ordertime) {
	this.ordertime = ordertime;
}
public BigDecimal getPaymoney() {
	return paymoney;
}
public void setPaymoney(BigDecimal paymoney) {
	this.paymoney = paymoney;
}
public String getPaytime() {
	return paytime;
}
public void setPaytime(String paytime) {
	this.paytime = paytime;
}
public String getDeliverytime() {
	return deliverytime;
}
public void setDeliverytime(String deliverytime) {
	this.deliverytime = deliverytime;
}
public String getGettime() {
	return gettime;
}
public void setGettime(String gettime) {
	this.gettime = gettime;
}
public String getEndtime() {
	return endtime;
}
public void setEndtime(String endtime) {
	this.endtime = endtime;
}
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}

}
