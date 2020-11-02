package demo.pattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @ClassName SingletonDemo
 * @Description 测试通过反射破除单例模式的防御
 * @Author ma.kangkang
 * @Date 2020/11/2 11:11
 **/
public class SingletonDemo {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 测试饿汉式单例模式通过 反射 暴力破除 private ，结果说明可以破除
        // 打印结果：
        // demo.pattern.singleton.StarvingSingleton@2503dbd3
        // demo.pattern.singleton.StarvingSingleton@4b67cf4d
        System.out.println(StarvingSingleton.getInstance());

        // 获取 Class 对象
        Class clazz = StarvingSingleton.class;
        // 通过反射获取 StarvingSingleton 的无参构造方法
        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        System.out.println(constructor.newInstance());


        // 测试懒汉式单例模式通过 反射 暴力破除 private ，结果说明可以破除
        // 打印结果：
        // demo.pattern.singleton.LazyDoubleCheckSingleton@7ea987ac
        // demo.pattern.singleton.LazyDoubleCheckSingleton@12a3a380
        System.out.println(LazyDoubleCheckSingleton.getInstance());
        // 获取 Class 对象
        Class clazz1 = LazyDoubleCheckSingleton.class;
        // 通过反射获取 StarvingSingleton 的无参构造方法
        Constructor constructor1 = clazz1.getDeclaredConstructor();
        constructor1.setAccessible(true);
        System.out.println(constructor1.newInstance());
    }

}
