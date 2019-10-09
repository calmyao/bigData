package com.yao.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Calm on 2018/11/22
 */
public class ListDemo {

    public static void main(String[] args) throws Exception{
        final List list = new ArrayList();
        //这是被代理的
        Object oo = Proxy.newProxyInstance(List.class.getClassLoader(), list.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.err.println("加入一个对象:");
                Object returnValue = method.invoke(list, args);//反射
                System.err.println("加入完成...");
                if (method.getName().equals("size")) {
                    return 100;
                }
                return returnValue;
            }
        });
        List list2 = (List) oo;
        list2.add("aaa");
        list2.add("bbb");

        System.err.println("size:" + list2.size() + "," + list.size());
        //为什么调用3次?
        //list2.size()这个也调用


    }
}
