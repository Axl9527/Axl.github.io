package com.easy.bean;

public class Comment {
	private long id;
    private long goodsid;
    private long userid;
    private String info;
    private String time;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", goodsid=" + goodsid +
                ", userid=" + userid +
                ", info='" + info + '\'' +
                ", time='" + time + '\'' +
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

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
