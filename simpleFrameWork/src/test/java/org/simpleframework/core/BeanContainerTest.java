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
    * @Description: @BeforeAll
    * @Param:  
    * @return: 
    */
    @BeforeAll
    static void init(){
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
