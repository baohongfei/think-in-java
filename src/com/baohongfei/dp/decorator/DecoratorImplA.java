package com.baohongfei.dp.decorator;

/**
 * Created by terry on 25/05/17.
 */
public class DecoratorImplA extends AbstractDecorator {

    public DecoratorImplA(IComponent component) {
        super(component);
    }

    public void method(){
        System.out.println(this.getClass().getSimpleName()+"."+new Exception().getStackTrace()[0].getMethodName()+" start");
        super.method();
        System.out.println(this.getClass().getSimpleName()+"."+new Exception().getStackTrace()[0].getMethodName()+" end");
    }
}
