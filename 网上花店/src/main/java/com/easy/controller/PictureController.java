package com.easy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.easy.bean.PictrueVO;
import com.easy.bean.Picture;
import com.easy.dao.IPicture;
import com.easy.service.IPictureService;
import com.easy.util.CommonResult;
import com.easy.util.Page;

@RestController
@CrossOrigin(origins="*")
public class PictureController {
	@Autowired
	IPictureService ser;
	
	@GetMapping("picture")
	public CommonResult pictureList(Picture pic,Page page) {
		List<Picture> list=ser.pictureList(pic, page);
		int total=ser.count(pic);
		Map map=new HashMap();
		map.put("list",list);
		map.put("count",total);
		return CommonResult.success(map);
	}
	@PostMapping("picture")
	public CommonResult add(@RequestBody PictrueVO pic) {
		System.out.println("’’∆¨"+pic);
		return CommonResult.success(ser.addPictrue(pic));
	}
}
