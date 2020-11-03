package com.mxk.controller;

import com.mxk.entity.dto.MainPageInfoDto;
import com.mxk.entity.dto.Result;
import com.mxk.service.HeadLineShppCategoryService;
import org.simpleframework.core.annotation.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName MainPageController
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/1 13:31
 **/
@Controller
public class MainPageController {

    private HeadLineShppCategoryService headLineShppCategoryService;

    public Result<MainPageInfoDto> getMainPageInfo(HttpServletRequest req, HttpServletResponse resp){
        return headLineShppCategoryService.getMainPageInfo();
    }
}
