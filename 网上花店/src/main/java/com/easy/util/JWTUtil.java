package com.easy.util;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class JWTUtil {
	private static final String SIGN="easy"; //��Կ
    //1.ͨ��jwt����token�ַ�����
    public static String createToken(Map<String,Object> params){
    	//tokenͷ������
        Map<String,Object> head=new HashMap();
        head.put("alg","HS256");
        head.put("typ","JWT");
 
        //����䷢ʱ��
        Date iss=new Date();
        //����ʱ��
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE,30);
        Date expire = nowTime.getTime();
 
        String token = JWT.create()
                //ָ��ͷ��Ϣ
                .withHeader(head)
                //�غ��ֵĹ���ʱ��
                .withExpiresAt(expire)
                //�䷢ʱ��
                .withIssuedAt(iss)
                //�䷢��
                .withIssuer("easy")
                //�Զ�����غ�����
                .withClaim("userInfo",params)
                //ǩ��
                .sign(Algorithm.HMAC256(SIGN));
 
        return token;
    }
 
    //2. �ж�token�Ƿ�Ϸ�  �Ƿ��Ǳ���ǩ����
    public static boolean verifyToken(String token){
        JWTVerifier build = JWT.require(Algorithm.HMAC256(SIGN)).build();
        try {
            DecodedJWT verify = build.verify(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
 
    //3. ����token�е�����
    public static Map<String,Object> decodeJWT(String token){
        Map<String, Object> userInfo = JWT.decode(token).getClaim("userInfo").asMap();
        return userInfo;
    }
 
 
    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap();
        map.put("name","zhangsan");
        map.put("id",1);
        map.put("age",19);
        String token = createToken(map);
        System.out.println(token);
 
        Map<String, Object> map2
                = decodeJWT(token);
        System.out.println(map2);
 
    }
    
    //����--map
    private Map getMap(Object obj) {
    	Class clazz=obj.getClass();
    	Field[] farrs=clazz.getDeclaredFields();
    	Map result=new HashMap();
    	for(Field fe:farrs) {
    		try {
				result.put(fe.getName(), fe.get(obj));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		return result;
    }
}
