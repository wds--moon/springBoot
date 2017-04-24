package com.example.common.constant;

/**
 * 枚举类型,系统错误级别定义规则
 */
public enum CodeEnum {
    SUCCESS(Integer.valueOf(1), "success"),
    FAILURE(Integer.valueOf(0), "failure"),
    ERROR_SYSTEM(Integer.valueOf(-1), "System error"),
    ERROR_PARAMETER(Integer.valueOf(-2), "Parameter error"),
    ERROR_MD5(Integer.valueOf(-3), "MD5 Check failure"),
    ERROR_DATABASE(Integer.valueOf(-4), "DataBase anomaly");


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

    public static void main(String[] args) {
        System.out.println(CodeEnum.SUCCESS.getCode()+""+ CodeEnum.SUCCESS.getMessage());
        System.out.println(CodeEnum.FAILURE.getCode()+""+ CodeEnum.SUCCESS.getMessage());
        System.out.println(CodeEnum.valueOf(1)+"");
    }
}
