package com.baohongfei.dp.decorator;

/**
 * Created by terry on 25/05/17.
 */
public class ComponentImpl implements IComponent {

    @Override
    public void method() {
        System.out.println(this.getClass().getSimpleName()+"."+new Exception().getStackTrace()[0].getMethodName()+" executed");
    }
}
