//package org.simpleframework.aop;
//
//import org.simpleframework.aop.annotation.Aspect1;
//import org.simpleframework.aop.annotation.Order;
//import org.simpleframework.aop.aspect.AspectInfo1;
//import org.simpleframework.aop.aspect.DefaultAspect;
//import org.simpleframework.core.BeanContainer;
//import org.simpleframework.util.ValidationUtil;
//
//import java.lang.annotation.Annotation;
//import java.util.*;
//
///**
// * @ClassName AspectWeaver
// * @Description AOP 1.0 的 AspectWeaver
// * @Author ma.kangkang
// * @Date 2020/11/17 20:53
// **/
//public class AspectWeaver1 {
//    private BeanContainer beanContainer;
//
//    // 因为容器是绝对单例的，所以直接用无参构造方法获取容器实例
//    public AspectWeaver1(){
//        this.beanContainer = BeanContainer.instance();
//    }
//
//    // 真正实现AOP的方法
//    public void doAop(){
//        // 1、获取所有的切面类（从容器中获取被 Aspect 标签标记的所有的类）
//        Set<Class<?>> aspectSet = beanContainer.getClassesByAnnotation(Aspect1.class);
//        // 2、将切面类按照不同的织入目标进行切分（按照Aspect 的属性值进行分类，针对不同的注解，获取不同的列表）
//        Map<Class<? extends Annotation>, List<AspectInfo1>> categorizedMap = new HashMap();
//        // 判空
//        if (ValidationUtil.isEmpty(aspectSet)){return;}
//        // 遍历切面类
//        for (Class<?> aspectClass : aspectSet){
//            // 验证 aspectClass 的合法性
//            if (verifyAspect(aspectClass)){
//                // 执行 aspectClass 的分类逻辑
//                categorizeAspect(categorizedMap,aspectClass);
//            } else {
//                // 当前切面类没有被@Aspect或者@Order标签标记，或者不是继承自DefaultAspect，或者该Aspect的属性是@Aspect标签本身
//                throw new RuntimeException("@Aspect and @Order have not been added to the Aspect class," +
//                        "or Aspect class does not extend from DefaultAspect ,or the value in Aspect Tag equals @Aspect");
//            }
//        }
//        // 3、按照不同的织入目标分别去按序织入 Aspect 的逻辑
//        // 判空
//        if (ValidationUtil.isEmpty(categorizedMap)){return;}
//        for (Class<? extends Annotation> category : categorizedMap.keySet()){
//            weaverByCategory(category,categorizedMap.get(category));
//        }
//
//    }
//
//    // 框架中一定要遵守给 Aspect 类添加 @Aspect 和 @Order 标签的规范，同时，必须继承自 DefaultAspect.class
//    // 此外，@Aspect 的属性值不能是它本身
//    private boolean verifyAspect(Class<?> aspectClass) {
//        return aspectClass.isAnnotationPresent(Aspect1.class) &&
//                // 是否被 Order 标签标记
//                aspectClass.isAnnotationPresent(Order.class) &&
//                // 是否继承自 DefaultAspect
//                DefaultAspect.class.isAssignableFrom(aspectClass) &&
//                // @Aspect 的属性值不能是它本身
//                aspectClass.getAnnotation(Aspect1.class).value() != Aspect1.class;
//    }
//
//    private void categorizeAspect(Map<Class<? extends Annotation>, List<AspectInfo1>> categorizedMap, Class<?> aspectClass) {
//        // 获取 Order 标签实例
//        Order orderTag = aspectClass.getAnnotation(Order.class);
//        // 获取 Aspect 标签实例
//        Aspect1 aspectTag = aspectClass.getAnnotation(Aspect1.class);
//        // 获取 aspectClass 获取对应的 Aspect 实例
//        DefaultAspect aspect = (DefaultAspect) beanContainer.getBeanByClass(aspectClass);
//        AspectInfo1 aspectInfo = new AspectInfo1(orderTag.value(),aspect);
//
//        if (!categorizedMap.containsKey(aspectTag.value())){
//            // 如果织入的 joinPoint 第一次出现，则以该 joinPoint 为key，以新创建的 List<AspectInfo> 为value
//            List<AspectInfo1> aspectInfoList = new ArrayList<>();
//            aspectInfoList.add(aspectInfo);
//            categorizedMap.put(aspectTag.value(),aspectInfoList);
//        } else {
//            // 如果织入的 joinPoint 不是第一次出现，则往 joinPoint 对应的 value 里添加新的 Aspect 逻辑
//            List<AspectInfo1> aspectInfoList = categorizedMap.get(aspectTag.value());
//            aspectInfoList.add(aspectInfo);
//            categorizedMap.put(aspectTag.value(),aspectInfoList);
//        }
//    }
//
//    private void weaverByCategory(Class<? extends Annotation> category, List<AspectInfo1> aspectInfos) {
//        // 1、获取被代理类的集合
//        Set<Class<?>> classSet = beanContainer.getClassesByAnnotation(category);
//        if (ValidationUtil.isEmpty(classSet)){return;}
//        // 2、遍历被代理类，分别为每个被代理类生成动态代理实例
//        for (Class<?> targetClass : classSet){
//            AspectExcutor1 aspectExcutor = new AspectExcutor1(targetClass,aspectInfos);
//            // 创建动态代理对象
//            Object proxyBean = ProxyCreator.createProxy(targetClass, aspectExcutor);
//            // 3、将动态代理对象实例添加到容器里，取代未被代理前的类实例
//            beanContainer.addBean(targetClass,proxyBean);
//        }
//
//    }
//}
