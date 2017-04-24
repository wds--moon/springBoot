package com.example.common.constant;

/**
 * 系统里面数据库常用状态变量赋值
 *
 * 以接口的方式实现,参数比较多的时候比枚举类型好用
 */
public interface ContextParamUtil {

    static interface STATUS {
        byte TRUE = 1;
        byte ERROR = 0;
    };
    static interface SEX {
        byte MAN = 1;
        byte WOMAN = -1;
        byte CENTER = 0;
    }
}
