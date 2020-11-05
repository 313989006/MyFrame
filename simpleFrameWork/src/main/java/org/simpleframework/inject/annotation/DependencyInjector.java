package org.simpleframework.inject.annotation;

import javafx.beans.binding.ObjectExpression;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * @ClassName DependencyInjector
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/4 10:01
 **/
public class DependencyInjector {

    /**
    * @Description: Bean 容器，私有化一个Bean容器
    * @Param:
    * @return:
    */
    private BeanContainer beanContainer;

    /**
    * @Description: 获取 Bean 容器
    * @Param:
    * @return:
    */
    public DependencyInjector(){
        beanContainer = BeanContainer.instance();
    }

    /**
    * @Description: 执行 IOC
    * @Param:
    * @return:
    */
    public void doIOC(){
        // 先校验下 classes 是否存在
        if (ValidationUtil.isEmpty(beanContainer.getClasses())){
            return;
        }
        // 1、遍历 Bean 容器中所有的 Class 对象
        for (Class<?> clazz : beanContainer.getClasses()) {

            // 2、遍历 Class 对象的所有成员变量
            Field[] fields = clazz.getDeclaredFields();
            if (ValidationUtil.isEmpty(fields)){
                continue;
            }
            for (Field field : fields){
                // 3、找出被 Autowired 注解标记的成员变量
                if (field.isAnnotationPresent(Autowired.class)){

                    // 获取 Autowired 实例
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String autowiredValue = autowired.value();

                    // 4、获取这些成员变量的类型( getType() 方法来获取成员变量的类型)
                    Class<?> fieldClass = field.getType();
                    // 5、获取这些成员变量的类型在容器中对应的实例
                    Object fieldObj = getFieldInstance(fieldClass,autowiredValue);
                    if (fieldObj == null){
                        // 如果获取不到，抛出注入失败的异常
                        throw new RuntimeException("注入失败！");
                    } else {
                        // 6、通过反射将对应的成员变量实例注入到成员变量所在的类的实例里
                        Object targetBean = beanContainer.getBeanByClass(clazz);
                        ClassUtil.setField(field,targetBean,fieldObj,true);
                    }
                }
            }
        }
    }

    /**
    * @Description: 根据 Class 在 beanContainer 里获取其实例或者实现类
    * @Param:
    * @return:
    */
    private Object getFieldInstance(Class<?> fieldClass,String autowiredValue) {
        Object object = beanContainer.getBeanByClass(fieldClass);
        if (object != null){
            // 如果不为空,说明该成员变量的类型不是接口，直接返回object
            return object;
        } else {
            // 表示该成员变量的类型有可能是接口
            // 获取 fieldClass 对应的实现类对应的Class对象
            Class<?> implementedClass = getImplementedClass(fieldClass,autowiredValue);
            if (implementedClass != null){
                return beanContainer.getBeanByClass(implementedClass);
            }
        }
        return null;
    }

    /**
    * @Description: 根据接口成员变量的 class 获取实现他的实现类
    * @Param:
    * @return:
    */
    private Class<?> getImplementedClass(Class<?> fieldClass,String autowiredValue) {
        Set<Class<?>> classSet = beanContainer.getClassesBySuper(fieldClass);
        if (!ValidationUtil.isEmpty(autowiredValue)){
            if (classSet.size() == 1){
                // 如果只有一个实现类的情况下
                // 返回 class 对象的实例
                return classSet.iterator().next();
            } else {
                // 如果多于两个实现类且用户未指定其中一个实现类，则抛出异常
                throw new RuntimeException("请确定一个实现类");
            }
        } else {
            for (Class<?> clazz : classSet) {
                // 判断 autowiredValue 是否等于获取到的class对象的名字（去除掉package的）
                if (autowiredValue.equals(clazz.getSimpleName())){
                    return clazz;
                }
            }
        }
        return null;
    }
}
