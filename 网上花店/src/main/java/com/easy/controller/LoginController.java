package com.easy.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.easy.bean.User;
import com.easy.dao.IUserDao;
import com.easy.service.IUserService;
import com.easy.util.CommonResult;
import com.easy.util.JWTUtil;
import com.easy.util.MD5Util;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
@RestController
@CrossOrigin(origins="*")
public class LoginController {
	
	
//	@Autowired
//	RedisTemplate redisTemplate;
	@Autowired
	IUserService userS;
	
	 @Autowired
	    private DefaultKaptcha kaptcha;
	
	@PostMapping("login")
	public CommonResult login(@RequestBody User user) {
		//System.out.println(redisTemplate);
		System.out.println("password:"+user.getUname());
		System.out.println("password:"+user.getUpassword());
		List<User> list=userS.userListL();
		System.out.println(list);
		
		
		
		// 认证用户名和密码是否有效
		String name = "";
		String pass = "";
		int a=0;
		CommonResult result;
		if (user != null && user.getUname() != null && user.getUpassword() != null) {
			System.out.println("一层");
			for(User users:list) {
				 name=users.getUname();
				 pass=users.getUpassword();
				 System.out.println(pass+"数据库中密码");
				 String decode=MD5Util.deCode(user.getUpassword(), pass);
				 System.out.println(decode+"验证密码后");
				 if(name.equals(user.getUname())&& decode.equals(pass)) {
					 a=1;
				 }
			}
			
			if (a==1) {
				System.out.println("二层");
				// 认证成功
				// 成功:签发token,回应给前端
				Map params = new HashMap();

				params.put("name", name);
				String token = JWTUtil.createToken(params);
				//将token存放至redis中
				//用户每次请求都会携带这个token,服务器都会截取这个token,检查这恶token是否有效
				//1.token是否有效,2.token是否过去 3.服务器是否记录这个token,存入redis
				//String redisDataJson=JSON.toJSONString(user);
			//	redisTemplate.opsForValue().set(token,redisDataJson);
				//reDao.set(token, redisDataJson);
			result=	CommonResult.success(token);
				return result;
			
			}else {
				//验证失败
				result=CommonResult.failed500();
				return result;
			}
		}
		//格式不对
		result=CommonResult.failed500();
		return result;

		// 成功:签发token,回应给前端

		// 失败:发送错误信息给前端
	}
	 @RequestMapping("/kaptcha")
	    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
	        HttpSession session = request.getSession();
	        response.setDateHeader("Expires", 0);
	        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
	        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
	        response.setHeader("Pragma", "no-cache");
	        response.setContentType("image/jpeg");
	        String capText = kaptcha.createText();
	        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
	        BufferedImage bi = kaptcha.createImage(capText);
	        ServletOutputStream out = response.getOutputStream();
	            ImageIO.write(bi, "jpg", out);
	        
	    }
}
