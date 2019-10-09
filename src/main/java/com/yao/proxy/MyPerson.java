package com.yao.proxy;

/**
 * Created by Calm on 2018/11/22
 */
public class MyPerson implements PersonInterface{

    @Override
    public void doSomeThing() {
        System.out.println("MyPerson is doing its thing.....");
    }


    @Override
    public void saySomeThing() {
        System.out.println("MyPerson is saying its thing.....");
    }
}
