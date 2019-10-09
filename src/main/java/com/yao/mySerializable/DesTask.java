package com.yao.mySerializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *从磁盘中反序列化对象
 * Created by Calm on 2018/11/22
 */
public class DesTask {

    public static void main(String[] args) throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D://tasks"));
        ExecutorService pool = Executors.newCachedThreadPool();
        Task t = (Task) ois.readObject();
        pool.execute(t);
        ois.close();
        pool.shutdown();
    }
}
