package com.yao.testmapreduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by Calm on 2018/4/19
 * 假如数据量巨大,两表的数据时以文件的形式存储在HDFS中,需要mapreduce程序来实现以下SQL查询运算
 * "select a.id,a.date,b.name,b.category_id,b.price from t_order a join t_product on a.pid=b.id"
 * 实现机制:通过将关联的条件作为map输出的key,将两表满足join条件的数据并携带数据所来源的文件信息,发送同一个reduce task,在reduce中进行数据的串联
 */
public class OrderJoin {
    static class OrderJoinMapper extends Mapper<LongWritable,Text,Text,OrderJoinBean>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //拿一行数据,并且要分辨这行所属的文件
            String line = value.toString();

            String[] fields = line.split("\t");

            //拿到itemed
            String itemid = fields[0];

            //获取到这一行所在的文件名(通过inpusplit)
            String name = "你拿到的文件名";

            //根据文件名,切分出个字段(如果是a,切分出两个字段,如果是b,切分出3个字段)

            OrderJoinBean bean = new OrderJoinBean();
            bean.set(null,null,null,null);
            context.write(new Text(itemid),bean);
        }
    }


    static class OrderJoinReducer extends Reducer<Text,OrderJoinBean,OrderJoinBean,NullWritable>{

        @Override
        protected void reduce(Text key, Iterable<OrderJoinBean> values, Context context) throws IOException, InterruptedException {
            //拿到的key是某一个itemid,比如1000
            //拿到的beans是来自于两类文件的bean
            //{1000,amount}{1000,amount}{1000,amount}  ---  {1000,price,name}

            //将来自于b文件的bean里面的字段,跟来自于a的所有bean进行字段拼接并输出
        }
    }

    /**
     * 缺点:这种方式中,join的操作是在reduce阶段完成,reduce端的处理压力太大,map节点的运算负载则很低,资源利用率不高,且在reduce阶段极易产生数据倾斜
     *
     * 解决方案:map端join实现方式
     */

}
