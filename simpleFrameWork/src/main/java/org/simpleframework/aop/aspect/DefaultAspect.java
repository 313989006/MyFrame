package org.simpleframework.aop.aspect;

import java.lang.reflect.Method;

/**
 * @ClassName DefaultAsoect
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/17 14:09
 **/
public abstract class DefaultAspect {

    /**
    * 事前拦截
     * targetClass 被代理的目标类
     * method   被代理的目标方法
     * args 被代理的目标方法的参数列表
    */
    public void before(Class<?> targetClass, Method method,Object[] args) throws Throwable{

    }

    /**
     * 事后拦截
     * targetClass 被代理的目标类
     * method   被代理的目标方法
     * args 被代理的目标方法的参数列表
     * returnValue 被代理的目标方法执行后的返回值
     */
    public Object afterReturning(Class<?> targetClass, Method method,Object[] args,Object returnValue) throws Throwable{
        return returnValue;
    }

    /**
     * targetClass 被代理的目标类
     * method   被代理的目标方法
     * args 被代理的目标方法的参数列表
     * throwable 被代理的目标方法抛出的异常
     */
    public void afterThrowing(Class<?> targetClass, Method method,Object[] args,Throwable throwable) throws Throwable{

    }
}
