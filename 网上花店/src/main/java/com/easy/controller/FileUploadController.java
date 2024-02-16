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
	
	//�������ļ���easy.upload-file-path��¼������ֵ,ע�뵽fileUpladPath
	@Value("${easy.upload-file-path}")
	String fileUploadPath;
	@Value("${easy.upload-file-url}")//ͨ��·�����ʵ��ļ�
	String uploadFileUrl;
	@RequestMapping("file")
	//����һ���������,�����ϴ����ļ� 
	//�ϴ��ļ��ķ�װ��
	public CommonResult fileUpload(MultipartFile file) {
		//1.ͨ���ļ���Դ�ļ���ȡ���ļ��ĺ�׺
		String oldName=file.getOriginalFilename();
		  //��ȡ���һ������ֵ�λ�� �±�
		int lastIndex=oldName.lastIndexOf(".");
		  //��ȡ��׺��
		String lastName=oldName.substring(lastIndex);
		//2.ͨ����׺����һ���ַ������һ���·��ļ���
		UUID uuid=UUID.randomUUID();
		String newName=uuid+lastName;
		//3.�����ļ������浽ָ��λ��
		  //����һ����������ļ���
		File destFile=new File(fileUploadPath+newName);
		
		try {
			file.transferTo(destFile);
		} catch (Exception e) {
			// �ļ�����ʧ��,��Ӧ������Ϣ
			e.printStackTrace();
			return CommonResult.failed500();
		} 
		//4.����ͼƬ�ķ���·��
		String fileUrl=uploadFileUrl+"/"+newName;
		System.out.println(oldName+"��·����Ƭ");
		return CommonResult.success(fileUrl);
		
	}
	
















}
