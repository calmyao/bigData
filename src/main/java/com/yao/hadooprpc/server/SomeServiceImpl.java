package com.yao.hadooprpc.server;

import com.yao.hadooprpc.protocol.SomeService;

/**
 * Created by Calm on 2019/1/10
 */
public class SomeServiceImpl implements SomeService{
    @Override
    public String heartBeat(String name) {
        System.out.println("接收到客户端" + name + "的心跳,正常连接………………");
        return "心跳成功";
    }
}
