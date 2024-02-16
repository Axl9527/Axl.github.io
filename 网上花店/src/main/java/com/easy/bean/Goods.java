package com.easy.bean;

import java.math.BigDecimal;

public class Goods {
private long id;
private String code;
private String name;
private String jointime;
private long pid;
private String info;
private BigDecimal price;
private BigDecimal oldprice;
private int stock;
private int state;
private int del;
private String edittime;
private long adminid;
private long editid;
private Admin initAdmin;
private Admin editAdmin;
private Category category;
private String img;
private String baozhuang;
private String cailiao;



public String getBaozhuang() {
	return baozhuang;
}

public void setBaozhuang(String baozhuang) {
	this.baozhuang = baozhuang;
}

public String getCailiao() {
	return cailiao;
}

public void setCailiao(String cailiao) {
	this.cailiao = cailiao;
}

public String getImg() {
	return img;
}

public void setImg(String img) {
	this.img = img;
}

public Category getCategory() {
	return category;
}

public void setCategory(Category category) {
	this.category = category;
}

public Admin getInitAdmin() {
	return initAdmin;
}

public void setInitAdmin(Admin initAdmin) {
	this.initAdmin = initAdmin;
}

public Admin getEditAdmin() {
	return editAdmin;
}

public void setEditAdmin(Admin editAdmin) {
	this.editAdmin = editAdmin;
}

public long getEditid() {
	return editid;
}

public void setEditid(long editid) {
	this.editid = editid;
}


@Override
public String toString() {
	return "Goods [id=" + id + ", code=" + code + ", name=" + name + ", jointime=" + jointime + ", pid=" + pid
			+ ", info=" + info + ", price=" + price + ", oldprice=" + oldprice + ", stock=" + stock + ", state=" + state
			+ ", del=" + del + ", edittime=" + edittime + ", adminid=" + adminid + ", editid=" + editid + ", initAdmin="
			+ initAdmin + ", editAdmin=" + editAdmin + ", category=" + category + ", img=" + img + "]";
}

public String getEdittime() {
	return edittime;
}

public void setEdittime(String edittime) {
	this.edittime = edittime;
}

public long getAdminid() {
	return adminid;
}

public void setAdminid(long adminid) {
	this.adminid = adminid;
}

public String getInfo() {
	return info;
}

public void setInfo(String info) {
	this.info = info;
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
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getJointime() {
	return jointime;
}
public void setJointime(String jointime) {
	this.jointime = jointime;
}
public long getPid() {
	return pid;
}
public void setPid(long pid) {
	this.pid = pid;
}
public BigDecimal getPrice() {
	return price;
}
public void setPrice(BigDecimal price) {
	this.price = price;
}
public BigDecimal getOldprice() {
	return oldprice;
}
public void setOldprice(BigDecimal oldprice) {
	this.oldprice = oldprice;
}
public int getStock() {
	return stock;
}
public void setStock(int stock) {
	this.stock = stock;
}
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}
public int getDel() {
	return del;
}
public void setDel(int del) {
	this.del = del;
}


}
