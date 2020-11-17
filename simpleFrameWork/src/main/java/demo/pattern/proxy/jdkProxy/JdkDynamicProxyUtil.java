package demo.pattern.proxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @ClassName DynamicInvocationProxyUtil
 * @Description 动态代理的 util，包装 Proxy
 * @Author ma.kangkang
 * @Date 2020/11/17 11:13
 **/
public class JdkDynamicProxyUtil {

    /**
    * @Description: TODO
    * @Param:  targetObject 被代理的类的实例
    * @Param:  handler
    * @return:
    */
    public static <T>T newProxyInstance(Object targetObject, InvocationHandler handler){
        // newProxyInstance 需要
        // 1、类加载器（classLoader）
        // 2、interfaces （将要给我们要代理的对象提供一组什么样的接口，代理类最终会实现这些接口）
        // 3、handler 当前动态代理对象的方法被调用的时候会关联到哪一个 InvocationHandler 的实现类实例上
        ClassLoader classLoader = targetObject.getClass().getClassLoader();
        Class<?>[] interfaces = targetObject.getClass().getInterfaces();
        return (T)Proxy.newProxyInstance(classLoader, interfaces, handler);
    }
}
