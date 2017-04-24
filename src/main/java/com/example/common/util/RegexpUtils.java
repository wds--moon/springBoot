package com.example.common.util;/**
 * Created by Administrator on 2017/4/13 0013.
 */

import org.apache.oro.text.regex.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wendongshan
 * @create 2017-04-13 下午 2:35
 * 一般正则表达式匹配工具
 **/
public final class RegexpUtils {

    private static Logger log = LoggerFactory.getLogger(RegexpUtils.class);

    public static final String ICON_REGEXP = "\\w+.(gif|dmp|png|jpg)$";
    //检查字符串中是否存在中文
    public static final String CHINESE = "[\\u4e00-\\u9fa5]";
    public static final String EMAIL = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
    public static final String URL = "[a-zA-z]+://[^\\s]*";
    /**
     *
     * @param source
     *            批配的源字符串
     * @param regexp
     *            批配的正规表达式
     * @return 如果源字符串符合要求返回真,否则返回假
     */
    public static boolean isHardRegexpValidate(String source, String regexp) {

        try {
            // 用于定义正规表达式对象模板类型
            PatternCompiler compiler = new Perl5Compiler();
            // 正规表达式比较批配对象
            PatternMatcher matcher = new Perl5Matcher();
            // 实例大小大小写敏感的正规表达式模板
            Pattern hardPattern = compiler.compile(regexp);
            // 返回批配结果
            return matcher.contains(source, hardPattern);

        } catch (MalformedPatternException e) {
            log.error("表达式匹配失败==>{}",e);
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(RegexpUtils.isHardRegexpValidate("https://www.baidu.com",URL));
    }
}
