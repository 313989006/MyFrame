package org.simpleframework.aop.annotation;

import javax.xml.bind.Element;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName Order
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/17 14:04
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Order {

    /**
     * 控制类的执行顺序，值越小 优先级越高
     */
    int value();
}
