package org.simpleframework.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.aop.annotation.Aspect;
import org.simpleframework.core.annotation.Component;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.annotation.Repository;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 获取Bean实例: IOC 容器
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
            = Arrays.asList(Component.class, Controller.class, Service.class, Repository.class, Aspect.class);


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
            for (Class<? extends Annotation> annotation : BEAN_ANNOTATION) {
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

    /**
    * @Description: 添加一个Bean到IOC容器
    * @Param: clazz : Class 对象
    * @Param: bean : Bean 实例
    * @return: 原本的Bean实例，没有则返回null，具体返回查看map.put（）的源码
    */
    public Object addBean(Class<?> clazz,Object bean){
        return beanMap.put(clazz,bean);
    }

    /**
     * @Description: 删除一个Bean到IOC容器
     * @Param: clazz : Class 对象
     * @return: 原本的Bean实例，没有则返回null，具体返回查看map.put（）的源码
     */
    public Object removeBean(Class<?> clazz){
        return beanMap.remove(clazz);
    }

    /**
    * @Description: 根据Class对象获取Bean实例
    * @Param:  clazz : Class 对象
    * @return: 原本的Bean实例，没有则返回null，具体返回查看map.get（）的源码
    */
    public Object getBeanByClass(Class<?> clazz){
        return beanMap.get(clazz);
    }

    /**
    * @Description: 获取容器管理的所有Class对象集合
    * @Param:
    * @return:
    */
    public Set<Class<?>> getClasses(){
        return beanMap.keySet() ;
    }

    /**
    * @Description: 获取所有bean集合（由于返回的是所有Bean 实例的集合，所以返回的是 Set<Object>）
    * @Param:
    * @return: Bean 集合
    */
    public Set<Object> getBeans(){
        return new HashSet<>(beanMap.values());
    }

    /**
    * @Description: 根据注解获取Class对象集合
    * @Param:   annotation ： 注解
    * @return: Class集合
    */
    public Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotation){
        // 1、获取BeanMap的所有class对象
        Set<Class<?>> keySet = getClasses();
        // 2、通过注解筛选被注解标记的class对象，并添加到classSet里
        if (ValidationUtil.isEmpty(keySet)){
            log.warn("beanMap 无class对象！");
            return null;
        }
        Set<Class<?>> classSet = new HashSet<>();
        for ( Class<?> clazz : keySet) {
            // 判断类是否被相关的注解标记
            if (clazz.isAnnotationPresent(annotation)){
                classSet.add(clazz);
            }
        }
        return classSet.size() > 0 ? classSet : null;
    }

    /**
    * @Description: 通过接口或者父类获取实现类或者子类的Class集合，不包括其本身
    * @Param: interfaceOrClass : 接口Class或者父类Class
    * @return: Class集合
    */
    public Set<Class<?>> getClassesBySuper(Class<?> interfaceOrClass){
        // 1、获取BeanMap的所有class对象
        Set<Class<?>> keySet = getClasses();
        if (ValidationUtil.isEmpty(keySet)){
            log.warn("beanMap 无class对象！");
            return null;
        }
        // 2、判断ketSet里的元素是否是传入的接口或者类的子类，如果是，就将其添加到classSet里
        Set<Class<?>> classSet = new HashSet<>();
        for ( Class<?> clazz : keySet) {
            // 判断类是否被相关的注解标记
            if (interfaceOrClass.isAssignableFrom(clazz) && !clazz.equals(interfaceOrClass)){
                classSet.add(clazz);
            }
        }
        return classSet.size() > 0 ? classSet : null;
    }
}
