package com.easy.bean;

import java.math.BigDecimal;
import java.util.List;

public class OrderAll {
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
	private String sum;
	private List<Detail> list;
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
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
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
	public List<Detail> getList() {
		return list;
	}
	public void setList(List<Detail> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "OrderAll [id=" + id + ", code=" + code + ", uid=" + uid + ", ordertime=" + ordertime + ", paymoney="
				+ paymoney + ", paytime=" + paytime + ", deliverytime=" + deliverytime + ", gettime=" + gettime
				+ ", endtime=" + endtime + ", state=" + state + ", oaddress=" + oaddress + ", sum=" + sum + ", list="
				+ list + "]";
	}
	
	
	
}
