package com.mxk.aspect;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.aop.annotation.Aspect1;
import org.simpleframework.aop.annotation.Aspect2;
import org.simpleframework.aop.annotation.Order;
import org.simpleframework.aop.aspect.DefaultAspect;
import org.simpleframework.core.annotation.Service;

import java.lang.reflect.Method;

/**
 * @ClassName ControllerInfoRecordAspect
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/20 9:08
 **/

//@Aspect1(Service.class)
@Aspect2(pointcut = "within(org.simpleframework.aop.annotation.Component)")
@Slf4j
@Order(1)
public class ServiceInfoRecordAspect extends DefaultAspect {

    @Override
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {
        log.info("方法开始执行了，执行的类是[{}]，执行的方法是[{}],参数是[{}]",targetClass.getName(),method.getName(),args);
    }

    @Override
    public Object afterReturning(Class<?> targetClass, Method method, Object[] args, Object returnValue) throws Throwable {
        log.info("方法执行完成了，执行的类是[{}]，执行的方法是[{}],参数是[{}]，返回的是[{}]",targetClass.getName(),method.getName(),args,returnValue);
        return returnValue;
    }

    @Override
    public void afterThrowing(Class<?> targetClass, Method method, Object[] args, Throwable throwable) throws Throwable {
        log.info("方法执行异常了，执行的类是[{}]，执行的方法是[{}],参数是[{}]，异常是[{}]",targetClass.getName(),method.getName(),throwable.getMessage());

    }
}
