package demo.pattern.proxy.cglibProxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName AliMethodIntercepter
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/17 12:48
 **/
public class AliMethodIntercepter implements MethodInterceptor {
    @Override
    public Object intercept(Object targetObject, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        beforePay();
        Object result = methodProxy.invokeSuper(targetObject,args);
        afterPay();
        return result;
    }

    public void beforePay(){
        System.out.println("从建行取款");
    }

    public void afterPay(){
        System.out.println("支付给Mxk");
    }

}
