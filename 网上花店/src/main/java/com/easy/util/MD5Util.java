package com.easy.util;

import org.springframework.util.DigestUtils;

public class MD5Util {

	/**
	 * 鍔犲瘑
	 * 
	 * @param src 鍘熷瓧绗︿覆
	 * @return 鍔犲瘑瀛楃涓�
	 */
	public static String enCode(String src) {
		// 鏀惧瓧绗︾殑鏁扮粍
		char[] arr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		// 闅忔満涓嬫爣
		int index_ran_count = (int) (Math.random() * arr.length);
		int index_ran_salt = (int) (Math.random() * arr.length);
		char ran_count = arr[index_ran_count];
		char ran_salt = arr[index_ran_salt];
		for (int i = 0; i < ran_count; i++) {
			src = src + ran_salt;
			src = DigestUtils.md5DigestAsHex(src.getBytes());
		}
		// 寰幆涔嬪悗src灏辨槸鍔犲瘑鍚庣殑缁撴灉
		String result = ran_count + src + ran_salt;
		return result;
	}

	/**
	 * 瑙ｅ瘑
	 * @param src  鏈姞瀵嗙殑瀛楃涓�
	 * @param code 鍔犲瘑绛栫暐瀛楃涓�
	 * @return 灏唖rc鐢╟ode涓殑鍔犲瘑绛栫暐鍔犲瘑鍚庣殑缁撴灉
	 */
	public static String deCode(String src, String code) {
		if (code==null||code.isEmpty()) {
			return null;
		}
		char ran_count = code.charAt(0);
		char ran_salt = code.charAt(code.length() - 1);
		for (int i = 0; i < ran_count; i++) {
			src = src + ran_salt;
			src = DigestUtils.md5DigestAsHex(src.getBytes());
		}
		// 寰幆涔嬪悗src灏辨槸鍔犲瘑鍚庣殑缁撴灉
		String result = ran_count + src + ran_salt;
		return result;
	}
	public static void main(String[] args) {
		String code=enCode("123456");
		
		String decode=deCode("123123","c4d1d600dd3fa17037435bc012947da42b");
		System.out.println(code);
		System.out.println(decode);
	}
}
