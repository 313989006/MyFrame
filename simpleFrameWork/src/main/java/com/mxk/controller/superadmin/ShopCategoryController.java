package com.mxk.controller.superadmin;

import com.mxk.entity.ShopCategory;
import com.mxk.entity.dto.Result;
import com.mxk.service.ShopCategoryService;
import lombok.Getter;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName ShopCategoryController
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/1 12:22
 **/
@Controller
@Getter
public class ShopCategoryController {

    @Autowired(value = "ShopCategoryServiceImpl")
    private ShopCategoryService shopCategoryService;

    public Result<Boolean> insert(HttpServletRequest req, HttpServletResponse resp){
        // TODO :参数校验以及请求参数转化
        return shopCategoryService.insert(new ShopCategory());
    }

    public Result<Boolean> update(HttpServletRequest req, HttpServletResponse resp){
        // TODO :参数校验以及请求参数转化
        return shopCategoryService.update(new ShopCategory());
    }

    public Result<Boolean> delete(HttpServletRequest req, HttpServletResponse resp){
        // TODO :参数校验以及请求参数转化
        return shopCategoryService.delete(1);
    }

    public Result<ShopCategory> queryById(HttpServletRequest req, HttpServletResponse resp){
        // TODO :参数校验以及请求参数转化
        return shopCategoryService.queryById(1);
    }

    public Result<List<ShopCategory>> queryByPage(HttpServletRequest req, HttpServletResponse resp){
        // TODO :参数校验以及请求参数转化
        return shopCategoryService.queryByPage(new ShopCategory(),1,100);
    }
}
