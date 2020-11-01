package com.mxk.servlet;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName HelloServlet
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/10/30 16:45
 **/
// 采用注解的方式获取logger对象、和LoggerFactory。get的方式获取Logger对象效果一样，这里先采用后一种方式
//@Slf4j
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    // 采用LoggerFactory。get的方式获取Logger对象
    private Logger log = LoggerFactory.getLogger(HelloServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("我的简易框架开始。。。");
        String name = "我的简易框架。。。";
        request.setAttribute("name",name);
        request.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(request,response);

    }

}
