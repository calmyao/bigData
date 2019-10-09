package com.yao.mapreduce;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Calm on 2018/4/16
 * Java的序列化是一个重量级序列化框架(Serializable),一个对象被序列化后,会附带很多
 * 额外的信息(各种校验信息,header,继承体系...),不便于在网络中高效传输;
 * 所以,hadoop自己开发了一套序列化机制(Writable),精简,高效
 */
public class TestSeri {
    //简单代码验证两种序列化机制的差别
    public static void main(String[] args) throws  Exception{
        //定义两个ByteArrayOutPutStream,用来接收不同序列化机制的序列化结果
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        ByteArrayOutputStream ba2 = new ByteArrayOutputStream();

        //定义两个DataOutputStream,用于将普通对象进行jdk标准序列化
        DataOutputStream dout = new DataOutputStream(ba);
        DataOutputStream dout2 = new DataOutputStream(ba2);
        ObjectOutputStream obout = new ObjectOutputStream(dout2);
        //定义两个bean,作为序列化的源对象

    }
}
