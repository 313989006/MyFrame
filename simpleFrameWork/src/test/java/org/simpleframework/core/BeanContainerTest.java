package org.simpleframework.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @ClassName BeanContainerTest
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/3 10:15
 **/
public class BeanContainerTest {

    private static BeanContainer beanContainer;
    /** 
    * @Description: 初始化 bean实例 ，@BeforeAll ：保证框架在执行test之前，执行init，初始化beanContainer实例
    * @Param:  
    * @return: 
    */
    @BeforeAll
    static void init(){
        // 获取容器的单例实例
        beanContainer = BeanContainer.instance();
    }

    @Test
    public void loadBeansTest() throws InstantiationException, IllegalAccessException {
        Assertions.assertEquals(false,beanContainer.isLoaded());
        beanContainer.loadBeans("com.mxk");
        Assertions.assertEquals(6,beanContainer.size());
        Assertions.assertEquals(true,beanContainer.isLoaded());
    }


}
