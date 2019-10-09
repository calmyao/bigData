package com.yao.mySerializable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Calm on 2018/11/22
 */
public class Task implements Runnable,Serializable {

    private  static final long serialVersionUID = 1L;

    @Override
    public void run() {
        System.out.println(new Date());
    }
}
