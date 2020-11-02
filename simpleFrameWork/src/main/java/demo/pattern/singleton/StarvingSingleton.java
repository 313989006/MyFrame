package demo.pattern.singleton;

/**
 * @ClassName StarvingSingleton
 * @Description 单例模式---饿汉式单例模式
 *
 * @Author ma.kangkang
 * @Date 2020/11/2 9:46
 **/
public class StarvingSingleton {

    // 线程安全：在线程出现以前，就已经把对象实例化好了


    // 类加载的时候，直接new出一个实例赋值给starvingSingleton，并且不能修改
    // 成员变量为私有的，防止外部直接调用 StarvingSingleton.starvingSingleton 直接获取对象实例
    private static final StarvingSingleton starvingSingleton = new StarvingSingleton();

    // 构造方法设置为私有的，以防止客户端通过调用构造函数来创建出实例
    private StarvingSingleton() {
    }

    // 提供唯一一个访问成员变量的方式，静态的 getInstance 方法，让客户端获取已经初始化好的 StarvingSingleton 实例
    public static StarvingSingleton getInstance(){
        return  starvingSingleton;
    }

    public static void main(String[] args) {
        System.out.println(StarvingSingleton.getInstance());
        System.out.println(StarvingSingleton.getInstance());
    }
}
