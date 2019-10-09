package com.yao.jvm.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Calm on 2018/11/5
 */
public class TestThread {
    /***
     * 死循环演示
     *
     */
    public static void createBusyThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("createBusyThread");
                while (true)
                    ;
            }
        },"testBusyThread");
        thread.start();
    }

    /**
     * 线程锁等待
     * @param lock
     */
    public static void createLockThread(final Object lock){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("createLockThread");
                synchronized (lock){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"testLockThread");
        thread.start();
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();
        Object object = new Object();
        createLockThread(object);

    }
}
