package com.yao.blockingqueue.main;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Calm on 2018/8/13
 */
public class TestBlockingQueue {
    public static void main(String[] args) {

        BlockingQueue<String> queue = new LinkedBlockingQueue<>(2);
        //BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        //不设置的话,LinkedBlockingQueue默认大小为Integer.MAX_VALUE
        //BlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);
        TestBlockingQueueConsumer consumer = new TestBlockingQueueConsumer(queue);
        TestBlockingQueueProducer producer = new TestBlockingQueueProducer(queue);
        for (int i = 0;i < 3; i ++){
            new Thread(producer,"Producer" + (i + 1)).start();
        }
        for (int i = 0;i < 5;i++){
            new Thread(consumer,"Consumer" + (i + 1)).start();
        }
        new Thread(producer,"Producer" + (5)).start();
    }
    /**
     * 不应用线程池的缺点
     * 有些开发者图省事,遇到需要多线程处理的地方,直接new Thread(...).start(),对于一般场景
     * 是没问题的,但如果是在并发请求很高的情况下,就会有些隐患:
     * - 新建线程的开销.线程虽然比进程要轻量许多,但对于JVM来说,新建一个线程的
     *   代价还是挺大的,决不同于新建一个对象
     * - 资源消耗量.没有一个线程池来限制线程的数量,会导致线程的数量直接取决于应用的
     *   这样有潜在的线程数据巨大的可能,那么资源消耗量将是巨大的
     * 稳定性.当线程数量超过系统资源所能承受的程度,稳定性就会成问题
     *
     *
     *
     */
}
