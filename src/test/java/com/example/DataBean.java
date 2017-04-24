package com.example;/**
 * Created by Administrator on 2017/3/21 0021.
 */

/**
 * @author wendongshan
 * @create 2017-03-21 下午 2:24
 **/
public enum DataBean {
    URL_BAIDU(Integer.valueOf(0), "wwww.baidu.com"),
    URL_DANGLE(Integer.valueOf(1), "wwww.dl.com");
    private Integer code;
    private String text;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    DataBean(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public static boolean getValid(String text) {
        boolean flag = false;
        try {
            DataBean[] dataBeans = DataBean.values();
            for (DataBean data : dataBeans
                    ) {
                if (data.getText().equals(text)) {
                    flag = true;
                }
            }
        } catch (IllegalArgumentException e) {

        }
        return flag;
    }

    public static void main(String[] args) {
        System.out.println(DataBean.getValid("wwww.dl.com"));
    }
}
