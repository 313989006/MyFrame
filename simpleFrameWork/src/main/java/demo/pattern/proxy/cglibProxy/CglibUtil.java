package demo.pattern.proxy.cglibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @ClassName CglibUtil
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/17 12:49
 **/
public class CglibUtil {

    /**
    * @Description: TODO
    * @Param:  targetObject  被代理对象实例
    * @Param:  methodInterceptor  aspect 实例
    * @return:
    */
    public static <T>T createCglibProxy(T targetObject, MethodInterceptor methodInterceptor){
        return (T) Enhancer.create(targetObject.getClass(),methodInterceptor);
    }
}
