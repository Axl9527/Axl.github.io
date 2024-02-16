package com.easy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
@Configuration
@EnableRedisRepositories
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer{

	//获取配置文件upload-file-path属性D:/JavaPicture
	@Value("${easy.upload-file-path}")
	String uploadFilePath;
	@Value("${easy.upload-file-url}")
	String uploadFileUrl;
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		//将上传保存的文件夹,注册为项目的静态资源文件夹 设置img url请求
		registry.addResourceHandler(uploadFileUrl+"/**").addResourceLocations("file:"+uploadFilePath);

	}
	
	
}
