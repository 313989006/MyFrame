package demo.pattern.proxy.staticProxy;

import demo.pattern.proxy.staticProxy.impl.ToBPaymentImpl;
import demo.pattern.proxy.staticProxy.impl.ToCPaymentImpl;

/**
 * @ClassName AliPayToC
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/17 11:00
 **/
public class AliPayToB implements ToBPayment {

    private ToBPayment toBPayment;

    public AliPayToB(ToBPayment toBPayment) {
        this.toBPayment = toBPayment;
    }

    @Override
    public void pay() {
        before();
        toBPayment.pay();
        after();
    }

    public void before(){
        System.out.println("从建行取款");
    }

    public void after(){
        System.out.println("支付给Mxk");
    }
}
