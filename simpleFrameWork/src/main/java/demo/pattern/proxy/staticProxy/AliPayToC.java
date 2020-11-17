package demo.pattern.proxy.staticProxy;

import demo.pattern.proxy.staticProxy.impl.ToCPaymentImpl;

/**
 * @ClassName AliPayToC
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/17 11:00
 **/
public class AliPayToC implements ToCPayment {

    private ToCPayment toCPayment;

    public AliPayToC(ToCPayment toCPayment) {
        this.toCPayment = toCPayment;
    }

    @Override
    public void pay() {
        before();
        toCPayment.pay();
        after();
    }

    public void before(){
        System.out.println("从建行取款");
    }

    public void after(){
        System.out.println("支付给Mxk");
    }
}
