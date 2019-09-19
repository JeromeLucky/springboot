/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: WebSocketService
 * Author:   JG
 * Date:     2019/9/19 22:09
 * Description: 程序的启动入口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br> 
 * 〈程序的启动入口〉
 *
 * @author JG
 * @create 2019/9/19
 * @since 1.0.0
 */

@Component
public class WebSocketService {
    @Autowired
    private NettyAcctConfig nettyAccountConfig;


    @Bean(name = "bossGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup bossGroup(){
        return new NioEventLoopGroup(nettyAccountConfig.getBossThread());
    }

    @Bean(name = "workerGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup workerGroup(){
        return new NioEventLoopGroup(nettyAccountConfig.getWorkerThread());
    }

    @Bean(name = "tcpSocketAddress")
    public InetSocketAddress tcpPost(){
        return new InetSocketAddress(nettyAccountConfig.getPort());
    }

    @Bean(name = "tcpChannelOptions")
    public Map<ChannelOption<?>, Object> tcpChannelOptions(){
        Map<ChannelOption<?>, Object> options = new HashMap<ChannelOption<?>, Object>();
        options.put(ChannelOption.SO_KEEPALIVE, nettyAccountConfig.isKeepalive());
        options.put(ChannelOption.SO_BACKLOG, nettyAccountConfig.getBacklog());
        return options;
    }

    @Autowired
    @Qualifier("selfChannelInitializer")
    private WebSocketChannelHandler webSocketChannelHandler;

    @Bean(name = "serverBootstrap")
    public ServerBootstrap bootstrap(){
        ServerBootstrap b = new ServerBootstrap();
                 b.group(bossGroup(), workerGroup())
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(webSocketChannelHandler);
        Map<ChannelOption<?>, Object> tcpChannelOptions = tcpChannelOptions();
        Set<ChannelOption<?>> keySet = tcpChannelOptions.keySet();
        for (@SuppressWarnings("rawtypes") ChannelOption option : keySet) {
            b.option(option, tcpChannelOptions.get(option));
        }
        return b;
    }
}
