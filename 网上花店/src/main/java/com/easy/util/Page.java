package com.easy.util;

public class Page {
	private Integer size;
	private  Integer page;
	private Integer start;
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getStart() {
		return (page-1)*size;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	
}
