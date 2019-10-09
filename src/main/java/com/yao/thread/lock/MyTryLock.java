package com.yao.thread.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Calm on 2018/7/11
 * 观察现象:一个线程获得锁后,另一个线程取不到锁,不会一直等待
 */
public class MyTryLock {
    private static ArrayList<Integer> arrayList = new ArrayList<>();
    static Lock lock = new ReentrantLock();//注意这个地方

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                Thread thread = Thread.currentThread();
                boolean tryLock = lock.tryLock();
                System.out.println(thread.getName() + " " + tryLock);
                if(tryLock){
                    try {
                        System.out.println(thread.getName() + "得到锁");
                        for(int i = 0;i<5;i++){
                            arrayList.add(i);
                        }
                        Thread.sleep(1000);
                    }catch (Exception e){
                        //TODO
                    }finally {
                        System.out.println(thread.getName() + "释放锁");
                        lock.unlock();
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                Thread thread = Thread.currentThread();
                boolean tryLock = lock.tryLock();
                System.out.println(thread.getName() + "" + tryLock);
                if(tryLock){
                    try {
                        System.out.println(thread.getName() + "得到了锁");
                        for(int i = 0;i < 5; i++){
                            arrayList.add(i);
                        }
                    }catch (Exception e){
                        //TODO:handle exception
                    }finally {
                        System.out.println(thread.getName() + "释放锁");
                        lock.unlock();
                    }
                }
            }
        }.start();



    }


}
