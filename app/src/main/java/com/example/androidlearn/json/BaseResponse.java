package com.example.androidlearn.json;

/**
 * @Author: luweiming
 * @Description: TODO
 * @Date: Created in 15:42 2022/3/8
 */
public class BaseResponse<T> {
    int code;
    String msg;
    T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
}
