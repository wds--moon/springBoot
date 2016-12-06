package com.example.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 注解式的Servlet使用
 * Created by liaoqianyang on 2016/10/28.
 */
@WebServlet(urlPatterns = "/myServlet/*", description = "Servlet说明")
public class MyServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("=========MyServlet do something==========");
    }
}
