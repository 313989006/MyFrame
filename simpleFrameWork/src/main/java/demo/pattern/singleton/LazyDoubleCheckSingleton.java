package demo.pattern.singleton;

/**
 * @ClassName LazyDoubleCheckSingleton
 * @Description 单例模式---懒汉式单例模式：基于双重检查锁实现
 * 确保了在多线程下能够安全获取单实例的需求
 * @Author ma.kangkang
 * @Date 2020/11/2 10:16
 **/
public class LazyDoubleCheckSingleton {

    // 定义成员变量，作为单例返回给用户。不同的是：该单例不是在类初始化的时候进行初始化的，而是在第一次使用的时候（调用getInstance）才进行初始化

    // volatile 关键字：类型修饰符，被用来修饰：被不同线程访问和修改的变量 可以被 异步的线程 所修改
    // 如果 不加 volatile 修饰，synchronized 里面代码的可能先执行第三步（指向内存地址），后执行第二步（初始化对象），就会导致 对象还没被初始化好，就被分配了内存地址
    private volatile static LazyDoubleCheckSingleton instance;

    // 构造方法设置为私有的，以防止客户端通过调用构造函数来创建出实例
    public LazyDoubleCheckSingleton() {
    }

    /**
    * @Description: 为什么 instance 要用 volatile 修饰？
    * 三个线程，A，B，C，如果 instance 不用 volatile 修饰，如果 A 执行到 synchronized 获取锁，可能会先执行 instance = memory;而未初始化对象
     * C 线程执行的时候，第一次检测 instance 的时候，instance !- null,就不会执行 synchronized ，就直接返回了一个未初始化的对象，这时就是一个大坑
    * @Param:
    * @return:
    * @Author: ma.kangkang
    * @Date: 2020/11/2
    */
    public static LazyDoubleCheckSingleton getInstance(){

        // 两次 if  为了确保线程安全
        //第一次检测
        if (instance == null){
            // 同步
            // 比如两个线程A和B，当A执行到 synchronized 的时候，就会上一个同步锁，执行完之前，B执行到 synchronized 会被锁住，等待A执行完并释放锁
            //
            synchronized (LazyDoubleCheckSingleton.class){
                if (instance == null){
                    // new LazyDoubleCheckSingleton() 三步走,如果不用 volatile 修饰的话，可能会先执行第三步，然后再执行第二步
                    // 因为第二步和第三步不存在依赖关系，顺序可以被程序执行器任意调整的
                    // 1、分配对象内存空间
                    // memory = allocate();
                    // 2、初始化对象
                    // instance(memory);
                    // 3、设置 instance 指向刚分配的内存地址，此时 instance != null
                    // instance = memory;
                    instance = new LazyDoubleCheckSingleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        // 获取两次发现获取的是同一个实例对象
        //demo.pattern.singleton.LazyDoubleCheckSingleton@2503dbd3
        //demo.pattern.singleton.LazyDoubleCheckSingleton@2503dbd3
        System.out.println(LazyDoubleCheckSingleton.getInstance());
        System.out.println(LazyDoubleCheckSingleton.getInstance());
    }

    /**
     *   instance= new LazyDoubleCheckSingleton()并不是一个原子操作，其实际上可以抽象为下面几条JVM指令：
     * memory =allocate();    //1：分配对象的内存空间
     * ctorInstance(memory);  //2：初始化对象
     * instance =memory;     //3：设置instance指向刚分配的内存地址
     * 上面操作2依赖于操作1，但是操作3并不依赖于操作2。所以JVM是可以针对它们进行指令的优化重排序的，经过重排序后如下：
     *
     * memory =allocate();    //1：分配对象的内存空间
     * instance =memory;     //3：instance指向刚分配的内存地址，此时对象还未初始化
     * ctorInstance(memory);  //2：初始化对象
     * 指令重排之后，instance指向分配好的内存放在了前面，而这段内存的初始化被排在了后面。
     * 在线程A执行这段赋值语句，在初始化分配对象之前就已经将其赋值给instance引用，恰好另一个线程进入方法判断instance引用不为null，然后就将其返回使用，导致出错。
     *
     * 解决办法:
     *  用volatile关键字修饰instance变量，使得instance在读、写操作前后都会插入内存屏障，避免重排序。
     */
}
