package com.yao.blockingqueue.customer;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Calm on 2018/8/13
 */
public class Consumer implements Runnable{
    BlockingQueue<String> queue;
    public Consumer(BlockingQueue<String> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        String consumer = Thread.currentThread().getName();
        System.out.println(consumer);
        try {
            String temp = queue.take();//如果队列为空,会阻塞当前线程

            System.out.println(consumer + "get a product:" + temp);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
