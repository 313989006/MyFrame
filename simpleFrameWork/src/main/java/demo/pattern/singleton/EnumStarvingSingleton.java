package demo.pattern.singleton;

/**
 * @ClassName EnumStarvingSingleton
 * @Description 无视反射和序列化攻击的单例：通过 enum
 * @Author ma.kangkang
 * @Date 2020/11/2 12:30
 **/
public class EnumStarvingSingleton {

    // 私有无参构造函数
    private EnumStarvingSingleton() {
    }

    // 客户端统一用 静态 getInstance 方法，获取单例
    public static EnumStarvingSingleton getInstance(){
        return ContainerHolder.HOLDER.instance;
    }

    // 私有枚举类型变量
    private enum ContainerHolder{
        // 枚举值，用来保存单例
        HOLDER;
        // 创建成员变量，定义为 private
        private EnumStarvingSingleton instance;
        // 在类加载的时候，就初始化实例，所以是懒汉式的
        ContainerHolder() {
            instance = new EnumStarvingSingleton();
        }
    }

}
