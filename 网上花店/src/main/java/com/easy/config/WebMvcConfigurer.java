package com.easy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
@Configuration
@EnableRedisRepositories
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer{

	//��ȡ�����ļ�upload-file-path����D:/JavaPicture
	@Value("${easy.upload-file-path}")
	String uploadFilePath;
	@Value("${easy.upload-file-url}")
	String uploadFileUrl;
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		//���ϴ�������ļ���,ע��Ϊ��Ŀ�ľ�̬��Դ�ļ��� ����img url����
		registry.addResourceHandler(uploadFileUrl+"/**").addResourceLocations("file:"+uploadFilePath);

	}
	
	
}
