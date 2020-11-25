package org.simpleframework.aop.annotation;

import java.lang.annotation.*;

/**
 * AOP 2.0 的 Aspect 注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect2 {

    /**
     * AOP 2.0 ,先注释 1.0 的属性
     */
    String pointcut();

}
