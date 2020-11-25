package org.simpleframework.aop.aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.simpleframework.aop.PointCutLocator;

/**
 * @ClassName AspectInfo
 * @Description 封装 DefaultAspect 以及 Order 的属性值
 * @Author ma.kangkang
 * @Date 2020/11/17 14:27
 **/
@AllArgsConstructor
@Getter
public class AspectInfo1 {

    // AOP 1.0
    private int orderIndex;

    private DefaultAspect aspectObject;

    // 此处可以用注解（@AllArgsConstructor）实现
//    public AspectInfo(int orderIndex, DefaultAspect aspectObject) {
//        this.orderIndex = orderIndex;
//        this.aspectObject = aspectObject;
//    }
}
