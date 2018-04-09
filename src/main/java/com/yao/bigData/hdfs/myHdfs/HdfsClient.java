package com.yao.bigData.hdfs.myHdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 自己写一个hdfs,未完待续...
 * Created by Calm on 2018/4/1
 */
public class HdfsClient {

    public String readFile() throws Exception{
        Socket socket = new Socket("datanode",8888);
        OutputStream out = socket.getOutputStream();
        byte[] b = new byte[4096];
        FileInputStream in = new FileInputStream(new File("D:/temp/testhdfs.png"));
        while (in.read() != -1){
            out.write(b);
        }
        return null;
    }

}
