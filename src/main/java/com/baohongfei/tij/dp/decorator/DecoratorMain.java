package com.baohongfei.tij.dp.decorator;

/**
 *
 * @see http://www.cnblogs.com/zuoxiaolong/p/pattern11.html
 *
 * Created by terry on 25/05/17.
 */
public class DecoratorMain {
    public static void main(String[] args) {
        IComponent IComponent = new ComponentImpl();
        DecoratorImplA decoratorImplA = new DecoratorImplA(IComponent);//装饰成A
        DecoratorImplB decoratorImplB = new DecoratorImplB(decoratorImplA);//装饰成B
        decoratorImplB.method();
    }
}
