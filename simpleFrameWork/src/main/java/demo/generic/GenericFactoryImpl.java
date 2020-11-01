package demo.generic;

/**
 * @ClassName GenericFactoryImpl
 * @Description 泛型类实现泛型接口
 * @Author ma.kangkang
 * @Date 2020/11/1 11:15
 **/
public class GenericFactoryImpl<T,N> implements GenericFactory<T,N>{
    @Override
    public T nextObject() {
        return null;
    }

    @Override
    public N nextNumber() {
        return null;
    }
}
