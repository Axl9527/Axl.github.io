package com.easy.bean;

public class Category {
private long id;
private String name;
private String code;
private String time;
private int del;
private long adminid;
private String edittime;
private Admin admin;
private Admin adminedit;
private long editid;
private long pid;
private Category cate;


@Override
public String toString() {
	return "Category [id=" + id + ", name=" + name + ", code=" + code + ", time=" + time + ", del=" + del + ", adminid="
			+ adminid + ", edittime=" + edittime + ", admin=" + admin + ", adminedit=" + adminedit + ", editid="
			+ editid + ", pid=" + pid + ", cate=" + cate + "]";
}
public Category getCate() {
	return cate;
}
public void setCate(Category cate) {
	this.cate = cate;
}
public long getPid() {
	return pid;
}
public void setPid(long pid) {
	this.pid = pid;
}

public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public int getDel() {
	return del;
}
public void setDel(int del) {
	this.del = del;
}
public long getAdminid() {
	return adminid;
}
public void setAdminid(long adminid) {
	this.adminid = adminid;
}
public String getEdittime() {
	return edittime;
}
public void setEdittime(String edittime) {
	this.edittime = edittime;
}
public Admin getAdmin() {
	return admin;
}
public void setAdmin(Admin admin) {
	this.admin = admin;
}
public Admin getAdminedit() {
	return adminedit;
}
public void setAdminedit(Admin adminedit) {
	this.adminedit = adminedit;
}
public long getEditid() {
	return editid;
}
public void setEditid(long editid) {
	this.editid = editid;
}

}
