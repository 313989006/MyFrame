package demo.pattern.proxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName DynamicInvocationHandler
 * @Description 动态代理的 InvocationHandler
 * @Author ma.kangkang
 * @Date 2020/11/17 11:08
 **/
public class DynamicInvocationHandler implements InvocationHandler {

    private Object targetObject;

    public DynamicInvocationHandler(Object targetObject){
        this.targetObject = targetObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        beforePay();
        Object result = method.invoke(targetObject, args);
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
