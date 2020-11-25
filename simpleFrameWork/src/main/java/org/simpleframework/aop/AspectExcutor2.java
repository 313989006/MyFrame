package org.simpleframework.aop;

import lombok.Getter;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.simpleframework.aop.aspect.AspectInfo1;
import org.simpleframework.aop.aspect.AspectInfo2;
import org.simpleframework.util.ValidationUtil;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName AspectExcutor
 * @Description 创建 MethodInterceptor 的实现类：AspectExcutor
 *
 * 针对每个被代理的对象进行方法的拦截
 *
 * 使用的是 cgLib 动态代理，所以需要实现 MethodInterceptor
 **/
public class AspectExcutor2 implements MethodInterceptor {

    // 被代理的类
    private Class<?> targetClass;

    // 排好序的 Aspect 列表
    @Getter
    private List<AspectInfo2> sortedAspectInfoList;

    public AspectExcutor2(Class<?> targetClass, List<AspectInfo2> aspectInfoList) {
        this.targetClass = targetClass;
        // aspectInfoList 的排序
        this.sortedAspectInfoList = sortAspectInfoList(aspectInfoList);
    }

    /**
    * 定义横切逻辑的执行顺序
    */
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object returnValue = null;

        // AOP 2.0 传入 method，对 sortedAspectInfoList 进行精筛
        // 剩下的 sortedAspectInfoList 跟之前的逻辑一样进行织入
        collectAccurateMatchedAspectList(method);

        if (ValidationUtil.isEmpty(sortedAspectInfoList)){
            // AOP 2.0修改的地方，即便没有织入的逻辑，也要保证被代理方法的执行
            returnValue = methodProxy.invokeSuper(proxy,args);
            return returnValue;
        }
        // 1、按照order 的顺序升序执行完所有Aspect 的 before 方法
        invokeBeforeAdvices(method,args);
        try {
            // 2、执行被代理类的方法
            // proxy ：动态代理对象实例
            // args：被代理方法对应的参数列表
            returnValue = methodProxy.invokeSuper(proxy,args);
            // 3、如果被代理方法正常返回，则按照 order 的顺序降序执行完所有 Aspect 的 afterReturning 方法
            // method：被代理的方法实例
            // args：被代理方法对应的参数列表
            // returnValue ：返回值
            returnValue = invokeAfterReturningAdvices(method,args,returnValue);
        } catch (Exception e) {
            // 4、如果被代理方法抛出异常，则按照 order 的顺序降序执行完所有 Aspect 的 afterThrowing 方法
            invokeAfterThrowingAdvices(method,args,e);
        }
        return returnValue;
    }

    private void collectAccurateMatchedAspectList(Method method) {
        if (ValidationUtil.isEmpty(sortedAspectInfoList)){return;}
        // 迭代器的方式去遍历
        Iterator<AspectInfo2> iterator = sortedAspectInfoList.iterator();
        while (iterator.hasNext()){
            AspectInfo2 aspectInfo = iterator.next();
            // 精准筛选
            if (!aspectInfo.getPointCutLocator().accurateMatches(method)) {
                iterator.remove();
            }
        }
    }


    // 1、按照order 的顺序升序执行完所有 Aspect 的 before 方法
    private void invokeBeforeAdvices(Method method, Object[] args) throws Throwable {
        for (AspectInfo2 aspectInfo : sortedAspectInfoList){
            aspectInfo.getAspectObject().before(targetClass,method,args);
        }
    }

    // 3、如果被代理方法正常返回，则按照 order 的顺序降序执行完所有 Aspect 的 afterReturning 方法
    private Object invokeAfterReturningAdvices(Method method, Object[] args, Object returnValue) throws Throwable {
        Object result = null;
        for (int i = sortedAspectInfoList.size() - 1; i >= 0 ; i--) {
            result = sortedAspectInfoList.get(i).getAspectObject().afterReturning(targetClass,method,args,returnValue);
        }
        return result;
    }

    // 4、如果被代理方法抛出异常，则按照 order 的顺序降序执行完所有 Aspect 的 afterThrowing 方法
    private void invokeAfterThrowingAdvices(Method method, Object[] args, Exception e) throws Throwable {
        for (int i = sortedAspectInfoList.size() - 1; i >= 0 ; i--) {
            sortedAspectInfoList.get(i).getAspectObject().afterThrowing(targetClass,method,args,e);
        }
    }

    /**
    * 按照 order 的值进行升序排序，确保 order 值小的 aspect 先被织入
    */
    private List<AspectInfo2> sortAspectInfoList(List<AspectInfo2> aspectInfoList) {
        // 匿名的Comparator 实例
        Collections.sort(aspectInfoList, new Comparator<AspectInfo2>() {
            @Override
            public int compare(AspectInfo2 o1, AspectInfo2 o2) {
                // 按照值的大小进行升序降序
                // return 0 ：表示 o1 和 o2 是相等的
                // return 大于0 ：表示 o1 大于 o2
                // return 小于0 ：表示 o1 小于 o2
                // 因为这里要按照 order 的值升序排列，所以这里返回 o1.getOrderIndex() - o2.getOrderIndex()
                return o1.getOrderIndex() - o2.getOrderIndex();
            }
        });
        return aspectInfoList;
    }
}
