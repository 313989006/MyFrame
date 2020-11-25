//package org.simpleframework.aop.aspect;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
////import org.simpleframework.aop.AspectExcutor1;
//import org.simpleframework.aop.mock.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @ClassName AspectExcutorTest
// * @Description TODO
// * @Author ma.kangkang
// * @Date 2020/11/17 14:43
// **/
//public class AspectExcutorTest {
//
//    @DisplayName("Aspect 排序：sortedAspectList")
//    @Test
//    public void sortTest(){
//        List<AspectInfo1> aspectInfoList = new ArrayList<>();
//        aspectInfoList.add(new AspectInfo1(3,new Mock1()));
//        aspectInfoList.add(new AspectInfo1(5,new Mock2()));
//        aspectInfoList.add(new AspectInfo1(2,new Mock3()));
//        aspectInfoList.add(new AspectInfo1(4,new Mock4()));
//        aspectInfoList.add(new AspectInfo1(1,new Mock5()));
//
//        AspectExcutor1 aspectExcutor = new AspectExcutor1(AspectExcutor1.class,aspectInfoList);
//
//        List<AspectInfo1> sortedaAspectInfoList = aspectExcutor.getSortedAspectInfoList();
//        for (AspectInfo1 aspectInfo : sortedaAspectInfoList) {
//            System.out.println(aspectInfo.getAspectObject().getClass().getName() + ":" + aspectInfo.getOrderIndex());
//        }
//
//    }
//}
