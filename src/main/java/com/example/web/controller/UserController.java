package com.example.web.controller;

import com.example.common.config.RetryHttpClientProxy;
import com.example.common.config.SolrClientConfig;
import com.example.common.util.RedisProxyUtil;
import com.example.common.util.Sender;
import com.example.entity.User;
import com.example.service.IUserService;
import com.example.vo.common.ResponseVO;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liaoqianyang on 2016/10/25.
 */
@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    RetryHttpClientProxy retryService;
    @Autowired
    private IUserService userService;
    @Autowired
    RedisProxyUtil redisProxyUtil;
    @Autowired
    SolrClientConfig solrClientConfig;

    @Autowired
    Sender sender;

    @ApiOperation(value = "根据用户id查询用户信息", notes = "获取用户信息存储到session")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") Integer id) {
        LOGGER.info("==========getUser id={}=========", id);
        return userService.getUserById(id);
    }

    @ApiOperation(value = "user列表", notes = "user列表信息")
    //   @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseVO findAllUsers() {
        // 分页插件第一个参数表示第几页,第二个参数表示每一页多少条记录
        int pageNum = 3;
        int pageSize = 5;
        /**
         * 简洁版本的分页参数
         */
        Page<User> page = PageHelper.startPage(pageNum, pageSize);
        return ResponseVO.success(userService.findAll(), page.getPageNum(), page.getPageSize(), page.getTotal());
        /**
         * 配合前端使用的详细分页信息
         */
//        PageInfo pageInfo=new PageInfo(userService.findAll(),5);
//        return ResponseVO.success(pageInfo.getList(),pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal());

    }

    @RequestMapping(value = "/addAndUpdate")
    @ResponseBody
    public void addAndUpdate() {
        userService.addAndUpdateUser();
    }


    @ApiOperation(value = "reTry", notes = "重试功能")
    @RequestMapping(value = "/reTry")
    public void reTry() {
        try {
            System.out.println("++++++任务开始+++++++");
            retryService.getCall("http://192.168.2.102:8080");
            System.out.println("++++++任务结束++++++");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("redis")
    public void redis(HttpServletResponse response) {
        String key = "lfy";
        Object obj = redisProxyUtil.get(key);
        try {
            System.out.println(obj);
            response.getWriter().print(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "setRedis/{key}/{value}", method = RequestMethod.GET)
    public void setRedis(@PathVariable("key") String key, @PathVariable("value") String value, HttpServletResponse response) {
        boolean obj = redisProxyUtil.set(key, value);
        try {
            response.getWriter().print(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "delRedis/{key}", method = RequestMethod.GET)
    public void delRedis(@PathVariable("key") String key, HttpServletResponse response) {
        redisProxyUtil.remove(key);
    }


    @RequestMapping("/testRabbitmqRetry")
    public void testRabbitmqRetry(HttpServletResponse response) {

        System.out.println("+++++++++++++++++++");
        sender.send();
    }


    @RequestMapping("/query")
    public void query(HttpServletResponse res) {
        try {
            res.getWriter().print(solrClientConfig.search());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(params = "act=downLoadcsv")
    public void downLoadcsv(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n");
        sb.append("\r\n");
        HttpHeaders headers = new HttpHeaders();
        try {
            OutputStream ps = response.getOutputStream();
            String fileName = "H5BillInfo";//为了解决中文名称乱码问题
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            responseSetProperties(fileName, response);
            ps.write(sb.toString().getBytes());
            ps.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void responseSetProperties(String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        // 设置文件后缀
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String fn = fileName + sdf.format(new Date()).toString() + ".csv";
        // 读取字符编码
        String utf = "UTF-8";
        // 设置响应
        response.setContentType("application/ms-txt.numberformat:@");
        response.setCharacterEncoding(utf);
        response.setHeader("Pragma", "public");
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fn, utf));
    }
}
