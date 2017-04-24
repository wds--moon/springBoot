package com.example.common.util;/**
 * Created by Administrator on 2017/3/23 0023.
 */

import org.apache.commons.io.IOUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;

/**
 * @author wendongshan
 * @create 2017-03-23 上午 11:08
 **/
public class JavaScriptInterpreter {

    public static void execScript() {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
        try {
            scriptEngine.eval("var a=3; var b=4;print (a+b);");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public static void execScriptFile() {
        try {
            File file = new File("");
            InputStream inputStream=new FileInputStream(file);
            byte[] str=new byte[1024];
            //IOUtils.read(inputStream,str);一次性读取文件
            IOUtils.readLines(inputStream,"UTF-8");
            ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
            ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
            scriptEngine.eval("var a=3; var b=4;print (a+b);");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JavaScriptInterpreter.execScript();
    }

}
