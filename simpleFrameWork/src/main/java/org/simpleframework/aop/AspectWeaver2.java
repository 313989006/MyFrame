package org.simpleframework.aop;

import org.simpleframework.aop.annotation.Aspect1;
import org.simpleframework.aop.annotation.Aspect2;
import org.simpleframework.aop.annotation.Order;
import org.simpleframework.aop.aspect.AspectInfo1;
import org.simpleframework.aop.aspect.AspectInfo2;
import org.simpleframework.aop.aspect.DefaultAspect;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.util.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @ClassName AspectWeaver
 * @Description AOP 2.0 的 AspectWeaver
 * @Author ma.kangkang
 * @Date 2020/11/17 20:53
 **/
public class AspectWeaver2 {
    private BeanContainer beanContainer;

    // 因为容器是绝对单例的，所以直接用无参构造方法获取容器实例
    public AspectWeaver2(){
        this.beanContainer = BeanContainer.instance();
    }

    // 真正实现AOP的方法
    public void doAop(){
        // 1、获取所有的切面类（从容器中获取被 Aspect 标签标记的所有的类）
        Set<Class<?>> aspectSet = beanContainer.getClassesByAnnotation(Aspect2.class);
        if (ValidationUtil.isEmpty(aspectSet)){return;}
        // 2、拼装 AspectInfoList
        List<AspectInfo2> aspectInfoList = packAspectInfoList(aspectSet);
        // 3、遍历容器里的类
        Set<Class<?>> classSet = beanContainer.getClasses();
        for (Class<?> targetClass : classSet){
            // 判断是否是 Aspect2 标签类
            if (targetClass.isAnnotationPresent(Aspect2.class)){
                continue;
            }
            // 4、粗筛符合条件的 Aspect
            // 为特定的类去初筛
            List<AspectInfo2> roughMatchedAspectList = collectRoughMatchedAspectInfoListForSpecificClass(aspectInfoList,targetClass);
            // 5、尝试进行 Aspect 的织入
            wrapIfNecessary(roughMatchedAspectList,targetClass);

        }

    }

    private void wrapIfNecessary(List<AspectInfo2> roughMatchedAspectList, Class<?> targetClass) {
    }

    private List<AspectInfo2> collectRoughMatchedAspectInfoListForSpecificClass(List<AspectInfo2> aspectInfoList, Class<?> targetClass) {
        List<AspectInfo2> roughMatchedAspectInfoList = new ArrayList<>();

        // 粗筛
        for (AspectInfo2 aspectInfo : aspectInfoList){
            if (aspectInfo.getPointCutLocator().roughMatches(targetClass)) {
                roughMatchedAspectInfoList.add(aspectInfo);
            }
        }
        return roughMatchedAspectInfoList;
    }

    private List<AspectInfo2> packAspectInfoList(Set<Class<?>> aspectSet) {
        List<AspectInfo2> aspectInfoList = new ArrayList<>();
        return aspectInfoList;
    }


}
