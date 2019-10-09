package com.yao.netty.sendobject.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Calm on 2019/05/24
 * 配置服务器功能,如线程,端口.事项服务器处理程序,它包含业务逻辑,决定当有
 * 一个请求连接或接收数据时该做什么
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port){
        this.port = port;
    }

    public void start() throws Exception{
        EventLoopGroup eventLoopGroup = null;
        try{
            //创建ServerBootStrap实例来引导绑定和启动服务器
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //创建NioEventLoopGroup对象来处理事件,
            //如接受新连接,接收数据,写数据等等
            eventLoopGroup = new NioEventLoopGroup();

            //指定通道类型为NioServerSocketChannel,一种一步模式,OIO阻塞模式为OioServerSocketChannel
            //设置InetSocketAddress让服务器监听某个端口已等待客户端连接
            //serverBootstrap.group(eventLoopGroup).channel(NioServerSocketChannel.class).localAddress("localhost",port).childHandler(new ChannelInitializer<Channel>() {
            //});
        }finally {

        }
    }
}
