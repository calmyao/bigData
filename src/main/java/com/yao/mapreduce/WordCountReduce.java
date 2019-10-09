package com.yao.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by Calm on 2018/4/16
 * 定义一个reducer类
 */
public class WordCountReduce extends Reducer<Text,IntWritable,Text,IntWritable>{
    //生命周期:框架每传递进来一个kv组,reduce方法被调用一次
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //定义一个计数器
        int count = 0;
        //遍历这一组kv的所有v,累计到count中
        for (IntWritable value :
                values) {
            count += value.get();
        }
        context.write(key,new IntWritable(count));

    }
}
