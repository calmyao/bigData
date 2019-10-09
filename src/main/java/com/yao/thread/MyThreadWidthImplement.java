package com.yao.thread;

/**
 * Created by Calm on 2018/7/10
 */
public class MyThreadWidthImplement implements Runnable {
     int x;

     public MyThreadWidthImplement(int x){
         this.x = x;
     }


    @Override
    public void run() {
        String tname = Thread.currentThread().getName();
        System.out.println("线程" + tname + "run方法调用");
        for(int i = 0; i < 10 ; i ++){
            System.out.println(x);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyThreadWidthImplement(1),"thread-1");
        Thread thread2 = new Thread(new MyThreadWidthImplement(2),"thread-2");
        thread1.start();
        thread2.start();
        //调用run和调用start的区别,直接调用run则都运行在main线程中
    }
}
