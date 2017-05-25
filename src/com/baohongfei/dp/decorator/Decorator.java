package com.baohongfei.dp.decorator;

/**
 * Created by terry on 25/05/17.
 */
public abstract class Decorator implements Component {
    protected Component component;

    public Decorator(Component component){
        super();
        this.component = component;
    }

    @Override
    public void method(){
        component.method();
    }
}
