package com.zhifa.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;

public class HelloServer {
    public static void main(String[] args) throws Exception {
        //定义主从线程组 主线程组只用于客户端的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //工作线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            //netty服务器的创建 这个是启动类
            serverBootstrap.group(bossGroup, workerGroup);
            //chanel类型
            serverBootstrap.channel(NioSctpServerChannel.class);
            serverBootstrap.childHandler(new HelloServerInitializer());
            //等待  用于启动
            ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();
            //jiant监听关闭的channel 设置同步方式
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }
}
