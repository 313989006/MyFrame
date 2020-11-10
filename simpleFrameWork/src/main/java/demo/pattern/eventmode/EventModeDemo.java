package demo.pattern.eventmode;

/**
 * @ClassName EventMOdeDemo
 * @Description 事件监听器模式 的demo
 * @Author ma.kangkang
 * @Date 2020/11/10 21:05
 **/
public class EventModeDemo {
    public static void main(String[] args) {

        // 获取 事件源实例
        EventSource eventSource = new EventSource();
        // 获取单击事件监听器
        SingleClickEventListener singleClickEventListener = new SingleClickEventListener();
        // 获取双击事件监听器
        DoubleClickEventListener doubleClickEventListener = new DoubleClickEventListener();

        Event event = new Event();
        event.setType("doubleClick");

        // 注册事件监听器
        eventSource.registry(singleClickEventListener);
        eventSource.registry(doubleClickEventListener);

        // 发布事件
        eventSource.publish(event);


    }
}

