package com.mxk.controller.superadmin;

import com.mxk.entity.HeadLine;
import com.mxk.entity.dto.Result;
import com.mxk.service.HeadLineService;
import lombok.Getter;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;
import org.simpleframework.mvc.annotation.RequestMapping;
import org.simpleframework.mvc.type.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName HeadLineController
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/1 12:22
 **/
@Controller
//@Getter
@RequestMapping(value = "/headline")
public class HeadLineController {

    @Autowired(value = "HeadLineServiceImpl")
    private HeadLineService headLineService;

    public Result<Boolean> insert(HttpServletRequest req, HttpServletResponse resp){
        // TODO :参数校验以及请求参数转化
        return headLineService.insert(new HeadLine());
    }

    public Result<Boolean> update(HttpServletRequest req, HttpServletResponse resp){
        // TODO :参数校验以及请求参数转化
        return headLineService.update(new HeadLine());
    }

    public Result<Boolean> delete(HttpServletRequest req, HttpServletResponse resp){
        // TODO :参数校验以及请求参数转化
        return headLineService.delete(1);
    }

    @RequestMapping(value = "/remove",method = RequestMethod.GET)
    public void remove(){
        System.out.println("删除完成");
    }

    public Result<HeadLine> queryById(HttpServletRequest req, HttpServletResponse resp){
        // TODO :参数校验以及请求参数转化
        return headLineService.queryById(1);
    }

    public Result<List<HeadLine>> queryByPage(HttpServletRequest req, HttpServletResponse resp){
        // TODO :参数校验以及请求参数转化
        return headLineService.queryByPage(new HeadLine(),1,100);
    }
}
