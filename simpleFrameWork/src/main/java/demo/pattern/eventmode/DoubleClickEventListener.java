package demo.pattern.eventmode;

/**
 * @ClassName DoubleClickEventListener
 * @Description 双击事件监听器
 * @Author ma.kangkang
 * @Date 2020/11/10 21:00
 **/
public class DoubleClickEventListener implements EventListener{
    @Override
    public void processEvent(Event event) {
        if ("doubleClick".equals(event.getType())){
            System.out.println("双击事件被触发了");
        }
    }

}
