package org.simpleframework.util;

import org.jcp.xml.dsig.internal.dom.DOMUtils;

/**
 * @ClassName ConverterUtil
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/26 15:44
 **/
public class ConverterUtil {

    /**
    * 返回基本数据类型的空值
     * 需要特殊处理的基本类型 即 int/double/short/long/byte/float/boolean
     * @param type 参数类型
     * @return 对应的空值
    */
    public static Object primitiveNull(Class<?> type) {
        if (type == int.class || type == double.class ||
                type == short.class || type == long.class ||
                type == byte.class || type == float.class
        ){
            return 0;
        } else if (type == boolean.class){
            return false;
        }
        return null;
    }

    /**
     * String 类型转换成对应的参数类型
     * @param type 参数类型
     * @param requestValue 值
     * @return 转换后的 Object
     */
    public static Object convert(Class<?> type, String requestValue) {
        if (isPrimitive(type)){
            if (ValidationUtil.isEmpty(requestValue)) {
                return primitiveNull(type);
            }
            if (type.equals(int.class) || type.equals(Integer.class)){
                return Integer.parseInt(requestValue);
            } else if (type.equals(String.class)){
                return requestValue;
            } else if (type.equals(String.class) || type.equals(Integer.class)){
                return requestValue;
            } else if (type.equals(Double.class) || type.equals(double.class)){
                return Double.parseDouble(requestValue);
            } else if (type.equals(Float.class) || type.equals(float.class)){
                return Float.parseFloat(requestValue);
            } else if (type.equals(Long.class) || type.equals(long.class)){
                return Long.parseLong(requestValue);
            } else if (type.equals(Boolean.class) || type.equals(boolean.class)){
                return Boolean.parseBoolean(requestValue);
            } else if (type.equals(Short.class) || type.equals(short.class)){
                return Short.parseShort(requestValue);
            } else if (type.equals(Byte.class) || type.equals(byte.class)){
                return Byte.parseByte(requestValue);
            }
        } else {
            throw new RuntimeException("could not support non primitive type conversion yet ");
        }
        return requestValue;
    }

    /**
    * 判定是否基本数据类型（包括包装类以及 String）
     * @param type 参数类型
     * @return 是否为基本数据类型
    */
    private static boolean isPrimitive(Class<?> type) {
        return type == Boolean.class ||
                type == boolean.class ||
                type == Double.class ||
                type == double.class ||
                type == Float.class ||
                type == float.class ||
                type == Short.class ||
                type == short.class ||
                type == Integer.class ||
                type == int.class ||
                type == Long.class ||
                type == long.class ||
                type == String.class ||
                type == Byte.class ||
                type == byte.class ||
                type == Character.class ||
                type == char.class;

    }
}
