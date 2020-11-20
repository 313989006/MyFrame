package org.simpleframework.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @ClassName ProxyCreator
 * @Description 创建动态代理对象 的类
 * @Author ma.kangkang
 * @Date 2020/11/17 15:51
 **/
public class ProxyCreator {

    /** 
    * 创建动态代理对象并返回
    * @Param: targetClass 被代理的目标对象
    * @Param: methodInterceptor 方法拦截器
    * @return: 
    */
    public static Object createProxy(Class<?> targetClass, MethodInterceptor methodInterceptor){
        // 创建动态代理对象
        return Enhancer.create(targetClass,methodInterceptor);
    }
}
