package org.simpleframework.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.Component;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.annotation.Repository;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 获取Bean实例
 */
// 增加日志注解
@Slf4j
// 注解替换无参构造方法实现
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanContainer {

    /**
    * @Description: 存放所有被配置标记的目标对象的Map
     * 实现保存对象的载体，为什么使用 ConcurrentHashMap？
     * (因为 ConcurrentHashMap，利用CAS、红黑树细化锁的粒度和提升扩容效率，进一步提升并发性能。 支持并发，并且并发性很好，稍后百度学习)
    */
    private final Map<Class<?>,Object> beanMap = new ConcurrentHashMap<>();

    /**
     * @Description: 加载Bean的注解列表
     */
    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION
            = Arrays.asList(Component.class, Controller.class, Service.class, Repository.class);


    /**
    * 1,2,3 获取 IOC 容器的实例
    */
    /**
    * @Description: 1、定义私有的无参构造方法 // 这里使用注解进行替换
    * @Param:
    * @return:
    * @Author: ma.kangkang
    * @Date: 2020/11/2
    */
//    private BeanContainer(){
//
//    }

    /**
    * @Description: 2、定义私有的enum类型的Holder
    * @Param:
    * @return:
    * @Author: ma.kangkang
    * @Date: 2020/11/2
    */
    private enum ContainerHolder{
        // 枚举值，用来保存单例
        HOLDER;
        // 创建成员变量，定义为 private
        private BeanContainer instance;

        private ContainerHolder(){
            instance = new BeanContainer();
        }
    }

    /**
    * @Description: 3、提供一个供客户端获取单例的入口
    * @Param:
    * @return:
    * @Author: ma.kangkang
    * @Date: 2020/11/2
    */
    public static BeanContainer instance(){
        return ContainerHolder.HOLDER.instance;
    }

    /**
    * @Description: 扫描加载所有类
    */
    public synchronized void loadBeans(String packageName) throws IllegalAccessException, InstantiationException {

        // 判断bean是否被加载，加载过的话就直接返回，没有被加载过的话，就继续执行加载
        if (isLoaded()){
            log.warn("已经被加载过！");
            return;
        }
        // 通过 ClassUtil 获取包下所有的类的集合
        Set<Class<?>> classSet = ClassUtil.extractPackageClass(packageName);
//        if (classSet == null || classSet.isEmpty()){
        if (ValidationUtil.isEmpty(classSet)){
            log.warn("package下面提取不到任何东西");
            return;
        }
        for (Class<?> clazz : classSet) {
            for (Class<? extends Annotation> annotation  :BEAN_ANNOTATION) {
                // 如果类上标记了自定义的注解
                if (clazz.isAnnotationPresent(annotation)){
                    // 将目标类本身作为键，目标类的实例作为值，放入到 beanMap，
                    // 由于 newInstance 方法过时了，所以此处封装一个公共方法 到 ClassUtil 里，即 ClassUtil.newInstance(clazz);
//                    beanMap.put(clazz,clazz.newInstance());
                    beanMap.put(clazz,ClassUtil.newInstance(clazz,true));
                }

            }
        }
        loaded = true;
    }

    /**
    * @Description: 定义私有成员变量判断容器是否加载过bean
    * @Param:
    * @return:
    */
    private boolean loaded = false;

    /**
    * @Description: 是否被加载
    * @Param:
    * @return:
    */
    public boolean isLoaded(){
        return loaded;
    }
    /**
    * @Description: 获取bean实例数量
    * @Param:
    * @return:
    */
    public int size(){
        return beanMap.size();
    }
}
