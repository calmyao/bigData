package com.yao.pool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by Calm on 2018/7/11
 * 列出并发包中的各种线程池
 */
public class ExecutorDemo {
    public static void main(String[] args) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        int cpuNum = Runtime.getRuntime().availableProcessors();
        System.out.println(cpuNum);
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(cpuNum);
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(8);

        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();

    }
}
