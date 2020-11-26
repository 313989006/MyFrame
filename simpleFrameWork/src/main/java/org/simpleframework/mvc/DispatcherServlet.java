package org.simpleframework.mvc;

import org.simpleframework.aop.AspectWeaver2;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.inject.DependencyInjector;
import org.simpleframework.mvc.processor.RequestProcessor;
import org.simpleframework.mvc.processor.impl.ControllerRequestProcessor;
import org.simpleframework.mvc.processor.impl.JspRequestProcessor;
import org.simpleframework.mvc.processor.impl.PreRequestProcessor;
import org.simpleframework.mvc.processor.impl.StaticResourceRequestProcessor;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/*")
public class DispatcherServlet extends HttpServlet {

    List<RequestProcessor> PROCESOOR = new ArrayList<>();

    @Override
    public void init(){

        // 1、初始化容器
        BeanContainer beanContainer = BeanContainer.instance();
        try {
            beanContainer.loadBeans("com.mxk");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new AspectWeaver2().doAop();
        new DependencyInjector().doIOC();

        // 2、初始化请求处理器责任链
        PROCESOOR.add(new PreRequestProcessor());
        PROCESOOR.add(new StaticResourceRequestProcessor(getServletContext()));
        PROCESOOR.add(new JspRequestProcessor(getServletContext()));
        PROCESOOR.add(new ControllerRequestProcessor());
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp){
        // 1、创建责任链对象实例
        RequestProcessorChain requestProcessorChain = new RequestProcessorChain(PROCESOOR.iterator(),req,resp);
        // 2、通过责任链模式来依次调用请求处理器对请求进行处理
        requestProcessorChain.doRequestProcessorChain();
        // 3、对处理结果进行渲染
        requestProcessorChain.doRender();
    }
}
