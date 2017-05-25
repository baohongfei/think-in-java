package com.baohongfei.dp.decorator;

/**
 * Created by terry on 25/05/17.
 */
public class ConcreteDecoratorB extends Decorator {

    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    public void methodB(){
        System.out.println("被装饰器B扩展的功能");
    }

    public void method(){
        System.out.println("针对该方法加一层B包装");
        super.method();
        System.out.println("B包装结束");
    }
}
