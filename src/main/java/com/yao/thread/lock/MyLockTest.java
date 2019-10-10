package com.yao.thread.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Calm on 2018/7/10
 */
public class MyLockTest {
    private static ArrayList<Integer> arrayList = new ArrayList<>();
    static Lock lock = new ReentrantLock();//注意这个地方

    public static<E> void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                Thread thread = Thread.currentThread();

                lock.lock();
                try {
                    System.out.println(thread.getName() + "得到了锁");
                    for(int i = 0;i < 5; i ++){
                        arrayList.add(i);
                    }
                }catch (Exception e){

                }finally {
                    System.out.println(thread.getName() + "释放了锁");
                    lock.unlock();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                Thread thread = Thread.currentThread();
                try {
                    System.out.println(thread.getName() + "得到了锁");
                    for (int i = 0;i < 5; i++){
                        arrayList.add(i);
                    }
                }catch (Exception e){

                }finally {
                    System.out.println(thread.getName() + "释放了锁");
                    lock.unlock();
                }
            }
        }.start();
    }

}
