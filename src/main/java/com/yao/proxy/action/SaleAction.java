package com.yao.proxy.action;

import com.yao.proxy.service.IBoss;
import com.yao.proxy.service.impl.Boss;
import org.junit.Test;

/**
 * Created by Calm on 2018/11/22
 */
public class SaleAction {

    /**
     * 不使用代理,直接调用方法
     * 方法中规定什么业务,就只能调用什么业务,规定什么返回值,就只能输出什么返回值
     * @throws Exception
     */
    @Test
    public void saleByBoosSelf() throws Exception{
        IBoss boss = new Boss();
        System.out.println("自营");
        int money = boss.yifu("xxl");
        //自营,不需要客服,没有聊天记录
        System.out.println("成交价:" + money);
    }
}
