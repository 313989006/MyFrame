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
        // 1、测试饿汉式单例模式通过 反射 暴力破除 private ，结果说明可以破除
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


        // 2、测试懒汉式单例模式通过 反射 暴力破除 private ，结果说明可以破除
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


        ///3、通过enum 抵挡反射的攻击测试，结果一致，不能被破除
        // 打印结果：
        // demo.pattern.singleton.EnumStarvingSingleton@29453f44
        // demo.pattern.singleton.EnumStarvingSingleton@29453f44
        System.out.println(EnumStarvingSingleton.getInstance());
        // 获取 Class 对象
        Class clazz2 = EnumStarvingSingleton.class;
        // 通过反射获取 EnumStarvingSingleton 的无参构造方法
        Constructor constructor2 = clazz2.getDeclaredConstructor();
        constructor2.setAccessible(true);
        // 通过constructor2 new 一个 EnumStarvingSingleton 实例
        EnumStarvingSingleton enumStarvingSingleton = (EnumStarvingSingleton)constructor2.newInstance();
        System.out.println(enumStarvingSingleton.getInstance());
    }

}
