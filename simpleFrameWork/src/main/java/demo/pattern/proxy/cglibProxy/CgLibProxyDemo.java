package demo.pattern.proxy.cglibProxy;

import demo.pattern.proxy.staticProxy.ToCPayment;
import demo.pattern.proxy.staticProxy.impl.ToCPaymentImpl;

/**
 * @ClassName CgLibProxyDemo
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/17 12:51
 **/
public class CgLibProxyDemo {

    public static void main(String[] args) {

        CommonPayment payment = new CommonPayment();
        AliMethodIntercepter methodIntercepter = new AliMethodIntercepter();
        CommonPayment commonPayment = CglibUtil.createCglibProxy(payment,methodIntercepter);
        commonPayment.pay();
        

        System.out.println("*******************");
        ToCPayment toCPayment = new ToCPaymentImpl();
        ToCPayment toCPaymentProxy = CglibUtil.createCglibProxy(toCPayment,methodIntercepter);
        toCPaymentProxy.pay();
    }
}
