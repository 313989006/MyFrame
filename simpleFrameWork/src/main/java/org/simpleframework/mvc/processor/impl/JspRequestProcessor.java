package org.simpleframework.mvc.processor.impl;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.mvc.RequestProcessorChain;
import org.simpleframework.mvc.processor.RequestProcessor;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 * jsp 资源请求处理
 **/
@Slf4j
public class JspRequestProcessor implements RequestProcessor {

    // jsp 请求的 RequestDispatcher 的名称
    private static final String JSP_SERVLET = "jsp";
    // jsp 请求资源路径前缀
    private static final String JSP_RESOURCE_PREFIX = "templates";

    /**
    * jsp 的 RequestDispatcher 处理jsp 资源
    */
    private RequestDispatcher jspServlet;

    public JspRequestProcessor(ServletContext servletContext) {
        this.jspServlet = servletContext.getNamedDispatcher(JSP_SERVLET);
        if (this.jspServlet == null) {
            throw new RuntimeException("there is no jsp servlet");
        }
    }

    @Override
    public boolean process(RequestProcessorChain requestProcessorChain) throws Exception {
        if (isJspResource(requestProcessorChain.getRequestPath())){
            jspServlet.forward(requestProcessorChain.getRequest(),requestProcessorChain.getResponse());
            return false;
        }
        return false;
    }

    /**
    * 是否请求的是 jsp 资源
    */
    private boolean isJspResource(String url) {
        return url.startsWith(JSP_RESOURCE_PREFIX);
    }
}
