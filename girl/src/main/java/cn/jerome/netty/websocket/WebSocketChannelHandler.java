/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: WebSocketChannelHandler
 * Author:   JG
 * Date:     2019/9/19 22:00
 * Description: 初始化加载信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 * 〈一句话功能简述〉<br> 
 * 〈初始化加载信息 初始化连接组件〉
 *
 * @author JG
 * @create 2019/9/19
 * @since 1.0.0
 */
@Component
@Qualifier("selfChannelInitializer")
public class WebSocketChannelHandler extends ChannelInitializer<SocketChannel> {
    @Autowired
    private WebSocketHandler webSocketHandler;
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast("http-codec",new HttpServerCodec());
        socketChannel.pipeline().addLast("aggregator",new HttpObjectAggregator(65535));
        socketChannel.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
        socketChannel.pipeline().addLast(new WebSocketServerProtocolHandler("/ws"));
        socketChannel.pipeline().addLast("handler",webSocketHandler);  //这里不能使用new，不然在handler中不能注入依赖

    }
}
