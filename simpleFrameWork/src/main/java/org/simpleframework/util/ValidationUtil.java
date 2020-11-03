package org.simpleframework.util;

import java.util.Collection;
import java.util.Map;

/**
 * @ClassName ValidationUtil
 * @Description 判空的工具类,Util类的方法一般都是 static
 * @Author ma.kangkang
 * @Date 2020/11/3 9:54
 **/
public class ValidationUtil {

    /**
    * @Description: 判断集合是否为空
    * @Param:
    * @return:
    */
    public static boolean isEmpty(Collection obj){
        return obj == null || obj.isEmpty();
    }

    public static boolean isEmpty(String obj){
        return obj == null || "".equals(obj);
    }

    /**
    * @Description: 判断Array是否为空
    * @Param:
    * @return:
    */
    public static boolean isEmpty(Object[] obj){
        return obj == null || obj.length == 0;
    }

    /**
     * @Description: 判断Map是否为空
     * @Param:
     * @return:
     */
    public static boolean isEmpty(Map<?,?> obj){
        return obj == null || obj.isEmpty();
    }
}
