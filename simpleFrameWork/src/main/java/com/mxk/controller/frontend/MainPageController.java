package com.mxk.controller.frontend;

import com.mxk.entity.dto.MainPageInfoDto;
import com.mxk.entity.dto.Result;
import com.mxk.service.HeadLineShppCategoryService;
import lombok.Getter;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;
import org.simpleframework.mvc.annotation.RequestMapping;
import org.simpleframework.mvc.type.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName MainPageController
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/1 13:31
 **/
@Controller
//@Getter
@RequestMapping(value = "/mainPage")
public class MainPageController {

    @Autowired(value = "HeadLineShppCategoryServiceImpl")
    private HeadLineShppCategoryService headLineShppCategoryService;

    public Result<MainPageInfoDto> getMainPageInfo(HttpServletRequest req, HttpServletResponse resp){
        return headLineShppCategoryService.getMainPageInfo();
    }

    @RequestMapping(value = "/throwException",method = RequestMethod.GET)
    public void throwException(){
        throw new RuntimeException("抛出异常测试");
    }
}
