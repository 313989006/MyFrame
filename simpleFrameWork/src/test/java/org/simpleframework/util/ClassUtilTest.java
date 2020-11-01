package org.simpleframework.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * @ClassName ClassUtiTest
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/1 17:15
 **/
public class ClassUtilTest {


    @DisplayName("提取目标方法：extractPackageClassTest")
    @Test
    public void extractPackageClassTest(){
        Set<Class<?>> classeSet = ClassUtil.extractPackageClass("com.mxk.entity.dto");
        Assertions.assertEquals(2,classeSet.size());

    }
}
