package com.easy.util;

import java.io.Serializable;
import java.util.Objects;

public class CommonResult<T> implements Serializable {

    /**
     * 结果码
     */
    private Long code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    protected CommonResult() {
    }

    private CommonResult(long code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    /**
     * 返回成功结果
     */
    public static <T> CommonResult<T> success() {
        return new CommonResult<T>(ResultCodeEnum.SUCCESS.getCode(),
                ResultCodeEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCodeEnum.SUCCESS.getCode(),
                ResultCodeEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(ResultCodeEnum.SUCCESS.getCode(), message, data);
    }

    public static <T> CommonResult<T> failed404() {
        return new CommonResult<T>(ResultCodeEnum.ERROR_404.getCode(), ResultCodeEnum.ERROR_404.getMessage(), null);
    }
    
    public static <T> CommonResult<T> failed500() {
        return new CommonResult<T>(ResultCodeEnum.ERROR_500.getCode(), ResultCodeEnum.ERROR_500.getMessage(), null);
    }
    

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

   

    public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

	@Override
	public String toString() {
		return "CommonResult [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

	public int hashCode() {
		return code.hashCode();
	}

   
}

