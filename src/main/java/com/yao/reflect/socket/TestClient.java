package com.yao.reflect.socket;

import java.io.*;
import java.net.Socket;

/**
 * Created by Calm on 2018/11/21
 */
public class TestClient {

    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("localhost",9898);
        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();
        System.out.println("start:");
        PrintWriter pw = new PrintWriter(new BufferedOutputStream(out));
        pw.println("com.yao.reflect.socket.TestBusiness:getPrice:yifu");
        pw.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String readLine = br.readLine();

        System.out.println("client get result:" + readLine);

        socket.close();

    }
}
