package demo.generic;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName GenericDemo
 * @Description 泛型的demo
 * @Author ma.kangkang
 * @Date 2020/11/1 10:39
 **/
public class GenericDemo {

    public static void handleMember(GenericClassExample<Integer> integerGenericClassExample){
        Integer result = 111 + integerGenericClassExample.getMember();
        System.out.println("result : " + result);
    }



    public static void main(String[] args) {

//        // 最开始的demo，强转报错
//        List list = new LinkedList();
//        list.add("words");
//        list.add(111);
//
//        for (int i = 0; i < list.size() ; i++) {
//            String item = (String) list.get(i);
//            System.out.println(item);
//        }
        // 泛型类
        GenericClassExample<String> stringExample = new GenericClassExample<String>("abc");
        GenericClassExample<Integer> integerExample = new GenericClassExample<Integer>(123);

        // 打印结果：
        // class java.lang.String
        // class java.lang.Integer
//        System.out.println(stringExample.getMember().getClass());
//        System.out.println(integerExample.getMember().getClass());

        // 这里验证：泛型类的类型约束只在编译时有效
        // 打印结果：
        // class demo.generic.GenericClassExample
        // class demo.generic.GenericClassExample
        System.out.println(stringExample.getClass());
        System.out.println(integerExample.getClass());
        // 继续验证javac 编译后的 GenericDemo.class 文件，查看后里面的内容是
        // GenericClassExample stringExample = new GenericClassExample("abc");
        // GenericClassExample integerExample = new GenericClassExample(123);


        handleMember(integerExample);


        // 泛型方法可以传入与泛型类不一样的实参，比如stringExample的实参是String，printArray方法传入的是Integer数组
        // 说明：GenericClassExample<T> 中的 T 是独立于 GenericClassExample 存在的，泛型类里的其他方法是受制于T的
        Integer[] integers = {1,2,3,4,5,6};
        Double[] doubles = {1.1,2.2,3.3,4.4,5.5,6.6};
        Character[] characters = {'A','B','C'};
        stringExample.printArray(integers);
        stringExample.printArray(doubles);
        stringExample.printArray(characters);


    }
}
