package demo.pattern.eventmode;

/**
 * @ClassName SingleClickEventListener
 * @Description 单击事件监听器
 * @Author ma.kangkang
 * @Date 2020/11/10 20:59
 **/
public class SingleClickEventListener implements EventListener {
    @Override
    public void processEvent(Event event) {
        if ("singleClick".equals(event.getType())){
            System.out.println("单击事件被触发了");
        }
    }
}
