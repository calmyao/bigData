package com.yao.bigData.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;


/**
 * 相对于那些封装好的方法而言的更底层一些的操作方式
 * 上层那些mapreduce   spark 等运算框架,去hdfs中获取数据的时候,就是调用的这种底层的api
 * Created by Calm on 2018/4/9
 */
public class StreamAccess {

    FileSystem fs = null;
    @Before
    public void init() throws Exception {
        Configuration conf = new Configuration();
        fs = FileSystem.get(new URI("hdfs://mini1:9000"),conf,"hadoop");
    }

    @Test
    public void testDownLoadToLocal() throws Exception{
        //先获取一个文件的输入流--针对hdfs上的
        FSDataInputStream in = fs.open(new Path("/aaa"));

        //再构造一个文件的输出流--针对本地的
        FileOutputStream out = new FileOutputStream(new File("d:/temp/123.png"));

        //再将输入流中数据传输到输出流
        IOUtils.copyBytes(in,out,4096);
    }

    /**
     * hdfs支持随机定位进行文件读取,而且可以方便地读取指定长度
     * 用于上层分布式运算框架并发处理数据
     * @throws Exception
     */
    @Test
    public void testRandomAccess() throws Exception{
        //先获取一个文件的输入流--针对hdfs上的
        FSDataInputStream in = fs.open(new Path("/test.txt"));

        //可以将流的起始偏移量进行自定义
        in.seek(5);

        //再构造一个文件的输入流--针对本地的
        FileOutputStream out = new FileOutputStream(new File("d:/temp/123.txt"));

        IOUtils.copyBytes(in,out,19L,true);
    }

    /**
     * 显示hdfs上文件的内容
     * @throws Exception
     */
    @Test
    public void testCat() throws  Exception{
        FSDataInputStream in = fs.open(new Path("/test.txt"));

        IOUtils.copyBytes(in,System.out,1024);

    }

}
