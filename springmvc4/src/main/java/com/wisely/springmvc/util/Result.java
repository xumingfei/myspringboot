package com.wisely.springmvc.util;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/10 15:05
 */
public class Result<T> {
    private Object data;
    private String msg;
    private String code;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
