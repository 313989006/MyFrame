package org.simpleframework.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 表示作用于类上
@Target(ElementType.TYPE)
// 由于程序在运行时要通过反射获取注解信息，所以将注解的生命周期设置为运行时
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
}
