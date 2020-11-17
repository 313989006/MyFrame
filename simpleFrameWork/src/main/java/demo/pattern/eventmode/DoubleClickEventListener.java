package demo.pattern.eventmode;

/**
 * @ClassName DoubleClickEventListener
 * @Description 双击事件监听器
 * @Author ma.kangkang
 * @Date 2020/11/10 21:00
 **/
public class DoubleClickEventListener implements EventListener{

    private String name;

    // 验证 static 是在类被初始化的时候执行的
    static {
        System.out.println("静态方法被调用");
    }

    public DoubleClickEventListener(){
        System.out.println("无参构造方法被调用");
        this.name = "mxk";
    }

    @Override
    public void processEvent(Event event) {
        if ("doubleClick".equals(event.getType())){
            System.out.println("双击事件被触发了");
        }
    }

}
