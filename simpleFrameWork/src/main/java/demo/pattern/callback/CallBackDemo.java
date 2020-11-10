package demo.pattern.callback;

/**
 * @ClassName CallBackDemo
 * @Description 回调函数测试
 * @Author ma.kangkang
 * @Date 2020/11/10 17:35
 **/
public class CallBackDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("我要睡觉了");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("我被回调了");
            }
        });
        thread.start();
    }
}
