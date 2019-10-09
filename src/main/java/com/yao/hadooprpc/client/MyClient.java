package com.yao.hadooprpc.client;

import com.yao.hadooprpc.protocol.SomeService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.net.InetSocketAddress;

/**
 * Created by Calm on 2019/1/10
 */
public class MyClient {
    public static void main(String[] args) throws Exception{
        SomeService someService = RPC.getProxy(SomeService.class, Long.MAX_VALUE, new InetSocketAddress("localhost", 5555), new Configuration());
        String ret = someService.heartBeat("wilson");
        System.out.println(ret);

    }
}
