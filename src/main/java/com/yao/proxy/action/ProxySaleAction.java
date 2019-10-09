package com.yao.proxy.action;

import com.yao.proxy.proxyclass.ProxyBoos;
import com.yao.proxy.service.IBoss;
import com.yao.proxy.service.impl.Boss;
import org.junit.Test;

/**
 * Created by Calm on 2018/11/22
 * 什么是动态代理?
 * 简单的写一个模板接口,剩下的个性化工作,好给动态代理来完成
 */
public class ProxySaleAction {

    /**
     * 使用代理,在这个代理中,只代理了Boost的yifu方法
     * 定制化业务,可以改变原接口的参数、返回值等
     * @throws Exception
     */
    @Test
    public void saleByProxy() throws Exception{
        IBoss boss = ProxyBoos.getProxy(10,IBoss.class, Boss.class);
        //将代理的方法实例化成接口
        //IBoss boss = new Boss();
        System.out.println("代理");
        int money = boss.yifu("xxl");//调用接口的方法,实际上调用方式没有变
        System.out.println("成交价:" + money);
    }

}
