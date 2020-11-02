package org.simpleframework.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 获取Bean实例
 */
// 增加日志注解
@Slf4j
// 注解替换无参构造方法实现
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanContainer {

    /**
    * @Description: 1、定义私有的无参构造方法 // 这里使用注解进行替换
    * @Param:
    * @return:
    * @Author: ma.kangkang
    * @Date: 2020/11/2
    */
//    private BeanContainer(){
//
//    }

    /**
    * @Description: 2、定义私有的enum类型的Holder
    * @Param:
    * @return:
    * @Author: ma.kangkang
    * @Date: 2020/11/2
    */
    private enum ContainerHolder{
        // 枚举值，用来保存单例
        HOLDER;
        // 创建成员变量，定义为 private
        private BeanContainer instance;

        private ContainerHolder(){
            instance = new BeanContainer();
        }

    }

    /**
    * @Description: 3、提供一个供客户端获取单例的入口
    * @Param:
    * @return:
    * @Author: ma.kangkang
    * @Date: 2020/11/2
    */
    public BeanContainer instance(){
        return ContainerHolder.HOLDER.instance;
    }
}
