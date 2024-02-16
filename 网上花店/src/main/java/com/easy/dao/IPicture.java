package com.easy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.easy.bean.PictrueVO;
import com.easy.bean.Picture;
import com.easy.util.Page;
@Mapper

public interface IPicture {

	List<Picture> pictrueList(@Param("pic")Picture pic,@Param("page")Page page);
	 int  count (@Param("pic")Picture pic);
	 int addPictrue(PictrueVO pic);
}
