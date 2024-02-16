package com.easy.bean;

import java.math.BigDecimal;

public class ShoppingCar {
private long userid;
private long goodsid;
private String jointime;
private BigDecimal goodsprice;
private int num;
private Goods goodsimg;
private Goods goodsname;
private int check;



public int getCheck() {
	return check;
}
public void setCheck(int check) {
	this.check = check;
}
public Goods getGoodsimg() {
	return goodsimg;
}
public void setGoodsimg(Goods goodsimg) {
	this.goodsimg = goodsimg;
}
public Goods getGoodsname() {
	return goodsname;
}
public void setGoodsname(Goods goodsname) {
	this.goodsname = goodsname;
}
@Override
public String toString() {
	return "ShoppingCar [userid=" + userid + ", goodsid=" + goodsid + ", jointime=" + jointime + ", goodsprice="
			+ goodsprice + ", num=" + num + "]";
}
public long getUserid() {
	return userid;
}
public void setUserid(long userid) {
	this.userid = userid;
}
public long getGoodsid() {
	return goodsid;
}
public void setGoodsid(long goodsid) {
	this.goodsid = goodsid;
}
public String getJointime() {
	return jointime;
}
public void setJointime(String jointime) {
	this.jointime = jointime;
}
public BigDecimal getGoodsprice() {
	return goodsprice;
}
public void setGoodsprice(BigDecimal goodsprice) {
	this.goodsprice = goodsprice;
}
public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}

}
