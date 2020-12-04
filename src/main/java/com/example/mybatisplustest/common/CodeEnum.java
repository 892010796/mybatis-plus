package com.example.mybatisplustest.common;

/**
 * @author ：zhangjian
 * @date ：Created in 2020/11/25 17:00
 */
public enum  CodeEnum {
    /**
     * 成功
     */
    SUCCESS(200, "成功"),

    /**
     * 成功
     */
    FAIL(400, "失败"),
    ;

    private final Integer code;
    private final String message;

    CodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
