package com.yao.hadooprpc.protocol;

/**
 * Created by Calm on 2019/1/10
 */
public interface SomeService {
    public long versionID = Long.MAX_VALUE;

    public String heartBeat(String name);
}
