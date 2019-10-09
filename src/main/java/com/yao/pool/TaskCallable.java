package com.yao.pool;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by Calm on 2018/7/11
 */
public class TaskCallable implements Callable<String> {
    private int s;
    Random r = new Random();
    public TaskCallable(int s){
        this.s = s;
    }

    @Override
    public String call() throws Exception {
        String name = Thread.currentThread().getName();
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(name + "启动时间: " + currentTimeMillis / 1000);
        int rint = r.nextInt(3);
        Thread.sleep(rint * 1000);
        System.out.println(name + "is working" + s);
        return s + "";
    }
}
