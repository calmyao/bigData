package com.yao.thread.lock;

import org.omg.SendingContext.RunTime;

/**
 * Created by Calm on 2018/8/13
 */
public class Test {
    public static void main(String[] args) {
        int num = Runtime.getRuntime().availableProcessors();
        System.out.println(num);
    }
}
