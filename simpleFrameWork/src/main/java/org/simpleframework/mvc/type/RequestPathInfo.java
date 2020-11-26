package org.simpleframework.mvc.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 存储 http 请求路径和请求方法
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPathInfo {
    // http 请求方法
    private String httpMethod;

    // http 请求路径
    private String httpPath;
}
