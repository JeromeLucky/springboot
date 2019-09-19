/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TCPServer
 * Author:   JG
 * Date:     2019/9/19 22:37
 * Description: 启动协助类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;


/**
 * 〈一句话功能简述〉<br> 
 * 〈启动协助类〉
 *
 * @author JG
 * @create 2019/9/19
 * @since 1.0.0
 */
@Component
public class TCPServer {

    @Autowired
    @Qualifier("serverBootstrap")
    private ServerBootstrap serverBootstrap;

    @Autowired
    @Qualifier("tcpSocketAddress")
    private InetSocketAddress tcpPort;

    private Channel serverChannel;

    public void start() throws Exception {
        System.out.println("-------------------------###########-------------------------------------"+tcpPort);
        serverChannel =  serverBootstrap.bind(tcpPort).sync().channel().closeFuture().sync().channel();
    }

    @PreDestroy
    public void stop() throws Exception {
        serverChannel.close();
        serverChannel.parent().close();
    }
}
