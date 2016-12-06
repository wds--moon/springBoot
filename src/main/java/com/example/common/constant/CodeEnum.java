package com.example.common.constant;

/**
 * Created by liaoqianyang on 2016/10/27.
 */
public enum CodeEnum {
    SUCCESS(0, "success"),
    FAILURE(1, "failure");

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    CodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CodeEnum valueOf(int code) {
        CodeEnum[] var1 = values();
        int var2 = var1.length;
        for(int var3 = 0; var3 < var2; ++var3) {
            CodeEnum codeEnum = var1[var3];
            if(codeEnum.code == code) {
                return codeEnum;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + code + "]");
    }
}
