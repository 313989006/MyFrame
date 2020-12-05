package org.simpleframework.mvc.render;

import org.simpleframework.mvc.RequestProcessorChain;

import javax.servlet.http.HttpServletResponse;

/**
 * 内部异常渲染器
 **/
public class InternalErrorResultRender implements ResultRender {

    private String errorMsg;

    public InternalErrorResultRender(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Exception {
        // HttpServletResponse.SC_INTERNAL_SERVER_ERROR ： 500
        requestProcessorChain.getResponse().sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,errorMsg);
    }
}
