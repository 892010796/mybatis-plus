package com.example.mybatisplustest.common;

import java.io.Serializable;

/**
 * @author ：zhangjian
 * @date ：Created in 2020/11/25 16:54
 */
public class ReturnData<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 消息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public ReturnData() {
    }

    public ReturnData(Integer status) {
        this.status = status;
    }

    public ReturnData(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public ReturnData(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ReturnData(CodeEnum codeEnum) {
        this.status = codeEnum.getCode();
        this.message = codeEnum.getMessage();
    }

    public ReturnData(CodeEnum codeEnum, T data) {
        this(codeEnum);
        this.data = data;
    }

    /**
     * 请求成功（不返回数据）
     * @param <T>
     * @return
     */
    public static <T> ReturnData <T> success(){
        return new ReturnData <T>(CodeEnum.SUCCESS);
    }

    /**
     * 请求成功（返回数据）
     * @param <T>
     * @return
     */
    public static <T> ReturnData <T> success(T data){
        return new ReturnData <T>(CodeEnum.SUCCESS, data);
    }

    /**
     * 请求失败
     * @param <T>
     * @return
     */
    public static <T> ReturnData <T> fail(){
        return new ReturnData <T>(CodeEnum.FAIL);
    }

    /**
     * 请求失败
     * @param <T>
     * @return
     */
    public static <T> ReturnData <T> fail(T data){
        return new ReturnData <T>(CodeEnum.FAIL, data);
    }


    //  .......可根据自己的需要往下延伸


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
