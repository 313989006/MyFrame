package demo.generic;

import lombok.Data;

/**
 * @ClassName GenericClassExample
 * @Description 泛型类
 * @Author ma.kangkang
 * @Date 2020/11/1 10:42
 **/
@Data
public class GenericClassExample<T> {

    // member这个成员变量的类型为T，T的类型是由外部指定
    private T member;

    // 泛型构造方法形参member的类型为T，T的类型是由外部指定
    public GenericClassExample(T member){
        this.member = member;
    }

    public T doSomeThing(T target){
        return target;
    }

    // 泛型方法
    public static <E> void printArray(E[] array){
        for (E e : array) {
            System.out.printf("%s",e);
            System.out.printf(" ");
        }
    }


}
