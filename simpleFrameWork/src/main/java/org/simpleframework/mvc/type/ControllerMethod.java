package org.simpleframework.mvc.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 待执行的 Controller 及其方法实例和参数的映射
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControllerMethod {
    // Controller 对应的 class 对象
    private Class<?> controllerClass;

    // 执行的 Controller 方法实例
    private Method invokeMethod;

    // 方法参数名称以及对应的参数类型
    private Map<String,Class<?>> methodParameters;
}
