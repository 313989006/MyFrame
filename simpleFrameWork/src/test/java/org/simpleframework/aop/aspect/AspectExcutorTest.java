package org.simpleframework.aop.aspect;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simpleframework.aop.AspectExcutor;
import org.simpleframework.aop.annotation.Aspect;
import org.simpleframework.aop.mock.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AspectExcutorTest
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/17 14:43
 **/
public class AspectExcutorTest {

    @DisplayName("Aspect 排序：sortedAspectList")
    @Test
    public void sortTest(){
        List<AspectInfo> aspectInfoList = new ArrayList<>();
        aspectInfoList.add(new AspectInfo(3,new Mock1()));
        aspectInfoList.add(new AspectInfo(5,new Mock2()));
        aspectInfoList.add(new AspectInfo(2,new Mock3()));
        aspectInfoList.add(new AspectInfo(4,new Mock4()));
        aspectInfoList.add(new AspectInfo(1,new Mock5()));

        AspectExcutor aspectExcutor = new AspectExcutor(AspectExcutor.class,aspectInfoList);

        List<AspectInfo> sortedaAspectInfoList = aspectExcutor.getSortedAspectInfoList();
        for (AspectInfo aspectInfo : sortedaAspectInfoList) {
            System.out.println(aspectInfo.getAspectObject().getClass().getName() + ":" + aspectInfo.getOrderIndex());
        }

    }
}
