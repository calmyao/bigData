package com.yao.testmapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.*;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.CompressedSplitLineReader;
import org.apache.hadoop.mapreduce.lib.input.SplitLineReader;

import java.io.InputStream;
import java.util.HashMap;

/**
 * 定义自己的从map到reduce之间的数据(分组)分发规则
 * 按照手机号所属的省份来分发(分组)
 * ProvincePartitioner
 * 默认的分组组件是HashPartitioner
 * Created by Calm on 2018/4/19
 */
public class ProvincePartitioner extends Partitioner<Text,FlowBean> {

    static HashMap<String,Integer> provinceMap = new HashMap<String,Integer>();

    static {
        provinceMap.put("135",0);
        provinceMap.put("136",1);
        provinceMap.put("137",2);
        provinceMap.put("138",3);
        provinceMap.put("139",4);
    }
    @Override
    public int getPartition(Text key, FlowBean value, int numPartitions) {
        Integer code = provinceMap.get(key.toString().substring(0, 3));
        return code == null ? 5 : code;
    }

    //hadoop自带的InputFormat类内置支持压缩文件的读取,
    //比如TextInputformat类,在initialize方法中
    public void initialize(InputSplit genericSplit, TaskAttemptContext context) throws Exception{
        FileSplit split = (FileSplit) genericSplit;
        Configuration job = context.getConfiguration();
        int anInt = job.getInt("", 5);
        long start = split.getStart();
        long end =  start + split.getLength();
        final Path file = split.getPath();
        //open the file and seek to the start of the split
        final FileSystem fs = file.getFileSystem(job);
        FSDataInputStream fileIn = fs.open(file);
        //根据文件后缀名创建相应压缩编码codec
        CompressionCodec codec = new CompressionCodecFactory(job).getCodec(file);
        boolean isCompressedInput;
        Decompressor decompressor;
        byte[] recordDelimiterBytes = null;
        SplitLineReader in;
        InputStream filePosition;
        if(null != codec){
            isCompressedInput = true;
            decompressor = CodecPool.getDecompressor(codec);
            //判断是否属于可切片压缩编码类型
            if(codec instanceof SplittableCompressionCodec){
                final  SplitCompressionInputStream cIn = ((SplittableCompressionCodec)codec).createInputStream(fileIn,decompressor,start,end,SplittableCompressionCodec.READ_MODE.BYBLOCK);
                //如果是可切片压缩编码,则创建一个CompressedSplitLineReader读取压缩数据
                in = new CompressedSplitLineReader(cIn, job, recordDelimiterBytes);
                start = cIn.getAdjustedStart();
                end = cIn.getAdjustedEnd();
                filePosition = cIn;
            }else {
                //如果是不可切片压缩编码,则创建一个SplitLineReader读取压缩数据,并将文件输入流转换成解压
                //数据流给普通SplitLineReader读取
                in = new SplitLineReader(codec.createInputStream(fileIn,decompressor),job,recordDelimiterBytes);
                filePosition = fileIn;
            }
        }else{
            fileIn.seek(start);
            //如果不是压缩文件,则创建普通SplitLineReader读取数据
            in = new SplitLineReader(fileIn,job,recordDelimiterBytes);
            filePosition = fileIn;
        }
    }
}
