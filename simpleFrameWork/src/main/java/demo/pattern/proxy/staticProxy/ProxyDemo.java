package demo.pattern.proxy.staticProxy;

import demo.pattern.proxy.staticProxy.impl.ToBPaymentImpl;
import demo.pattern.proxy.staticProxy.impl.ToCPaymentImpl;

/**
 * @ClassName ProxyDemo
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/17 11:05
 **/
public class ProxyDemo {

    public static void main(String[] args) {
        ToCPayment toCPayment = new AliPayToC(new ToCPaymentImpl());
        toCPayment.pay();
        ToBPayment toBPayment = new AliPayToB(new ToBPaymentImpl());
        toBPayment.pay();
    }
}
