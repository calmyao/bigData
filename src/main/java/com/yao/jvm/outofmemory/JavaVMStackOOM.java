package com.yao.jvm.outofmemory;

/**
 * 线程导致内存溢出异常
 * VM Args:-Xss2M(这时候不妨设置大些)
 * 容易导致系统假死
 * Created by Calm on 2018/11/7
 */
public class JavaVMStackOOM {
    private void dontStop(){
        while (true){

        }
    }

    public void stackLeakByThread(){
        while (true){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
