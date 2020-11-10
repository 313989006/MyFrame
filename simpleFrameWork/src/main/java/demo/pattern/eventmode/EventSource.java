package demo.pattern.eventmode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName EventSource
 * @Description 事件源
 * @Author ma.kangkang
 * @Date 2020/11/10 21:01
 **/
public class EventSource {

    private List<EventListener> listenerList = new ArrayList<>();

    // 注册事件监听器
    public void registry(EventListener eventListener){
        listenerList.add(eventListener);
    }

    // 发布事件
    public void publish(Event event){
        for (EventListener listener : listenerList){
            listener.processEvent(event);
        }
    }
}
