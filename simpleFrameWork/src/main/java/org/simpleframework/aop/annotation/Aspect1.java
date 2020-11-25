package org.simpleframework.aop.annotation;

import java.lang.annotation.*;

/**
* AOP 1.0 的 Aspect 注解
*/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect1 {

    // 表示被 Aspect 标签标记的横切逻辑是会织入到被属性值里的注解标签标记的那些类里
    /**
    * AOP 1.0 需要被织入横切逻辑的注解标签
    */
    Class<? extends Annotation> value();

}
