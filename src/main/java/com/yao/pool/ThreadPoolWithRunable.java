package com.yao.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Calm on 2018/7/11
 */
public class ThreadPoolWithRunable {
    /**
     * 通过线程池执行线程
     * @param args
     */
    public static void main(String[] args) {
        //创建一个线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        for(int i =1;i<5;i++){
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("thread name: " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });
        }
        pool.shutdown();


    }
}
