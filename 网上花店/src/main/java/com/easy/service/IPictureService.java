package com.easy.service;

import java.util.List;

import com.easy.bean.PictrueVO;
import com.easy.bean.Picture;
import com.easy.util.Page;

public interface IPictureService {
	List<Picture> pictureList (Picture user,Page page);
	int count(Picture user);
	 int addPictrue(PictrueVO pic);
}
