package demo.pattern.proxy.staticProxy.impl;

import demo.pattern.proxy.staticProxy.ToCPayment;

/**
 * @ClassName ToCPaymentImpl
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/17 10:59
 **/
public class ToCPaymentImpl implements ToCPayment {
    @Override
    public void pay() {
        System.out.println("以用户的名义取款");
    }
}
