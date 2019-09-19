package cn.jerome;


import cn.jerome.netty.websocket.TCPServer;
import cn.jerome.netty.websocket.WebSocketService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling //启动异步任务
public class GirlApplication {
	public static void main(String[] args) throws Exception {
		//SpringApplication.run(GirlApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(GirlApplication.class, args);
		//注入WebSocketService 获取对应Bean
		WebSocketService socketService = context.getBean(WebSocketService.class);
		//注入TCPServer 获取对应Bean
		TCPServer tcpServer = context.getBean(TCPServer.class);
		//启动websocket的服务
		tcpServer.start();

	}
}
