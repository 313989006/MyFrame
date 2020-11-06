package demo.pattern.template;

/**
 * @ClassName KTVRoom
 * @Description KTV包厢，点歌（必须），点东西（非必须）
 * @Author ma.kangkang
 * @Date 2020/11/6 16:15
 **/
public abstract class KTVRoom {

    public void procedure(){
        openDevice();
        orderSong();
        orderExtra();
        pay();
    }

    // 模板自带方法，使用前必须得打开设备
    private void openDevice(){
        System.out.println("打开音响和视频");
    }

    // 子类必须实现的方法，必须得选歌
    protected abstract void orderSong();

    // 钩子，额外开销视情况而定
    protected void orderExtra(){}

    // 模板自带方法，消费完，必须付款
    private void pay() {
        System.out.println("支付本次的消费账单...");
    }

}
