package org.simpleframework.mvc.render;

import com.google.gson.Gson;
import org.ietf.jgss.GSSContext;
import org.simpleframework.mvc.RequestProcessorChain;

import java.io.PrintWriter;

/**
 * json 渲染器
 **/
public class JsonResultRender implements ResultRender {

    private Object jsonData;

    public JsonResultRender(Object jsonData) {
        this.jsonData = jsonData;
    }

    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Exception {
        requestProcessorChain.getResponse().setContentType("application/json");
        requestProcessorChain.getResponse().setCharacterEncoding("UTF-8");
        // 响应流写入经过 gson 格式化之后的处理结果
        try ( PrintWriter writer = requestProcessorChain.getResponse().getWriter()){
            Gson gson = new Gson();
            writer.write(gson.toJson(jsonData));
            writer.flush();
        }


    }
}
