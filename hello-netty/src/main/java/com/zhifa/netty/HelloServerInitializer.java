package com.zhifa.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/*
* 初始化器  chanel注册后 会执行里面的相应的初始化方法
* */
public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel channel) throws Exception {
        //通过channel 获取对应的管道
        ChannelPipeline pipeline = channel.pipeline();
        //通过管道添加handle  由netty提供的助手类 用于请求处理的编解码工作
        pipeline.addLast("httpServerCodec",new HttpServerCodec());

        //ti添加自定义拦截器..
        pipeline.addLast("customHandler", null);

    }
}
