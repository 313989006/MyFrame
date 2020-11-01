package org.simpleframework.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName Component
 * @Description
 * 既然可以用Component表示，为什么还要用Controller、Service、Repository?
 *  答案：能够在逻辑含义上划分出不同的主键模块，及针对不同的使用场景，采用特定功能化的注解主键，
 *      并在需要获取比如Controller层的类的时候，能够依据特定的标签，直接筛选出来
 * @Author ma.kangkang
 * @Date 2020/11/1 15:30
 **/
// 表示作用于类上
@Target(ElementType.TYPE)
// 由于程序在运行时要通过反射获取注解信息，所以将注解的生命周期设置为运行时
@Retention(RetentionPolicy.RUNTIME)
// 被标记的类代表着是一个通用的、被容器管理的主键，因此可以代表 Controller、Service、Repository
public @interface Component {
}
