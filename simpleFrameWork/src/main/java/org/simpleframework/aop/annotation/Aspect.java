package org.simpleframework.aop.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    // 表示被 Aspect 标签标记的横切逻辑是会织入到被属性值里的注解标签标记的那些类里
    /**
    * 需要被织入横切逻辑的注解标签
    */
    Class<? extends Annotation> value();
}
