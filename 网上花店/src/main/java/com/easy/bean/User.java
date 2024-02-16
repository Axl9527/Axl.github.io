package com.easy.bean;

public class User {
private Integer uid;
private String uname;
private String upassword;
private String email;
private String uphone;
private String usex;
private int ustate;
private int del;
private Address address;
private String img;
private String captcha;
private String newPassWord;

public String getNewPassWord() {
	return newPassWord;
}
public void setNewPassWord(String newPassWord) {
	this.newPassWord = newPassWord;
}
public String getCaptcha() {
	return captcha;
}
public void setCaptcha(String captcha) {
	this.captcha = captcha;
}
public String getImg() {
	return img;
}
public void setImg(String img) {
	this.img = img;
}
public Address getAddress() {
	return address;
}
public void setAddress(Address address) {
	this.address = address;
}
@Override
public String toString() {
	return "User [uid=" + uid + ", uname=" + uname + ", upassword=" + upassword + ", email=" + email + ", uphone="
			+ uphone + ", usex=" + usex + ", ustate=" + ustate + ", del=" + del + ", address=" + address + ", img="
			+ img + ", captcha=" + captcha + ", newPassWord=" + newPassWord + "]";
}

public Integer getUid() {
	return uid;
}
public void setUid(Integer uid) {
	this.uid = uid;
}
public String getUname() {
	return uname;
}
public void setUname(String uname) {
	this.uname = uname;
}
public String getUpassword() {
	return upassword;
}
public void setUpassword(String upassword) {
	this.upassword = upassword;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getUphone() {
	return uphone;
}
public void setUphone(String uphone) {
	this.uphone = uphone;
}
public String getUsex() {
	return usex;
}
public void setUsex(String usex) {
	this.usex = usex;
}
public int getUstate() {
	return ustate;
}
public void setUstate(int ustate) {
	this.ustate = ustate;
}
public int getDel() {
	return del;
}
public void setDel(int del) {
	this.del = del;
}

}
