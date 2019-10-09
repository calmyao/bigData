package com.yao.blockingqueue.main;

import com.yao.blockingqueue.customer.Consumer;
import com.yao.blockingqueue.producer.Producer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Calm on 2018/8/13
 */
public class Test {
    public static void main(String[] args) throws Exception{
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(2);
        //BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        //不设置的话,LinkedBlockingQueue默认大小为Integer.MAX_VALUE
        //BlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);
        Consumer consumer = new Consumer(queue);
        Producer producer = new Producer(queue);
        for(int i = 0;i<3;i++){
            new Thread(producer,"Producer"+(i+1)).start();
        }
        for (int i = 0;i<5;i++){
            new Thread(consumer,"Consumer" + (i +1)).start();
        }
        new Thread(producer,"Producer" + "5").start();
    }

}
