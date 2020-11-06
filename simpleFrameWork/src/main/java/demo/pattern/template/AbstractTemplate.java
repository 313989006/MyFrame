package demo.pattern.template;

/**
 * @ClassName AbstractTemplate
 * @Description 模板方法模式
 * @Author ma.kangkang
 * @Date 2020/11/6 16:07
 **/
public abstract class AbstractTemplate {

    /**
    *  模板方法
    */
    public void templateMehod(){
        concreateMethod();
        hookMethod();
        abstractMethod();
    }

    // 具体方法 (是必须要实现的)
    public void concreateMethod(){
        System.out.println("模板里自带的实现方法，万年不变");
    }

    // 钩子方法（视情况实现的）
    protected void hookMethod(){}

    // 抽象方法
    public abstract void abstractMethod();

}
