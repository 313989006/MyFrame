package demo.generic;

/**
 * @ClassName GenericFactory
 * @Description 泛型接口
 * @Author ma.kangkang
 * @Date 2020/11/1 11:07
 **/
public interface GenericFactory<T,N> {

    T nextObject();

    N nextNumber();
}
