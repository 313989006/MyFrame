package org.simpleframework.inject.annotation;

import javax.xml.bind.Element;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @Description: 仅支持成员变量注入，目标对象是成员变量
* @Param:
* @return:
*/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {

    // 简单实现给一个空字符串的默认值
    String value() default "";
}
