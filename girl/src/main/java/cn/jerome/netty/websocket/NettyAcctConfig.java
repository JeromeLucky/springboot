/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: NettyAcctConfig
 * Author:   JG
 * Date:     2019/9/19 22:22
 * Description: Netty 配置信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.jerome.netty.websocket;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Netty 配置信息〉
 *
 * @author JG
 * @create 2019/9/19
 * @since 1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "netty")
public class NettyAcctConfig {

    private int port;

    private int bossThread;

    private int workerThread;

    private boolean keepalive;

    private int backlog;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getBossThread() {
        return bossThread;
    }

    public void setBossThread(int bossThread) {
        this.bossThread = bossThread;
    }

    public int getWorkerThread() {
        return workerThread;
    }

    public void setWorkerThread(int workerThread) {
        this.workerThread = workerThread;
    }

    public boolean isKeepalive() {
        return keepalive;
    }

    public void setKeepalive(boolean keepalive) {
        this.keepalive = keepalive;
    }

    public int getBacklog() {
        return backlog;
    }

    public void setBacklog(int backlog) {
        this.backlog = backlog;
    }
}