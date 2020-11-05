package org.simpleframework.inject;

import com.mxk.controller.MainPageController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simpleframework.core.BeanContainer;

/**
 * @ClassName DependencyInjectorTest
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/5 14:40
 **/
public class DependencyInjectorTest {

    @DisplayName("依赖注入IOC")
    @Test
    public void dooIocTest() throws InstantiationException, IllegalAccessException {
        // 获取 IOC 容器实例
        BeanContainer beanContainer = BeanContainer.instance();
        beanContainer.loadBeans("com.mxk");
        // 判断 beanContainer 是否被加载
        Assertions.assertEquals(true,beanContainer.isLoaded());
        // 获取 MainPageController 对应的bean实例
        MainPageController mainPageController = (MainPageController)beanContainer.getBeanByClass(MainPageController.class);

        Assertions.assertEquals(true,mainPageController instanceof MainPageController);

        // 判断headLineShppCategoryService 是被被注入
        Assertions.assertEquals(null,mainPageController.getHeadLineShppCategoryService());


        new DependencyInjector().doIOC();
        Assertions.assertNotEquals(null,mainPageController.getHeadLineShppCategoryService());
    }
}
