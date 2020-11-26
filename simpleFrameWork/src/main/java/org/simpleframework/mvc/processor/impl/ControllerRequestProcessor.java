package org.simpleframework.mvc.processor.impl;

import com.sun.corba.se.spi.ior.iiop.RequestPartitioningComponent;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.mvc.RequestProcessorChain;
import org.simpleframework.mvc.annotation.RequestMapping;
import org.simpleframework.mvc.annotation.RequestParam;
import org.simpleframework.mvc.annotation.ResponseBody;
import org.simpleframework.mvc.processor.RequestProcessor;
import org.simpleframework.mvc.render.JsonResultRender;
import org.simpleframework.mvc.render.ResourceNotFoundResultRender;
import org.simpleframework.mvc.render.ResultRender;
import org.simpleframework.mvc.render.ViewResultRender;
import org.simpleframework.mvc.type.ControllerMethod;
import org.simpleframework.mvc.type.RequestPathInfo;
import org.simpleframework.util.ConverterUtil;
import org.simpleframework.util.ValidationUtil;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Controller 请求处理器
 **/
@Slf4j
public class ControllerRequestProcessor implements RequestProcessor {

    // IOC 容器
    private BeanContainer beanContainer;

    // 请求和 controller 方法的映射集合
    private Map<RequestPathInfo,ControllerMethod> pathControllerMethodMap = new ConcurrentHashMap<>();

    public ControllerRequestProcessor(){
        this.beanContainer = BeanContainer.instance();
        Set<Class<?>> requestMappingSet = beanContainer.getClassesByAnnotation(RequestMapping.class);
        initPathControllerMehthodMap(requestMappingSet);
    }

    private void initPathControllerMehthodMap(Set<Class<?>> requestMappingSet) {
        if (ValidationUtil.isEmpty(requestMappingSet)){
            return;
        }
        // 1、遍历所有被 @RequestMapping 标记的类，获取类上面该注解的属性值作为一级路径
        for (Class<?> requestMappingClass : requestMappingSet){
            RequestMapping requestMapping = requestMappingClass.getAnnotation(RequestMapping.class);
            String basePath = requestMapping.value();
            if (!basePath.startsWith("/")){
                basePath = "/" + basePath;
            }

            // 2、遍历类里所有被 @RequestMapping 标记的方法，获取方法上面该注解的属性值，作为二级路径
            Method[] methods = requestMappingClass.getDeclaredMethods();
            if (ValidationUtil.isEmpty(methods)){
                continue;
            }
            for (Method method : methods){
                if (method.isAnnotationPresent(RequestMapping.class)){
                    RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class );
                    String methodPath = methodRequestMapping.value();
                    if (!methodPath.startsWith("/")){
                        methodPath = "/" + methodPath;
                    }
                    String url = basePath + methodPath;

                    // 3、解析方法里被 @ RequestParam 标记的参数
                    // 获取改注解的属性值，作为参数名
                    // 获取被标记的参数的数据类型，建立参数名和参数类型的映射
                    Map<String ,Class<?>> methodParams = new HashMap<>();
                    Parameter[] parameters = method.getParameters();
                    if (ValidationUtil.isEmpty(parameters)){
                        continue;
                    }
                    for (Parameter parameter : parameters){
                        RequestParam param = parameter.getAnnotation(RequestParam.class);
                        // 目前暂定为 Controller 方法粒粒面所有的参数都需要 @RequestParam 注解
                        if (param == null){
                            throw new RuntimeException("The parameter must have @RequestParam");
                        }
                        methodParams.put(param.value(),parameter.getType());

                        // 4、将获取到的信息封装成 RequestPathInfo 实例 和 ControllerMethod 实例，放置到映射表 pathControllerMethodMap 里
                        String httpMethod = String.valueOf(methodRequestMapping.method());
                        RequestPathInfo requestPathInfo = new RequestPathInfo(httpMethod, url);

                        if (this.pathControllerMethodMap.containsKey(requestPathInfo)) {
                            log.warn("duplicate url :{} registration ,current class {} method {} will override the former one ",
                                    requestPathInfo.getHttpPath(),requestMappingClass.getName(),method.getName());
                        }
                        ControllerMethod controllerMethod = new ControllerMethod(requestMappingClass, method, methodParams);
                        this.pathControllerMethodMap.put(requestPathInfo,controllerMethod);
                    }
                }
            }
        }
    }

    @Override
    public boolean process(RequestProcessorChain requestProcessorChain) throws Exception {
        // 1、解析 HttpServletRequest 的请求方法，请求路径，获取对应的 ControllerMethod 实例
        // 请求方法
        String method = requestProcessorChain.getRequestMethod();
        // 请求路径
        String path = requestProcessorChain.getRequestPath();
        ControllerMethod controllerMethod = this.pathControllerMethodMap.get(new RequestPathInfo(method, path));
        if (controllerMethod == null){
            requestProcessorChain.setResultRender(new ResourceNotFoundResultRender(method,path));
            return false;
        }

        // 2、解析请求参数，并传递给获取到的 ControllerMethod 实例去执行
        Object result = invokeControllerMethod(controllerMethod,requestProcessorChain.getRequest());
        // 3、根据解析的结果，选择对应的 render 进行渲染
        setResultRender(result,controllerMethod,requestProcessorChain);
        return false;
    }

    private Object invokeControllerMethod(ControllerMethod controllerMethod, HttpServletRequest request) {
        // 1、请求里获取 GET 或者 POST 的参数名及其对应的值
        Map<String,String> requestParamMap = new HashMap<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String,String[]> parameter : parameterMap.entrySet()) {
            if (ValidationUtil.isEmpty(parameter.getValue())) {
                // 只支持一个参数对应一个值的形式
                requestParamMap.put(parameter.getKey(),parameter.getValue()[0]);
            }
        }
        // 2、根据获取到的请求参数名及其对应的值，以及 controllerMethod 里面的参数和类型的映射关系，去实例化方法对应的参数
        List<Object> methodParams = new ArrayList<>();
        Map<String, Class<?>> methodParamMap = controllerMethod.getMethodParameters();
        for (String paramName : methodParamMap.keySet()){
            Class<?> type = methodParamMap.get(paramName);
            String requestValue = requestParamMap.get(paramName);
            Object value;
            // 只支持 String以及寄出类型 char，int，short，byte，double，long，float，boolean 以及他们的包装类型
            if (requestValue == null){
                // 将请求里的参数值转成适配于参数类型的空值
                value = ConverterUtil.primitiveNull(type);
            } else {
                value = ConverterUtil.convert(type,requestValue);
            }
            methodParams.add(value);
        }
        // 3、执行 Controller 里面对应的方法并返回结果
        // 从容器中获取到对应的 Controller Class bean 实例
        Object controller = beanContainer.getBeanByClass(controllerMethod.getControllerClass());
        Method invokeMethod = controllerMethod.getInvokeMethod();
        invokeMethod.setAccessible(true);
        Object result;
        try {
            if (methodParams.size() == 0) {
                // 没有参数
                result = invokeMethod.invoke(controller);
            } else {
                result = invokeMethod.invoke(controller,methodParams.toArray());
            }
        } catch (InvocationTargetException e){
            // 如果是调用异常的话，需要通过 e.getTargetException() 去获取执行方法抛出的异常
            throw new RuntimeException(e.getTargetException());
        } catch (IllegalAccessException e){
            throw new RuntimeException(e);
        }
        return result;
    }


    /**
    * 根据解析的结果，选择对应的 render 进行渲染
    */
    private void setResultRender(Object result, ControllerMethod controllerMethod, RequestProcessorChain requestProcessorChain) {
        if (result == null){
            return;
        }
        ResultRender resultRender;
        boolean isJson = controllerMethod.getInvokeMethod().isAnnotationPresent(ResponseBody.class);
        if (isJson){
            resultRender = new JsonResultRender(result);
        } else {
            resultRender = new ViewResultRender(result);
        }
        requestProcessorChain.setResultRender(resultRender);



    }
}
