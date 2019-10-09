package com.yao.mq.topic;

/**
 * Created by Calm on 2018/11/14
 */
public class ConsumerTest implements Runnable{
    static  Thread t1 = null;

    public static void main(String[] args) throws InterruptedException{
        t1 = new Thread(new ConsumerTest());
        t1.setDaemon(false);
        t1.start();

    }



    @Override
    public void run() {

    }
}
