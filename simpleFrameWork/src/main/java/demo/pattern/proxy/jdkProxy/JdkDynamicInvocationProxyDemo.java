package demo.pattern.proxy.jdkProxy;

import demo.pattern.proxy.staticProxy.ToBPayment;
import demo.pattern.proxy.staticProxy.ToCPayment;
import demo.pattern.proxy.staticProxy.impl.ToBPaymentImpl;
import demo.pattern.proxy.staticProxy.impl.ToCPaymentImpl;

import java.lang.reflect.InvocationHandler;

/**
 * @ClassName JdkDynamicInvocationProxyDemo
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/17 11:17
 **/
public class JdkDynamicInvocationProxyDemo {

    public static void main(String[] args) {
        ToCPayment toCPayment = new ToCPaymentImpl();
        InvocationHandler handler = new DynamicInvocationHandler(toCPayment);
        ToCPayment toCPayProxy = JdkDynamicProxyUtil.newProxyInstance(toCPayment, handler);
        toCPayProxy.pay();

//        ToBPayment toBPayment = new ToBPaymentImpl();
//        InvocationHandler handler2 = new DynamicInvocationHandler(toBPayment);
//        ToBPayment toBPayProxy = JdkDynamicProxyUtil.newProxyInstance(toBPayment, handler2);
//        toBPayProxy.pay();
    }
}
