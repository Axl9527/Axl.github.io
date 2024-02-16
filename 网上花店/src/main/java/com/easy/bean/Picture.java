package com.easy.bean;

public class Picture {
	  private long id;
	    private long goodsid;
	    private String url;

	    @Override
	    public String toString() {
	        return "Picture{" +
	                "id=" + id +
	                ", goodsid=" + goodsid +
	                ", url='" + url + '\'' +
	                '}';
	    }

	    public long getId() {
	        return id;
	    }

	    public void setId(long id) {
	        this.id = id;
	    }

	    public long getGoodsid() {
	        return goodsid;
	    }

	    public void setGoodsid(long goodsid) {
	        this.goodsid = goodsid;
	    }

	    public String getUrl() {
	        return url;
	    }

	    public void setUrl(String url) {
	        this.url = url;
	    }
}
