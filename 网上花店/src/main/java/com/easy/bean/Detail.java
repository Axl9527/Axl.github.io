package com.easy.bean;

import java.math.BigDecimal;

public class Detail {
	private long orderid;
    private long goodsid;
    private int count;
    private BigDecimal price;
    private Integer userid;
    private Goods good;
    
    public Goods getGood() {
		return good;
	}

	public void setGood(Goods good) {
		this.good = good;
	}

	@Override
    public String toString() {
        return "Detail{" +
                "orderid=" + orderid +
                ", goodsid=" + goodsid +
                ", count=" + count +
                ", price=" + price +
                '}';
    }

    public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public long getOrderid() {
        return orderid;
    }

    public void setOrderid(long orderid) {
        this.orderid = orderid;
    }

    public long getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(long goodsid) {
        this.goodsid = goodsid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
