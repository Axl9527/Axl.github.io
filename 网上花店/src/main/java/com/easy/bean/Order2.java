package com.easy.bean;

import java.math.BigDecimal;
import java.util.List;

public class Order2 {
	private Integer id;
	private String code;
	private Integer uid;
	private Integer adminid;
	private String ordertime;
	private BigDecimal paymoney;
	private String paytime;
	private String deliverytime;
	private String gettime;
	private String endtime;
	private int state;
	private String oaddress;
	private int del;
	private List<Detail> list;
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getAdminid() {
		return adminid;
	}
	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
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
	public String getOaddress() {
		return oaddress;
	}
	public void setOaddress(String oaddress) {
		this.oaddress = oaddress;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
	public List<Detail> getList() {
		return list;
	}
	public void setList(List<Detail> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "Order2 [id=" + id + ", code=" + code + ", uid=" + uid + ", adminid=" + adminid + ", ordertime="
				+ ordertime + ", paymoney=" + paymoney + ", paytime=" + paytime + ", deliverytime=" + deliverytime
				+ ", gettime=" + gettime + ", endtime=" + endtime + ", state=" + state + ", oaddress=" + oaddress
				+ ", del=" + del + ", list=" + list + "]";
	}
	

}
