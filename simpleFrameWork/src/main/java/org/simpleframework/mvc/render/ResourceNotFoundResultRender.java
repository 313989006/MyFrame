package org.simpleframework.mvc.render;

import org.simpleframework.mvc.RequestProcessorChain;

import javax.servlet.http.HttpServletResponse;

/**
 * 资源找不到时使用的渲染器
 **/
public class ResourceNotFoundResultRender implements ResultRender {

    private String method;

    private String path;

    public ResourceNotFoundResultRender(String method, String path) {
        this.method = method;
        this.path = path;
    }

    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Exception {
        requestProcessorChain.getResponse().sendError(HttpServletResponse.SC_NOT_FOUND,"获取不到对应的请求资源/路径");
    }
}
