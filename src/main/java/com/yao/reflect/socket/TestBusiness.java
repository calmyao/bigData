package com.yao.reflect.socket;

/**
 * Created by Calm on 2018/11/21
 */
public class TestBusiness implements IBusiness {
    @Override
    public int getPrice(String good) {
        return good.equals("yifu") ? 10 : 20;
    }
}
