package com.easy.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.easy.util.CommonResult;

@RestController
@CrossOrigin(origins="*")
public class FileUploadController {
	
	//将配置文件中easy.upload-file-path记录的属性值,注入到fileUpladPath
	@Value("${easy.upload-file-path}")
	String fileUploadPath;
	@Value("${easy.upload-file-url}")//通过路径访问到文件
	String uploadFileUrl;
	@RequestMapping("file")
	//定义一个请求控制,接收上传的文件 
	//上传文件的封装类
	public CommonResult fileUpload(MultipartFile file) {
		//1.通过文件的源文件获取到文件的后缀
		String oldName=file.getOriginalFilename();
		  //获取最后一个点出现的位置 下标
		int lastIndex=oldName.lastIndexOf(".");
		  //截取后缀名
		String lastName=oldName.substring(lastIndex);
		//2.通过后缀名和一个字符串组成一个新发文件名
		UUID uuid=UUID.randomUUID();
		String newName=uuid+lastName;
		//3.将新文件名保存到指定位置
		  //声明一个对象接收文件流
		File destFile=new File(fileUploadPath+newName);
		
		try {
			file.transferTo(destFile);
		} catch (Exception e) {
			// 文件保存失败,回应错误信息
			e.printStackTrace();
			return CommonResult.failed500();
		} 
		//4.返回图片的访问路径
		String fileUrl=uploadFileUrl+"/"+newName;
		System.out.println(oldName+"老路径照片");
		return CommonResult.success(fileUrl);
		
	}
	
















}
