/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ApplicationStartListener
 * Author:   JG
 * Date:     2019/9/20 0:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author JG
 * @create 2019/9/20
 * @since 1.0.0
 */

//@Component
public class ApplicationStartListener implements ApplicationListener<ApplicationStartedEvent> {
    @Autowired
    @Qualifier("serverBootstrap")
    private ServerBootstrap serverBootstrap;
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        try {
            serverBootstrap.bind(9999).sync().channel().closeFuture().sync().channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
