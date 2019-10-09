package com.yao.mySerializable;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化对象到磁盘
 * Created by Calm on 2018/11/22
 */
public class SerTask {

    public static void main(String[] args) throws Exception{
        Task t = new Task();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D://tasks"));
        oos.writeObject(t);
        oos.close();
    }
}
