package demo.pattern.proxy.staticProxy.impl;

import demo.pattern.proxy.staticProxy.ToBPayment;

/**
 * @ClassName ToBPaymentImpl
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/17 10:59
 **/
public class ToBPaymentImpl implements ToBPayment {
    @Override
    public void pay() {
        System.out.println("以公司的名义取款");
    }
}
