package org.simpleframework.core;

import org.junit.jupiter.api.*;

/**
 * @ClassName BeanContainerTest
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/3 10:15
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BeanContainerTest {

    /**
    *  @BeforeAll @AfterAll 注解的意义
     * @ @BeforeAll注释的实例方法会在实例所有的测试用例之前调用，
     * @ @AfterAll注释的实例方法会在所有实例的测试用例之后调用。
     * 但如果@BeforeAll或@AfterAll要应用在实例方法上，需要在实例的类上添加注释@TestInstance。
    */

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

    @Order(1)
    @DisplayName("加载目标类及其实例到BeanContainer ： loadBeansTest")
    @Test
    public void loadBeansTest() throws InstantiationException, IllegalAccessException {
        Assertions.assertEquals(false,beanContainer.isLoaded());
        beanContainer.loadBeans("com.mxk");
        Assertions.assertEquals(6,beanContainer.size());
        Assertions.assertEquals(true,beanContainer.isLoaded());
    }

}
