package com.yao.proxy.service.impl;

import com.yao.proxy.service.IBoss;

/**
 * 实现了卖衣服的接口
 * 自定义了自己的业务,卖裤子
 * Created by Calm on 2018/11/22
 */
public class Boss implements IBoss {
    @Override
    public int yifu(String size) {
        System.err.println("卖衣服--衣服型号:" + size);
        //衣服价格,从数据库读取
        return 50;
    }

    public void kuzi(){
        System.err.println("卖裤子");
    }
}
