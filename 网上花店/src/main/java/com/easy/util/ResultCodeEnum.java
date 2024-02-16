package com.easy.util;

public enum ResultCodeEnum {

	    SUCCESS(true, 200, "�ɹ�"),
	    
	    ERROR_404(false, 404, "δ�ҵ���Դ"),
	    ERROR_500(false, 500, "�����������쳣"),
	    
	    
	    
	    UNKNOWN_REASON(false, 201, "δ֪����"),
	    UPDATE_ERROR(false, 202, "����ʧ��"),

	    BAD_SQL_GRAMMAR(false, 211, "sql �﷨����"),
	    JSON_PARSE_ERROR(false, 212, "json �����쳣"),
	    PARAM_ERROR(false, 213, "��������ȷ"),

	    FILE_UPLOAD_ERROR(false, 214, "�ļ��ϴ�����"),
	    FILE_DELETE_ERROR(false, 215, "�ļ��h������"),
	    EXCEL_DATA_IMPORT_ERROR(false, 216, "Excel ���ݵ������"),

	    VIDEO_UPLOAD_ALIYUN_ERROR(false, 221, "��Ƶ�ϴ���������ʧ��"),
	    VIDEO_UPLOAD_TOMCAT_ERROR(false, 222, "��Ƶ�ϴ���ҵ�������ʧ��"),
	    VIDEO_DELETE_ALIYUN_ERROR(false, 223, "��������Ƶ�ļ�ɾ��ʧ��"),
	    FETCH_VIDEO_UPLOADAUTH_ERROR(false, 224, "��ȡ�ϴ���ַ��ƾ֤ʧ��"),
	    REFRESH_VIDEO_UPLOADAUTH_ERROR(false, 225, "ˢ���ϴ���ַ��ƾ֤ʧ��"),
	    FETCH_PLAYAUTH_ERROR(false, 226, "��ȡ����ƾ֤ʧ��"),

	    URL_ENCODE_ERROR(false, 231, "URL����ʧ��"),
	    ILLEGAL_CALLBACK_REQUEST_ERROR(false, 232, "�Ƿ��ص�����"),
	    FETCH_ACCESSTOKEN_FAILD(false, 233, "��ȡ accessToken ʧ��"),
	    FETCH_USERINFO_ERROR(false, 234, "��ȡ�û���Ϣʧ��"),
	    LOGIN_ERROR(false, 235, "��¼ʧ��"),

	    COMMENT_EMPTY(false, 246, "�������ݱ�����д"),

	    PAY_RUN(false, 250, "֧����"),
	    PAY_UNIFIEDORDER_ERROR(false, 251, "ͳһ�µ�����"),
	    PAY_ORDERQUERY_ERROR(false, 252, "��ѯ֧���������"),

	    ORDER_EXIST_ERROR(false, 253, "�γ��ѹ���"),

	    GATEWAY_ERROR(false, 260, "�����ܷ���"),

	    CODE_ERROR(false, 280, "��֤�����"),

	    LOGIN_PHONE_ERROR(false, 289, "�ֻ����벻��ȷ"),
	    LOGIN_MOBILE_ERROR(false, 281, "�˺Ų���ȷ"),
	    LOGIN_PASSWORD_ERROR(false, 288, "���벻��ȷ"),
	    LOGIN_DISABLED_ERROR(false, 282, "���û��ѱ�����"),
	    REGISTER_MOBLE_ERROR(false, 283, "�ֻ����ѱ�ע��"),
	    LOGIN_AUTH(false, 284, "��Ҫ��¼"),
	    LOGIN_ACL(false, 285, "û��Ȩ��"),
	    SMS_SEND_ERROR(false, 286, "���ŷ���ʧ��"),
	    SMS_SEND_ERROR_BUSINESS_LIMIT_CONTROL(false, 287, "���ŷ��͹���Ƶ��"),

	    DIVIDE_ZERO(false, 291, "�������"),

	    DATA_NULL(false, 301, "���ݲ����ڣ�"),
	    DATA_EXITS(false, 302, "�����Ѵ��ڣ�"),
	    DATA_NODE_EXITS(false, 303, "���½��´�����Ƶ�γ̣�����ɾ����Ƶ�γ̣�");
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
