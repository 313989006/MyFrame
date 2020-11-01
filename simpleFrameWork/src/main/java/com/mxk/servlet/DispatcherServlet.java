package com.mxk.servlet;

import com.mxk.controller.HeadLineController;
import com.mxk.controller.MainPageController;
import com.mxk.entity.HeadLine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName DispatcherServlet
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/1 13:29
 **/
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("request path is :" + req.getServletPath());
        System.out.println("request method is :" + req.getMethod());

        if (req.getServletPath().equals("/frontend/getMainPageInfo") && req.getMethod().equals("GET")){
            new MainPageController().getMainPageInfo(req,resp);
        } else if (req.getServletPath().equals("/superAdmin/addHeadLine") && req.getMethod().equals("POST")){
            new HeadLineController().insert(req,resp);
        }

    }
}
