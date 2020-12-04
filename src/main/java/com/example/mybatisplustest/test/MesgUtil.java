package com.example.mybatisplustest.test;

public enum MesgUtil {
    SYS_Success("100","成功"),
    SYS_Fail("200","失败");



    private final String code;
    private final String mesg;

    MesgUtil(String code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }

    public String getCode() {
        return code;
    }

    public String getMesg() {
        return mesg;
    }
}
