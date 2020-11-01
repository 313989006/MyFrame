package org.simpleframework.util;

import javafx.scene.effect.SepiaTone;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName ClassUtil
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/11/1 15:41
 **/
@Slf4j
public class ClassUtil {

    public static final String FILE_PROTOCAL = "file";

    /**
    * @Description: 获取包下所有类集合
    * @Param: packageName：包名
    * @return:  类集合
    * @Author: ma.kangkang
    * @Date: 2020/11/1 
    */ 
    public static Set<Class<?>> extractPackageClass(String packageName){

        // 1、获取到类的加载器
        ClassLoader classLoader = getClassLoader();
        // 2、通过类加载器获取到加载的资源
        //  getResource方法，参数是用/隔开的路径，所以先将 点 转成 /
        packageName = packageName.replace(".","/");
        URL url = classLoader.getResource(packageName);
        if (url == null){
            log.warn("从package：{} 获取不到任何东西",packageName);
            return null;
        }
        // 3、根据不同的资源类型，采用不同的方式获取资源的集合
        Set<Class<?>> classeSet = null;
        // 获取协议
        url.getProtocol();
        // 协议是 file 的话，继续处理
        if (url.getProtocol().equalsIgnoreCase(FILE_PROTOCAL)){
            classeSet = new HashSet<Class<?>>();
            File packageDirectory = new File(url.getPath());

            extractClassFile(classeSet,packageDirectory,packageName);

        }

        return classeSet;
    }

    /**
    * @Description: 递归获取目标 package 里面的所有class文件（包括子package里的class文件）
     *
    * @Param: emptyClasseSet ： 装载目标类的集合
    * @Param: fileSource ： 文件或者目录
    * @Param: packageName ： 包名
    * @return: 类集合
    * @Author: ma.kangkang
    * @Date: 2020/11/1
    */
    private static void extractClassFile(Set<Class<?>> emptyClasseSet, File fileSource, String packageName) {
        // 如果 fileSource 不是文件夹，直接返回
        if (!fileSource.isDirectory()){
            return;
        }
        // 如果是一个文件夹，则调用其listFiles方法获取文件夹下的文件或文件夹
        File[] files = fileSource.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()){
                    return true;
                } else {
                    // 获取文件的绝对值路径
                    String absolutePath = file.getAbsolutePath();
                    if (absolutePath.endsWith(".class")){
                        // 如果是class文件，则直接加载
                        addToClassSet(absolutePath);
                    }
                }
                return false;
            }

            // 根据 class 文件的绝对值路径，获取并生成 class 对象，并放入 set 中
            private void addToClassSet(String absolutePath) {
                // 1、从class文件的绝对值路径里提取出包含了package的类名
                // 如G:\myGitHubProject\MyFrame\simpleFrameWork\target\classes\com\mxk\entity\dto\MainPageInfoDto.class
                // 要弄成com.mxk.entity.dto.MainPageInfoDto.java
//                packageName = packageName.replace("/",".");
                absolutePath = absolutePath.replace(File.separator,".");
                String className = absolutePath.substring(absolutePath.indexOf(packageName.replace("/",".")));
                className = className.substring(0, className.lastIndexOf("."));

                // 2、通过反射机制获取对应的Class对象并加入到classSet里
                Class<?> targetClass = loadClass(className);
                emptyClasseSet.add(targetClass);
            }
        });

        if (files != null){
            for (File file :files) {
                // 递归调用
                extractClassFile(emptyClasseSet,file,packageName);
            }
        }
    }

    /**
    * @Description: 获取class对象
    * @Param: className class全名 = package + 类名
    * @return: Class
    * @Author: ma.kangkang
    * @Date: 2020/11/1
    */
    public static Class<?> loadClass(String className){
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("load class error {}",e);
            throw new RuntimeException(e);
        }
    }

    /**
    * @Description: 获取 ClassLoader 实例
    * @Param:
    * @return:  当前 ClassLoader
    * @Author: ma.kangkang
    * @Date: 2020/11/1
    */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    public static void main(String[] args) {
        extractPackageClass("com.mxk.entity");
    }
}
