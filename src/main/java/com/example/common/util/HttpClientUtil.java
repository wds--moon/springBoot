package com.example.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wendongshan on 2016/11/2.
 * 访问远程连接工具类
 */
public class HttpClientUtil {

    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());
    private static HttpClientBuilder httpClientBuilder = null;
    private static CloseableHttpClient closeableHttpClient = null;
    private static RequestConfig requestPostConfig = null;
    private static RequestConfig requestGetConfig = null;

    /**
     * 设置基本配置
     */
    static {
        //创建HttpClientBuilder
        httpClientBuilder = HttpClientBuilder.create();
        closeableHttpClient = httpClientBuilder.build();
        //post专用
        requestPostConfig = RequestConfig.custom()
                .setSocketTimeout(60000).setConnectTimeout(60000).build();// 设置请求和传输超时时间
        requestGetConfig = RequestConfig.custom()
                .setSocketTimeout(5000).setConnectTimeout(5000).build();// 设置请求和传输超时时间
    }

    /**
     * 设置大小
     */
    static int BUFFER_SIZE = 8192;

    static final String ENCODING = "UTF-8";

    /**
     * 默认编码格式
     *
     * @param url
     * @return
     */
    public static String getHttpUrl(String url) {
        return getHttpUrl(url, ENCODING);
    }

    /**
     * 默认编码格式
     *
     * @param url
     * @param data
     * @return
     */
    public static String postHttpUrl(String url, String data) {
        return postHttpUrl(url, data, ENCODING);
    }

    /**
     * 采用httpclient 抓取数据 post 4.1最新方法 手动指定编码
     *
     * @param url
     * @param encoding
     * @return
     */
    public static String getHttpUrl(String url, String encoding) {
        LOGGER.info("get.......开始....................");
        String content = "";
        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(requestGetConfig);
            //执行get请求
            HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
            //响应状态
            content = getRequstString(encoding, content, httpResponse);
        } catch (Exception e) {
            LOGGER.error(e.toString());
        } finally {
            try {                //关闭流并释放资源
                closeableHttpClient.close();
            } catch (IOException e) {
                LOGGER.error(e.toString());
            }
        }
        return content;
    }


    /**
     * 采用httpclient 抓取数据 post 4.1最新方法 手动指定编码
     *
     * @param url
     * @param encoding
     * @return
     */
    public static String postHttpUrl(String url, String data, String encoding) {
        LOGGER.info("post..........开始.................");
        String content = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestPostConfig);

            //设置参数
            StringEntity stringEntry = new StringEntity(data, encoding);
            //设置参数类型
            stringEntry.setContentType("application/json");
            httpPost.setEntity(stringEntry);
            //执行get请求
            HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
            //响应状态
            content = getRequstString(encoding, content, httpResponse);
        } catch (Exception ex) {
            LOGGER.error(ex.toString());
        }
        return content;
    }

    /**
     * 获取返回结果
     *
     * @param encoding
     * @param content
     * @param httpResponse
     * @return
     * @throws Exception
     */
    private static String getRequstString(String encoding, String content, HttpResponse httpResponse) throws Exception {
        if (httpResponse.getStatusLine().getStatusCode() == 200) {//成功响应吗为200
            //获取响应消息实体
            HttpEntity entity = httpResponse.getEntity();
            //判断响应实体是否为空
            if (entity != null) {
                InputStream instream = entity.getContent();
                try {
                    content = InputStreamTOString(instream, encoding);
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                } finally {
                    instream.close();
                }
            }
        }
        return content;
    }


    /**
     * 将InputStream转换成某种字符编码的String
     *
     * @param in
     * @param encoding
     * @return
     * @throws Exception
     */
    public static String InputStreamTOString(InputStream in, String encoding)
            throws Exception {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);

        data = null;
        return new String(outStream.toByteArray(), encoding);
    }

}
