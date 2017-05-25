package com.baohongfei.dp.decorator;

/**
 * Created by terry on 25/05/17.
 */
public class ConcreteComponent implements Component{

    @Override
    public void method() {
        System.out.println("原来的方法");
    }
}
