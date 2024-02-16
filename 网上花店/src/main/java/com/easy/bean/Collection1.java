package com.easy.bean;

public class Collection1 {
	private Integer uid;
	private Integer gid;
	private int del;
	private Goods good;
	
	public Goods getGood() {
		return good;
	}
	public void setGood(Goods good) {
		this.good = good;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
	@Override
	public String toString() {
		return "Collection [uid=" + uid + ", gid=" + gid + ", del=" + del + "]";
	}

	
	
}
