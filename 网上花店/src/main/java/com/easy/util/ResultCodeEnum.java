package com.easy.util;

public enum ResultCodeEnum {

	    SUCCESS(true, 200, "成功"),
	    
	    ERROR_404(false, 404, "未找到资源"),
	    ERROR_500(false, 500, "服务器出现异常"),
	    
	    
	    
	    UNKNOWN_REASON(false, 201, "未知错误"),
	    UPDATE_ERROR(false, 202, "更新失败"),

	    BAD_SQL_GRAMMAR(false, 211, "sql 语法错误"),
	    JSON_PARSE_ERROR(false, 212, "json 解析异常"),
	    PARAM_ERROR(false, 213, "参数不正确"),

	    FILE_UPLOAD_ERROR(false, 214, "文件上传错误"),
	    FILE_DELETE_ERROR(false, 215, "文件刪除错误"),
	    EXCEL_DATA_IMPORT_ERROR(false, 216, "Excel 数据导入错误"),

	    VIDEO_UPLOAD_ALIYUN_ERROR(false, 221, "视频上传至阿里云失败"),
	    VIDEO_UPLOAD_TOMCAT_ERROR(false, 222, "视频上传至业务服务器失败"),
	    VIDEO_DELETE_ALIYUN_ERROR(false, 223, "阿里云视频文件删除失败"),
	    FETCH_VIDEO_UPLOADAUTH_ERROR(false, 224, "获取上传地址和凭证失败"),
	    REFRESH_VIDEO_UPLOADAUTH_ERROR(false, 225, "刷新上传地址和凭证失败"),
	    FETCH_PLAYAUTH_ERROR(false, 226, "获取播放凭证失败"),

	    URL_ENCODE_ERROR(false, 231, "URL编码失败"),
	    ILLEGAL_CALLBACK_REQUEST_ERROR(false, 232, "非法回调请求"),
	    FETCH_ACCESSTOKEN_FAILD(false, 233, "获取 accessToken 失败"),
	    FETCH_USERINFO_ERROR(false, 234, "获取用户信息失败"),
	    LOGIN_ERROR(false, 235, "登录失败"),

	    COMMENT_EMPTY(false, 246, "评论内容必须填写"),

	    PAY_RUN(false, 250, "支付中"),
	    PAY_UNIFIEDORDER_ERROR(false, 251, "统一下单错误"),
	    PAY_ORDERQUERY_ERROR(false, 252, "查询支付结果错误"),

	    ORDER_EXIST_ERROR(false, 253, "课程已购买"),

	    GATEWAY_ERROR(false, 260, "服务不能访问"),

	    CODE_ERROR(false, 280, "验证码错误"),

	    LOGIN_PHONE_ERROR(false, 289, "手机号码不正确"),
	    LOGIN_MOBILE_ERROR(false, 281, "账号不正确"),
	    LOGIN_PASSWORD_ERROR(false, 288, "密码不正确"),
	    LOGIN_DISABLED_ERROR(false, 282, "该用户已被禁用"),
	    REGISTER_MOBLE_ERROR(false, 283, "手机号已被注册"),
	    LOGIN_AUTH(false, 284, "需要登录"),
	    LOGIN_ACL(false, 285, "没有权限"),
	    SMS_SEND_ERROR(false, 286, "短信发送失败"),
	    SMS_SEND_ERROR_BUSINESS_LIMIT_CONTROL(false, 287, "短信发送过于频繁"),

	    DIVIDE_ZERO(false, 291, "除零错误"),

	    DATA_NULL(false, 301, "数据不存在！"),
	    DATA_EXITS(false, 302, "数据已存在！"),
	    DATA_NODE_EXITS(false, 303, "该章节下存在视频课程，请先删除视频课程！");
	    private final Boolean success;

	    private final Integer code;

	    private final String message;

	    ResultCodeEnum(Boolean success, Integer code, String message) {
	        this.success = success;
	        this.code = code;
	        this.message = message;
	    }

		public Boolean getSuccess() {
			return success;
		}

		public Integer getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}
	    
	

}
