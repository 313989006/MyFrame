package org.simpleframework.aop.aspect;

import com.mxk.controller.HeadLineController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simpleframework.aop.AspectWeaver;
import org.simpleframework.aop.annotation.Aspect;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.inject.DependencyInjector;

import java.util.Set;

/**
 * @ClassName AspectWeaverTest
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/19 11:29
 **/
public class AspectWeaverTest {

    @DisplayName("织入通用逻辑测试： doAop")
    @Test
    public void doAopTest() throws InstantiationException, IllegalAccessException {
        BeanContainer beanContainer = BeanContainer.instance();
        beanContainer.loadBeans("com.mxk");

        // bean 先被织入横切逻辑才供外部去使用
        new AspectWeaver().doAop();

        // 依赖注入
        new DependencyInjector().doIOC();

        // 验证bean 的方法是否被织入横切逻辑
        HeadLineController headLineController = (HeadLineController)beanContainer.getBeanByClass(HeadLineController.class);
        headLineController.insert(null,null);

    }
}
