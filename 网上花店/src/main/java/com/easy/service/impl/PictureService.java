package com.easy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.bean.Order;
import com.easy.bean.PictrueVO;
import com.easy.bean.Picture;
import com.easy.dao.IOrderDao;
import com.easy.dao.IPicture;
import com.easy.service.IOrderService;
import com.easy.service.IPictureService;
import com.easy.util.Page;

@Service
public class PictureService implements IPictureService{

	@Autowired
	IPicture dao;

	public int count(Picture order) {
		int count =dao.count(order);
		return count;
	}

	public List<Picture> pictureList(Picture pic, Page page) {
		List<Picture> list=dao.pictrueList(pic, page);
		return list;
	}

	public int addPictrue(PictrueVO pic) {
		// TODO Auto-generated method stub
		return dao.addPictrue(pic);
	}

}
