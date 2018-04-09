package com.yao.bigData.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

/**
 * Created by Calm on 2018/3/30
 */
public class HdfsClient {

    FileSystem fs = null;

    @Before
    public void init() throws Exception {
        //构造有一个配置参数对象,设置一个参数:我们需要访问hdfs的URI
        //从FileSystem.get()方法就知道应该是去构造一个访问hdfs
        //文件系统的客户端,以及hdfs访问地址
        //new Configuration();的时候,它就会去加载jar包中的
        //hdfs-default.xml
        //然后再加载classpath下的hdfs-site.xml
        Configuration conf = new Configuration();
        conf.set("fs.default", "hdfs://mini1:9000");
        /**
         * 参数设置优先等级:1.客户端代码中的值
         *               2.classpath下的用户自定义配置文件
         *               3.然后是服务器的默认配置
         */
        conf.set("dfs.replication", "3");
        //获取一个hdfs的访问的客户端,根据参数,这个实例应该是DistributedFileSystem
        //的实例
        //fs = FileSystem.get(conf);
        //如果这样获取,那conf里面就可以不要配"fs.defaultFS"参数,而且,这个客户端的身份标识
        //已经是hadoop用户
        fs = FileSystem.get(new URI("hdfs://mini1:9000"), conf, "hadoop");

    }

    /**
     * 往hdfs上传文件
     *
     * @throws Exception
     */
    @Test
    public void testAddFileToHdfs() throws Exception {
        //要上传的文件所在的本地路径
        Path src = new Path("D:/temp/testhdfs.png");
        //要上传到hdfs的目标文件
        Path dst = new Path("/aaa");
        fs.copyFromLocalFile(src, dst);
        fs.close();
    }

    /**
     * 从hdfs中复制文件到本地文件系统中
     * windows中没有hadoop,需要在windows中解压一个windows版的hadoop包
     *
     * @throws Exception
     */
    @Test
    public void testDownloadFileToLocal() throws Exception {
        fs.copyToLocalFile(new Path("/aaa"), new Path("d:/temp/"));
        fs.close();
    }

    /**
     * @throws Exception
     */
    @Test
    public void testMkdirAndDeleteRename() throws Exception {
        //创建目录
        //fs.mkdirs(new Path("/a1/b1/c1"));
        //删除文件夹,如果是非空文件夹,参数2必须给值true
        //fs.delete(new Path("/a1/b1/c1"),true);
        //重命名文件或文件夹
        fs.rename(new Path("/a1"), new Path("/a2"));
    }

    /**
     * 查看目录信息,只显示文件
     *
     * @throws Exception
     */
    @Test
    public void testListFiles() throws Exception {
        //思考:为什么返回迭代器,而不是List之类的容器
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
        while (listFiles.hasNext()) {
            LocatedFileStatus fileStatus = listFiles.next();
            System.out.println(fileStatus.getPath().getName());
            System.out.println(fileStatus.getBlockSize());
            System.out.println(fileStatus.getPermission());
            System.out.println(fileStatus.getLen());
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            for (BlockLocation bl :
                    blockLocations) {
                System.out.println("block-length:" + bl.getLength() + "--" + "block-offset:" + bl.getOffset());
                String[] hosts = bl.getHosts();
                for (String host :
                        hosts) {
                    System.out.println(host);
                }
            }
            System.out.println("-----------分割线-----------");
        }
    }

    /**
     * 查看文件及文件夹信息
     * @throws Exception
     */
    @Test
    public void testListAll() throws Exception{
        FileStatus[] listStatus = fs.listStatus(new Path("/"));
        String flag = "d--              ";
        for (FileStatus fstatus :
                listStatus) {
            if(fstatus.isFile()){
                flag="f--               ";
            }
            System.out.println(flag + fstatus.getPath().getName());
        }
    }
}
