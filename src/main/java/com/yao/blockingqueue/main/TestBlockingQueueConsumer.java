package com.yao.blockingqueue.main;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Calm on 2018/8/13
 */
public class TestBlockingQueueConsumer implements Runnable{
    BlockingQueue<String> queue;
    Random random = new Random();

    public TestBlockingQueueConsumer(BlockingQueue<String> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(random.nextInt(10));
            System.out.println(Thread.currentThread().getName() + "trying...");
            String temp = queue.take();
            System.out.println(Thread.currentThread().getName() + "get a job" + temp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

