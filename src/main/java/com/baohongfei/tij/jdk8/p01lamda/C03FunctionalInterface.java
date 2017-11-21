package com.baohongfei.tij.jdk8.p01lamda;

/**
 * 2017/11/21 10:27 PM by hofer.bao
 */

@FunctionalInterface
interface MyInterface{
    void test();

    @Override
    String toString();
}

public class C03FunctionalInterface {

    public void myTest(MyInterface myInterface){
        System.out.println("111");
        myInterface.test();
        System.out.println("222");
    }


    public static void main(String[] args) {
        C03FunctionalInterface c03FunctionalInterface = new C03FunctionalInterface();

        c03FunctionalInterface.myTest(new MyInterface() {
            @Override
            public void test() {
                System.out.println("MyInterface");
            }
        });

        c03FunctionalInterface.myTest(() -> System.out.println("MyInterface"));

        MyInterface myInterface = () -> System.out.println("MyInterface");

        /**
         * Lambda表达式为Java添加了缺失的函数式编程特性，是我们能将函数当做一等公民看待
         *
         * 在将函数作为一等公民的语言中，Lambda表达式的类型是函数，
         * 在Java中，Lambda表达式是对象，他们必须依附于一类特别的对象类型：函数式接口（functional interface）
         */
        // class com.baohongfei.tij.jdk8.p01lamda.C03FunctionalInterface$$Lambda$2/381259350
        System.out.println(myInterface.getClass());
        System.out.println(myInterface.getClass().getSuperclass());
        System.out.println(myInterface.getClass().getInterfaces()[0]);
    }

}
