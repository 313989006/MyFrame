
package org.simpleframework.aop;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;

/**
 * @ClassName PointCutLocator
 * @Description 解析 Aspect 表达式，并且定位被织入的目标
 * @Author ma.kangkang
 * @Date 2020/11/20 11:01
 **/
// 注意 AOP 1.0 的@Aspect注解类的属性是 Class<? extends Annotation> value();
// AOP 1.0 的@Aspect注解类的属性是 String pointcut();
// 先将其修改 为 String pointcut();
public class PointCutLocator {

    /**
    * Pointcut 解析器，直接给他赋值上AspectJ 的所有表达式，以便支持对众多表达式的解析
    */
    // getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution 根据特定的不同的语法树集合创建出对应的 PointcutParser 实例
    // 并给 PointcutParser 实例装配上当前线程的 ClassLoader 实例
    private PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(
            // getAllSupportedPointcutPrimitives：获取 AspectJ 默认的所有语法树
            PointcutParser.getAllSupportedPointcutPrimitives());

    /**
     * 表达式 解析器
     */
    private PointcutExpression pointcutExpression;

    /**
     * 通过构造方法，根据表达式解析出对应的 PointcutExpression 实例
     */
    public PointCutLocator(String expression){
        this.pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }

    /**
    *   判断传入的 Class 对象是否是 Aspect 的目标代理类，即匹配的 PointCut 表达式（初筛）
     * @param targetClass：目标代理类
     * @return 是否匹配
    */
    public boolean roughMatches(Class<?> targetClass){
        // couldMatchJoinPointsInType 比较坑，只会校验 within
        // 不能校验(execution，call，get，set)，面对无法校验的表达式，直接返回true
        return pointcutExpression.couldMatchJoinPointsInType(targetClass);
    }

    /**
     *   判断传入的 Method 对象是否是 Aspect 的目标代理方法，即匹配的 PointCut 表达式（精筛）
     * @param method：目标代理类的方法
     * @return 是否匹配
     */
    public boolean accurateMatches(Method method){
        ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
        if (shadowMatch.alwaysMatches()) {
            return true;
        }
        return false;
    }

}
