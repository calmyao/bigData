package com.yao.mapreduce;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by Calm on 2018/4/18
 */
public class FlowBean implements WritableComparable<FlowBean> {
    public long upflow;
    public long dflow;
    public long sumflow;

    public long getSumflow() {
        return sumflow;
    }

    @Override
    public int compareTo(FlowBean o) {
        //实现按照sumflow的大小倒叙排序
        return sumflow>o.getSumflow()?-1:1;
    }

    /**
     * 序列化的方法
     * @param out
     * @throws IOException
     */
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(upflow);
        out.writeLong(dflow);
        //可以考虑不序列化总流量,因为总流量是可以通过上行流量和下行流量
        //计算出来的
        out.writeLong(sumflow);
    }

    /**
     * 反序列化的方法,反序列化时,从流中读取到的各个字段的顺序
     * 应该与序列化时写出去的顺序保持一致
     * @param in
     * @throws IOException
     */
    @Override
    public void readFields(DataInput in) throws IOException {
        upflow = in.readLong();
        dflow = in.readLong();
        sumflow = in.readLong();
    }
}
