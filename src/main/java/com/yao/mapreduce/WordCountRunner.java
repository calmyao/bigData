package com.yao.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Created by Calm on 2018/4/16
 * 定义一个主类,用来描述job并提交job
 */
public class WordCountRunner {
    //把业务逻辑相关的信息(哪个是mapper,哪个是reducer,要处理的数据在哪里,输出的结果放在哪里)
    //描述成一个job对象
    //把这个描述好的job提交给集群去运行
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        Job wcjob = Job.getInstance(conf);
        //指定我这个job所在的jar包
        //wcjob.setJar("/home/hadoop/wordcount.jar");
        wcjob.setJarByClass(WordCountRunner.class);
        wcjob.setMapperClass(WordCountMapper.class);
        wcjob.setReducerClass(WordCountReduce.class);
        //设置我们的业务逻辑Mapper类的输出key和value的数据类型
        wcjob.setMapOutputKeyClass(Text.class);
        wcjob.setMapOutputValueClass(IntWritable.class);
        //设置我们的业务逻辑Reducer类的输出key和value的数据类型
        wcjob.setOutputKeyClass(Text.class);
        wcjob.setOutputValueClass(IntWritable.class);

        //指定要处理的数据所在的位置
        FileInputFormat.setInputPaths(wcjob,"hdfs://mini1:9000/wordcount/data/big.txt");
        //指定处理完成之后的结果所保存的位置
        FileOutputFormat.setOutputPath(wcjob,new Path("hdfs://mini1:9000/wordcount/output/"));

        //向yarn集群提交这个job
        boolean res = wcjob.waitForCompletion(true);
        System.exit(res?0:1);
    }
}
