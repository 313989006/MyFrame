package com.mxk.aspect;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.aop.annotation.Aspect1;
import org.simpleframework.aop.annotation.Aspect2;
import org.simpleframework.aop.annotation.Order;
import org.simpleframework.aop.aspect.DefaultAspect;
import org.simpleframework.core.annotation.Service;

import java.lang.reflect.Method;

/**
 * @ClassName ControllerTimeCalculatorAspect
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/19 11:35
 **/
// 标记Controller标签的方法上织入横切逻辑
//@Aspect1(value = Service.class)
@Aspect2(pointcut = "within(org.simpleframework.aop.annotation.Component)")
@Slf4j
@Order(0)
public class ServiceTimeCalculatorAspect extends DefaultAspect {

    private long startTime;

    @Override
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {
        log.info("开始计时，执行的类是[{}]，执行的方法是[{}],参数是[{}]",targetClass.getName(),method.getName(),args);
        startTime = System.currentTimeMillis();
    }

    @Override
    public Object afterReturning(Class<?> targetClass, Method method, Object[] args, Object returnValue) throws Throwable {
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;
        log.info("结束计时，执行的类是[{}]，执行的方法是[{}],参数是[{}]，返回的是[{}]，时间为[{}ms]",
                targetClass.getName(),method.getName(),args,returnValue,costTime);
        return returnValue;
    }
}
